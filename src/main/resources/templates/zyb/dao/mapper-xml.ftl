<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${javacName}Mapper">


    <!-- 查询字段 -->
    <sql id="selectFieldSQL">
        SELECT
		<#list columnList as column>
            ${column.name} ${column.javaName}<#if column_has_next>,</#if>
        </#list>
    </sql>


    <resultMap id="BaseResultMap" type="${basePackage}.entity.${javacName}">
	   <#list columnList as column >
           <#if column.name == 'ID'>
                <id column="id" jdbcType="VARCHAR" property="id" />
           <#else>
                <result column="${column.name}" jdbcType="${column.dataType}" property="${column.javaName}" />
           </#if>
       </#list>
    </resultMap>



</mapper>