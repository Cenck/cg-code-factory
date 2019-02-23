package com.cengel.code.service.impl;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.entity.Tables;
import com.cengel.code.model.TablesDto;
import com.cengel.code.service.TableService;
import com.cengel.code.util.JdbcTempUtil;
import com.cengel.code.wrapper.impl.TableWrapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author zhz
 * @Time 2018/7/26 - 10:43
 * @Version V1.0
 **/
@Log
@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private JdbcTemplate      jdbcTemplate;
	@Autowired
	private ColumnServiceImpl columnService;

	private final String FIND_SQL = "SELECT upper(ts.TABLE_CATALOG) TABLE_CATALOG,        upper(ts.TABLE_SCHEMA)  TABLE_SCHEMA,        upper(ts.TABLE_NAME)    TABLE_NAME,        ts.TABLE_COMMENT        TABLE_COMMENT ,   ts.table_rows TABLE_ROWS,  ts.create_time CREATE_TIME,  ts.update_time UPDATE_TIME  FROM information_schema.TABLES ts  WHERE ts.TABLE_SCHEMA = ";

	@Resource
	private TableWrapper          tableWrapper;
	@Resource
	private CodeFactoryProperties properties;

	@Override
	public TablesDto getDto(String name) {
		String schema = properties.getSchema();
		Tables tables = findByName(schema, name);
		TablesDto tablesDto = new TablesDto();
		tablesDto.setCatalog(tables.getCatalog());
		tablesDto.setComment(tables.getComment());
		tablesDto.setCreateTime(tables.getCreateTime());
		tablesDto.setTableName(tables.getTableName());
		tablesDto.setRows(tables.getRows());
		tablesDto.setSchema(tables.getSchema());
		tablesDto.setUpdateTime(tables.getUpdateTime());
		tablesDto.setColumnList(columnService.findDto(name));
		tableWrapper.convert(tablesDto, properties.getTypeConverter());
		return tablesDto;
	}

	@Override
	public Tables findByName(String schema, String name) {
		String sql = FIND_SQL + "'" + schema + "' AND ts.TABLE_NAME= '" + name + "'";
		log.info(sql);
		return jdbcTemplate.queryForObject(sql, null, (rs, rowNum) -> {
			Tables tables = new Tables();
			JdbcTempUtil.handleRs(tables, rs);
			return tables;
		});
	}

	@Override
	public List<Tables> findByNames(String schema, String... tables) {
		StringBuilder stringBuilder = new StringBuilder(FIND_SQL + schema + " AND ts.TABLE_NAME in (");
		for (String table : tables) {
			stringBuilder.append("'" + table + "'" + ",");
		}
		stringBuilder.setLength(stringBuilder.length() - 1);
		stringBuilder.append(" ) ");
		return jdbcTemplate.query(stringBuilder.toString(), (rs, rowNum) -> {
			Tables tables1 = new Tables();
			JdbcTempUtil.handleRs(tables1, rs);
			return tables1;
		});
	}
}
