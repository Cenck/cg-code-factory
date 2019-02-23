package com.cengel.code.config;

import com.cengel.code.model.TemplateModelBean;
import com.cengel.code.task.TypeConverter;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:02
 **/
@Data
@ConfigurationProperties("pojo.code-factory")
public class CodeFactoryProperties implements Serializable, InitializingBean {

	/** 项目名称 */
	private String                  projectName;
	/** 模板 */
	private List<TemplateModelBean> templates;
	/** 转换器 */
	private Map<String, String>     typeConverterMap;
	private String                  schema;
	/**  数据对象  隐藏字段  */
	private List<String>            hideOnEntity;
	/**  页面 隐藏字段 	  */
	private List<String>            hideOnPage;
	private String basePackage;
	/** datasource */
	private String outPath;
	/** //表名，允许多张表，以英文,号隔开 */
	private String tableName;
	/** //web资源：ftl,html的路径 */
	private String webPath;
	/** //执行类型，生成所有或仅更新entity */
	private String startType;

	private TypeConverter typeConverter;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setTypeConverter(new TypeConverter(typeConverterMap));
	}
}
