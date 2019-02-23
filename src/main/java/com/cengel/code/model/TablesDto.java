package com.cengel.code.model;

import com.cengel.code.entity.Tables;
import com.cengel.code.util.TabColStrUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-03 14:29
 **/
@Getter
@Setter
public class TablesDto extends Tables {

	private String              basePackage;//基础表路径
	private String              schema;//dataBase
	private String              outPath;//输入根目录
	private String              webPath;//web资源：ftl,html的路径

	private String                                     javaName; //JavaSe
	private String                                     javacName; //JavaClassName
	//默认配置参数
	private List<ColumnsDto> columnList = new ArrayList<>();


	public String getJavaName() {
		if (javaName == null || javaName == "") {
			this.javaName = TabColStrUtil.tabName2jfiled(this.getTableName());
		}
		return javaName;
	}

}
