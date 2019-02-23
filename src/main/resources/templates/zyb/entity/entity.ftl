package ${basePackage}.entity;

import com.sendinfo.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
* 实体对象：${comment}
*/
@Data
@Table(name = "${tableName}")
public class ${javacName} extends BaseEntity<Long>{

    /** ~~~~实体属性 */
	<#list columnList as column>
        <#if column.visible >
	/** ${column.comment} */
            <#if column.nullable=='NO'>
    @Column(name="${column.name}",nullable = false)
            <#else >
    @Column(name="${column.name}")
            </#if>
	private  <#if column.scale?? && column.scale gt 0 > java.math.BigDecimal <#else> ${column.javaType} </#if> ${column.javaName};
        </#if>
    </#list>

}