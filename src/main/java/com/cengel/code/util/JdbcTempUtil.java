package com.cengel.code.util;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author zhz
 * @Time 2018/8/2 - 17:56
 * @Version V1.0
 **/
public class JdbcTempUtil {

	public static void handleRs(Object tables,ResultSet rs){
		for (Field field : tables.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				field.setAccessible(true);
				try {
					String tableName = field.getAnnotation(Column.class).name();
					if (Date.class.isAssignableFrom(field.getType())) {
						field.set(tables, rs.getDate(tableName));
					} else if (Integer.class.isAssignableFrom(field.getType())) {
						field.set(tables,rs.getInt(tableName));
					} else if (Long.class.isAssignableFrom(field.getType())) {
						field.set(tables,rs.getLong(tableName));
					}else if (Double.class.isAssignableFrom(field.getType())){
						field.setDouble(tables,rs.getDouble(tableName));
					}else if (BigDecimal.class.isAssignableFrom(field.getType())){
						field.set(tables,rs.getBigDecimal(tableName));
					}else {
						field.set(tables, rs.getObject(field.getAnnotation(Column.class).name()));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void setFiledVal(Field field,Object o){

	}
}
