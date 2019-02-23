package com.cengel.code.model;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 11:57
 **/
public class ParamContext {

	public static String outDir;
	public static String basePackage;
	/** //表名，允许多张表，以英文,号隔开 */
	public static String tableName;
	/** //web资源：ftl,html的路径 */
	public static String webPath;
	/** //执行类型，生成所有或仅更新entity */
	public static String startType;

}
