package com.cengel.code.service.impl;

import com.cengel.code.config.CodeFactoryProperties;
import com.cengel.code.entity.Columns;
import com.cengel.code.model.ColumnsDto;
import com.cengel.code.service.ColumnService;
import com.cengel.code.util.JdbcTempUtil;
import com.cengel.code.wrapper.impl.ColumnsWrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author zhz
 * @Time 2018/7/26 - 10:25
 * @Version V1.0
 **/
@Service
public class ColumnServiceImpl implements ColumnService {

	private final static String COLUMN_SQL = "SELECT upper(cs.COLUMN_NAME)       \"COLUMN_NAME\",       cs.ORDINAL_POSITION         ORDINAL_POSITION,       upper(cs.COLUMN_TYPE)       COLUMN_TYPE,       upper(cs.DATA_TYPE)         DATA_TYPE,       cs.COLUMN_DEFAULT           COLUMN_DEFAULT,       cs.IS_NULLABLE              IS_NULLABLE,       cs.CHARACTER_MAXIMUM_LENGTH CHARACTER_MAXIMUM_LENGTH,       cs.NUMERIC_PRECISION        NUMERIC_PRECISION,       cs.NUMERIC_SCALE            NUMERIC_SCALE,       upper(cs.COLUMN_KEY)        \"COLUMN_KEY\",       cs.EXTRA                    EXTRA,       cs.COLUMN_COMMENT           \"COLUMN_COMMENT\" FROM information_schema.COLUMNS cs WHERE cs.TABLE_SCHEMA = ";

	@Resource
	private JdbcTemplate          jdbcTemplate;
	@Resource
	private CodeFactoryProperties properties;
	@Resource
	private ColumnsWrapper columnsWrapper;

	@Override
	public List<ColumnsDto> findDto(String tableName) {
		List<ColumnsDto> retList = new ArrayList<>();
		List<Columns> columns =  findByTableName(properties.getSchema(),tableName);
		for (Columns column : columns) {
			ColumnsDto dto  = new ColumnsDto();
			dto.setType(column.getType());
			dto.setScale(column.getScale());
			dto.setPrecision(column.getPrecision());
			dto.setOrdinal(column.getOrdinal());
			dto.setNullable(column.getNullable());
			dto.setName(column.getName());
			dto.setLength(column.getLength());
			dto.setKey(column.getKey());
			dto.setExtra(column.getExtra());
			dto.setDefValue(column.getDefValue());
			dto.setDataType(column.getDataType());
			dto.setComment(column.getComment());
			columnsWrapper.convert(dto,properties.getTypeConverter());
			retList.add(dto);
		}
		return retList;
	}

	@Override
	public List<Columns> findByTableName(String schema, String tableName) {
		String sql = COLUMN_SQL + "'" + schema + "'" + " AND cs.TABLE_NAME = '" + tableName + "'  ORDER BY cs.ORDINAL_POSITION ";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Columns columns = new Columns();
			JdbcTempUtil.handleRs(columns,rs);
			return columns;
		});
	}
}
