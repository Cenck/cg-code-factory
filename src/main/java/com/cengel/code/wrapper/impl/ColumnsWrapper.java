package com.cengel.code.wrapper.impl;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.model.ColumnsDto;
import com.cengel.code.task.TypeConverter;
import com.cengel.code.util.TabColStrUtil;
import com.cengel.code.wrapper.TypeWrapper;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-07 18:45
 **/
public class ColumnsWrapper implements TypeWrapper<ColumnsDto> {

	private CodeFactoryProperties properties;


	public ColumnsWrapper(CodeFactoryProperties properties){
		this.properties = properties;
	}

	//@Description("Entity中不生成的字段")
	private static List<String> HideOnEntityFields;
	//@Description("页面中不展示的字段")
	private static List<String> hideOnPageFields;

	@PostConstruct
	public void initHideOnPageList() {
		hideOnPageFields = new ArrayList<>(properties.getHideOnPage().size());
		for (String field : properties.getHideOnPage()) {
			hideOnPageFields.add(field);
		}
		HideOnEntityFields = new ArrayList<>(properties.getHideOnEntity().size());
		for (String field : properties.getHideOnEntity()) {
			HideOnEntityFields.add(field);
		}
	}

	@Override
	public ColumnsDto convert(ColumnsDto dto, TypeConverter typeConverter) {
		// 根据规则生成实体名称
		dto.setJavacName(TabColStrUtil.tabName2jfiled(dto.getName()));
		dto.setJavaName(TabColStrUtil.firstLowerCase(dto.getJavacName()));
		dto.setJavaType(typeConverter.toJavaType(dto.getDataType()));
		dto.setDataType(typeConverter.toJdbcType(dto.getDataType()));
		for (String whereHide : HideOnEntityFields) {
			if (whereHide.equalsIgnoreCase(dto.getJavaName())) {
				dto.setWhereHide(true);
			}
		}

		/********************|| 设置entity需要隐藏的字段 ||********************/
		HideOnEntityFields.forEach(baseField -> {
			if (baseField.equalsIgnoreCase(dto.getJavaName())) {
				dto.setVisible(false);
			}
		});

		/********************|| 设置页面需要隐藏的字段 ||********************/
		if (dto.getName().toUpperCase().endsWith("ID")) {
			dto.setHideOnPage(true);
		}
		hideOnPageFields.forEach(bf -> {
			if (bf.equalsIgnoreCase(dto.getJavaName())) {
				dto.setHideOnPage(true);
			}
		});

		return dto;
	}

	private static List<String> getBaseFiled(Class c) {
		List<String> ret = new ArrayList<String>();
		return getBaseFiled(c, ret);
	}

	//递归找父类
	private static List<String> getBaseFiled(Class cls, List<String> ret) {
		if (cls != null) {
			for (Field field : cls.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				ret.add(field.getName());
			}
		}
		if (cls != null && cls.getSuperclass() != null) {
			getBaseFiled(cls.getSuperclass(), ret);
		}
		return ret;
	}

}
