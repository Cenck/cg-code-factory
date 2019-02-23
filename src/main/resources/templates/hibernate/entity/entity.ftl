package ${basePackage}.entity;

import com.cengel.starbucks.model.entity.BEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
* 实体对象：${comment}
*/
@Getter
@Setter
@Entity(name = "${tableName}")
public class ${javacName} implements BEntity<Integer> {

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

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /** 删除标志 0:未删除 1:已删除 */
    @Column(name="DELETED",columnDefinition = "0")
    private   Boolean  deleted;
}