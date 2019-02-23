package com.cengel.code.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:42
 **/
@Data
public class TemplateModelBean implements Serializable {
	private String name;
	private String temPath;
	private String pkg;
	private String fileNameSuffix;
}
