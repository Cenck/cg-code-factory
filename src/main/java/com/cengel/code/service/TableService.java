package com.cengel.code.service;

import com.cengel.code.entity.Tables;
import com.cengel.code.model.TablesDto;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author zhz
 * @Time 2018/7/26 - 10:41
 * @Version V1.0
 **/
public interface TableService {

	TablesDto getDto(String name);

	Tables findByName(String schema, String name);

	List<Tables> findByNames(String schema, String... tables);

}
