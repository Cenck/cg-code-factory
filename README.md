# cg-code-factory


#### 项目介绍
基于springboot+jdbc+freemarker实现的代码生成工具

*注：暂未提供对除mysql以外的其他数据库的支持*

#### 简要说明 
1. 基于spring-boot和jdbc查询mysql information_schema表及字段相关信息
2. 根据yml配置参数、静态变量参数和ftl模板，将db表信息渲染成string
3. 将上述string 生成用buffer io写入本地磁盘


#### 使用说明
1. 配置CodeGenSpringBoot中static块中的表名及其他信息
2. application.yml配置ftl模板信息，jdbc信息及其他配置
3. 修改.ftl模板样式，将决定导出的dao service等的.java,.xml等文件样式
4. 启动CodeGenSpringBoot.main方法
5. 在src同级生成_products目录即为生成的代码


