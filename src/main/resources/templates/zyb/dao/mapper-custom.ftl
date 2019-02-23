<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${javacName}Mapper">


<#--通用entity-where条件（全）-->
    <sql id="select_entity_condition" >
<#list columnList as column>
    <#if !column.whereHide>
        <#if column.dataType=='TIMESTAMP'>
        <if test="entity.${column.javaName} != null ">
            AND ${column.name} = ${"#"}{entity.${column.javaName},jdbcType=${column.dataType}}
        </if>
        <#elseif column.dataType=='VARCHAR' && !column.name?ends_with("ID")>
        <if test="entity.${column.javaName} != null<#if column.javaType> and entity.${column.javaName} != ''</#if>">
            AND ${column.name} LIKE CONCAT(CONCAT('%',trim( ${"#"}{entity.${column.javaName},jdbcType=${column.dataType}}) ), '%')
        </if>
        <#else>
        <if test="entity.${column.javaName} != null<#if column.javaType> and entity.${column.javaName} != ''</#if>">
            AND ${column.name} = ${"#"}{entity.${column.javaName},jdbcType=${column.dataType}}
        </if>
        </#if>
    </#if>
</#list>
    </sql>


</mapper>