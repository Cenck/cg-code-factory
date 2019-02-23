package com.cengel.code.wrapper.impl;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.model.TablesDto;
import com.cengel.code.task.TypeConverter;
import com.cengel.code.util.TabColStrUtil;
import com.cengel.code.wrapper.TypeWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-07 18:45
 **/
@Component
public class TableWrapper implements TypeWrapper<TablesDto> {

	@Resource
	private CodeFactoryProperties properties;

	@Override
	public TablesDto convert(TablesDto table, TypeConverter typeConverter) {
		// 根据规则生成实体名称
		table.setJavacName(TabColStrUtil.tabName2jfiled(table.getTableName()));
		table.setJavaName(TabColStrUtil.firstLowerCase(table.getJavaName()));
		table.setWebPath(properties.getWebPath());
		table.setOutPath(properties.getOutPath());
		table.setBasePackage(properties.getBasePackage());
		table.setSchema(properties.getSchema());
		return table;
	}

}
