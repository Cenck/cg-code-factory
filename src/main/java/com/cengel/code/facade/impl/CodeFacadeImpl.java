package com.cengel.code.facade.impl;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.facade.CodeFacade;
import com.cengel.code.model.CodeWritePojo;
import com.cengel.code.model.TablesDto;
import com.cengel.code.model.TemplateModelBean;
import com.cengel.code.service.impl.TableServiceImpl;
import com.cengel.code.task.FileWriteTask;
import com.cengel.code.util.TabColStrUtil;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Title:
 * @Description:
 * @Author zhz
 * @Time 2018/7/26 - 15:04
 * @Version V1.0
 **/
@Service
public class CodeFacadeImpl implements CodeFacade {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private FreeMarkerConfigurer     freeMarkerConfigurer;
	@Resource
	private ScheduledExecutorService scheduledExecutor;
	@Resource
	private TableServiceImpl         tableService;
	@Resource
	private CodeFactoryProperties    properties;

	@Override
	public void execute() {
		//先删除旧数据
		this.delOldPdtData();
		try {
			if (properties.getTableName() != null) {
				String[] tabList = properties.getTableName()
						.split(",");
				for (String tabName : tabList) {
					//TODO 取表信息
					TablesDto dto = tableService.getDto(tabName);
					logger.error("===========================");
					logger.warn("==：执行表: " + properties.getSchema() + "-" + tabName);
					logger.error("===========================");
					//TODO 拆分表名，逐个写入
					buildAndExec(properties.getTemplates(), dto);
				}
			}
			//所有线程启动就绪以后，监控线程执行完毕
			scheduledExecutor.shutdown();
			while (true) {
				if (scheduledExecutor.isTerminated()) {
					logger.warn("所有执行完毕,感谢使用！");
					break;
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			scheduledExecutor.shutdownNow();
			logger.warn(e.getMessage());
			logger.warn("出现错误，程序退出。");
		}

	}

	//删除旧数据
	private void delOldPdtData() {
		File root = new File(properties.getOutPath());
		if (!root.exists()) {
			root.mkdir();
		} else {
			try {
				FileUtils.deleteDirectory(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
			root.mkdir();
		}
	}

	private void buildAndExec(List<TemplateModelBean> templateList, TablesDto dto) {
		for (TemplateModelBean linkedHashMap : templateList) {
			String templatePath = properties.getProjectName() + "/" + linkedHashMap.getTemPath()
					.toString();
			String name = linkedHashMap.getName()
					.toString();
			String pkg = linkedHashMap.getPkg()
					.toString();
			String fileNameSuffix = linkedHashMap.getFileNameSuffix()
					.toString();

			String fileName = "", fileJavaName = "";
			if (fileNameSuffix.contains("java") || fileNameSuffix.contains(".xml")) {
				fileJavaName = TabColStrUtil.firstUpperCase(dto.getJavaName());
				pkg = "classes." + pkg;
			} else {
				fileJavaName = TabColStrUtil.firstLowerCase(dto.getJavaName());
				pkg = "templates" + properties.getWebPath();
			}
			if (fileNameSuffix.contains("~")) {
				fileName = fileNameSuffix.replace("~", fileJavaName);
			} else {
				fileName = fileJavaName + fileNameSuffix;
			}
			String outData = executeTemplate(templatePath, dto);

			final CodeWritePojo pojo = new CodeWritePojo();
			pojo.setRootPath(properties.getOutPath());
			pojo.setFileName(fileName);
			pojo.setPkg(pkg);
			pojo.setDatas(outData);
			scheduledExecutor.execute(new FileWriteTask(pojo));
		}

	}

	private String executeTemplate(String name, Object context) {
		StringWriter out = new StringWriter();
		Template template = null;
		try {
			template = this.freeMarkerConfigurer.getConfiguration()
					.getTemplate(name + ".ftl");
			template.process(context, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toString();
	}

}
