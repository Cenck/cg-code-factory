package com.cengel.code.util;

import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 字断切换
 * @author zhz
 * @version V1.0
 * @create 2018-03-03 14:41
 **/
public class TabColStrUtil {

    /**
     * @Name: java字段名转换成tableName
     * @Description:
     * @author zhz
     * @time 2018/3/3 - 14:43
     **/
    public static final String jfiled2tabName(String javaField){
        if (!StrUtil.isNotEmpty(javaField)){
            return null;
        }
        char [] charArr  = javaField.toCharArray();
        StringBuilder sb  = new StringBuilder(javaField);
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (Character.isUpperCase(c)){
                sb.insert(i,"_");
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String tabName2jfiled(String name) {
        String[] array = name.toLowerCase().split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            // if (i == 0 && array[0].length() <= 2) {
            // continue;
            // }
            builder.append(firstUpperCase(array[i]));
        }
        return builder.toString();
    }

    public static String firstLowerCase(String str) {
        return StringUtils.lowerCase(str.substring(0, 1)) + str.substring(1);
    }

    public static String firstUpperCase(String str) {
        return StringUtils.upperCase(str.substring(0, 1)) + str.substring(1);
    }
}
