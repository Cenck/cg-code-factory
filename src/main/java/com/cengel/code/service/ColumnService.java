package com.cengel.code.service;

import com.cengel.code.entity.Columns;
import com.cengel.code.model.ColumnsDto;

import java.util.List;

public interface ColumnService   {

	List<ColumnsDto> findDto(String tableName);

	List<Columns> findByTableName(String schema, String tableName);

}
