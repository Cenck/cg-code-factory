package com.cengel.code.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Entity(name = "information_schema.TABLES")
public class Tables implements Serializable {

    @Id
    @Column(name = "table_name") //表名
    private String tableName;


    @Column(name = "TABLE_CATALOG")
    private String catalog;
    @Column(name = "table_schema")
    private String schema;

    @Column(name = "table_comment") //备注
    private String comment;

    @Column(name = "table_rows") //记录总行数
    private Integer rows;

    @Column(name = "create_time") //记录总行数
    private Date createTime;
    @OrderBy(" desc")
    @Column(name = "update_time") //记录总行数
    private Date updateTime;

}
