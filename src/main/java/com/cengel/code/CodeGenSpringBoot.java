package com.cengel.code;

import com.cengel.code.model.ParamContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author zhz
 * @version V1.0
 * @since 2018/12/5 - 10:32
 **/
@Component
@SpringBootApplication
public class CodeGenSpringBoot {

	static {
		ParamContext.startType = "entity";
		ParamContext.webPath = "/order";
		ParamContext.basePackage = "com.??";
		ParamContext.tableName = "order_info";
		File targetClassFile = new File(CodeGenSpringBoot.class.getResource("/").getPath());
		ParamContext.outDir = targetClassFile.getParentFile().getParent() + File.separator + "_products";
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(CodeGenSpringBoot.class).web(WebApplicationType.NONE).run(args);
	}

}
