package com.cengel.code.handler;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.facade.CodeFacade;
import com.cengel.code.model.ParamContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:13
 **/
@Component
public class CodeStartupRunner implements CommandLineRunner {

	@Resource
	private CodeFactoryProperties properties;
	@Resource
	private CodeFacade codeFacade;

	@Override
	public void run(String... args) throws Exception {
		this.refineProperties();
		System.out.println(properties.getProjectName());
		codeFacade.execute();
	}

	private void refineProperties() {
		properties.setBasePackage(ParamContext.basePackage);
		properties.setOutPath(ParamContext.outDir);
		properties.setTableName(ParamContext.tableName);
		properties.setWebPath(ParamContext.webPath);
		properties.setStartType(ParamContext.startType);
	}
}
