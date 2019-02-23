//import com.cengel.code.CodeStarter;
//import com.cengel.code.model.config.DefaultConfig;
//import com.cengel.starbucks.annotation.Description;
//
//import java.io.File;
//
//
///**
// * @Title:
// * @Description:
// * @Author zhz
// * @Time 2018/8/2 - 16:57
// * @Version V1.0
// **/
//public class TestCodeGen {
//
//	@Description("启动类型，匹配yaml中summer.codefactory.project.??")
//	private final static String START_TYPE = "templates"; //代码生成器中模板存放根路径
//
//	//java包名
//	private final static String BASE_PACKAGE = "com.cengel.cglab.test";
//
//	//数据库表名，jdbc在summer.yml中配置，多个表之间用逗号分开
//	private final static String TABLE_NAME = "test_employee";//Service
//	// .html或ftl文件将被置于/templates/employee目录下
//	private final static String WEB_PATH = "/employee"; //ftl路径
//
//	public static void main(String[] args) {
//		/** 写入项目到根路径下 */
//		File targetClassFile = new File(CodeStarter.class.getResource("/").getPath());
//		String rootPath = targetClassFile.getParentFile().getParent() + File.separator + "_products";
//		DefaultConfig.setBasePackage(BASE_PACKAGE);
//		DefaultConfig.setTableName(TABLE_NAME);
//		DefaultConfig.setOutPath(rootPath);
//		DefaultConfig.setWebPath(WEB_PATH);
//		DefaultConfig.setStartType(START_TYPE);
//		CodeStarter.run();
//	}
//}
