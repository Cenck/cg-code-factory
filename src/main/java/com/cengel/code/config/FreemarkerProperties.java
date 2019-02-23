package com.cengel.code.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:49
 **/
@Data
@ConfigurationProperties("spring.freemarker")
public class FreemarkerProperties implements Serializable {

	private Boolean cache;
	private String  requestContextAttribute;
	private String  templateLoaderPath;
	private Boolean preferFileSystemAccess;
	private Properties settings;
}
