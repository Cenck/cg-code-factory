package com.cengel.code.task;

import com.cengel.code.entity.Columns;

import java.util.*;

public class TypeConverter {


	private List<Types>	typesMap	= new ArrayList<Types>();

	private Types		UNKOWN		= new Types("unkown", "unkown", new HashSet<String>());

	public TypeConverter(Map<String, String> settings) {
		for (Map.Entry<String, String> entry : settings.entrySet()) {
			String[] array = entry.getKey().split("-");
			String[] dbTypes = entry.getValue().split(",");
			String sql = "";
			for (int i = 0; i < dbTypes.length; i++) {
				sql += dbTypes[i] + ",";
			}
			this.typesMap.add(new Types(array[0], array[1], new HashSet<String>(Arrays.asList(dbTypes))));
		}
	}
	public String toJavaType(String dataType) {
		Types types = this.matcher(dataType);
		return types.getJavaType();
	}

	public String toJdbcType(String dataType) {
		Types types = this.matcher(dataType);
		return types.getJdbcType();
	}
	public String toJavaType(Columns Columns) {
		Types types = this.matcher(Columns);
		return types.getJavaType();
	}

	public String toJdbcType(Columns Columns) {
		Types types = this.matcher(Columns);
		return types.getJdbcType();
	}

	private Types matcher(Columns Columns) {
		for (Types types : typesMap) {
			if (types.matcher(Columns)) { return types; }
		}
		return UNKOWN;
	}

	private Types matcher(String dataType) {
		for (Types types : typesMap) {
			if (types.matcher(dataType)) { return types; }
		}
		return UNKOWN;
	}
	private static class Types {

		private String		javaType;
		private String		jdbcType;
		private Set<String>	dbTypes;

		public Types(String javaType, String jdbcType, Set<String> dbTypes) {
			this.javaType = javaType;
			this.jdbcType = jdbcType;
			this.dbTypes = dbTypes;
		}

		public boolean matcher(Columns Columns) {
			if (this.dbTypes.isEmpty()) { return true; }
			boolean match = this.dbTypes.contains(Columns.getDataType().toUpperCase());
			if (match) {
				// 小数测试
			}
			return match;
		}

		public boolean matcher(String dataType) {
			if (this.dbTypes.isEmpty()) { return true; }
			boolean match = this.dbTypes.contains(dataType.toUpperCase());
			if (match) {
				// 小数测试
			}
			return match;
		}

		public String getJavaType() {
			return this.javaType;
		}

		public String getJdbcType() {
			return this.jdbcType;
		}
	}
}
