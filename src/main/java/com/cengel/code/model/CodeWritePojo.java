package com.cengel.code.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-07 19:57
 **/
@Getter
@Setter
public class CodeWritePojo {
	public CodeWritePojo() {
	}

	public CodeWritePojo(String rootPath, String fileName, String pkg, String datas) {
		this.rootPath = rootPath;
		this.datas = datas;
		this.fileName = fileName;
		this.pkg = pkg;
	}

	private String rootPath; //如果pkg为空，这里的路径是写入文件的父路径 否则则为项目根路径
	private String datas;//待写入文本的长字符
	private String fileName;//包含后缀的文件名
	private String pkg;//包相对路径

}
