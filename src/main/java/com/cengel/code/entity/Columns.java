package com.cengel.code.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "information_schema.COLUMNS")
public class Columns implements Serializable {

	// 字段名称
	@Id
	@Column(name = "COLUMN_NAME")
	private String name;
	// 字段序号
	@Column(name = "ORDINAL_POSITION")
	private Long   ordinal;
	// 字段类型
	@Column(name = "COLUMN_TYPE")
	private String type;
	// 字段类型(全)
	@Column(name = "DATA_TYPE")
	private String dataType;
	// 默认值
	@Column(name = "COLUMN_DEFAULT")
	private String defValue;
	// 是否可为空
	@Column(name = "IS_NULLABLE")
	private String nullable;
	// 字段长度(字符串)
	@Column(name = "CHARACTER_MAXIMUM_LENGTH")
	private Long   length;
	// 字段长度(数值)
	@Column(name = "NUMERIC_PRECISION")
	private Long   precision;
	// 小数长度(数值)
	@Column(name = "NUMERIC_SCALE")
	private Long   scale;
	// PRI：主键
	@Column(name = "COLUMN_KEY")
	private String key;
	// auto_increment标识为自增长
	@Column(name = "EXTRA")
	private String extra;
	// 字段注释
	@Column(name = "COLUMN_COMMENT")
	private String comment;


}