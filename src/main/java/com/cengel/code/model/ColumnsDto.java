package com.cengel.code.model;

import com.cengel.code.entity.Columns;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-07 18:26
 **/
@Getter
@Setter
public class ColumnsDto extends Columns implements Serializable {

	private String  javaName;//首字母小写
	private String  javaType;
	private String  JavacName; // JavaClassName
	//@Description("在entity中隐藏本字段")
	private boolean visible = true; //过滤掉父entity有的字段
	//@Description("where中需要过滤的字段")
	private boolean whereHide;//过滤掉createTime等字段
	//@Description("页面不需要展示的字段")
	private boolean hideOnPage;

}
