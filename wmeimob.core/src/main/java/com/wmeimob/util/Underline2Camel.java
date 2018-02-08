package com.wmeimob.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰法-下划线互转
 * @author cshaper
 * @since 2015.07.04
 * @version 1.0.0
 */
public class Underline2Camel {
    
    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public String underline2Camel(String line, boolean smallCamel){
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
					: Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public String camel2Underline(String line){
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
    }
    
    /**
     * 首字母大写
     * @author zJun
     * @date 2018年1月10日 下午7:46:43
     */
	public String captureName(String name) {
		 name = name.substring(0, 1).toUpperCase() + name.substring(1);
		 return name;
//		以下代码虽然速度快 有BUG  大写开头的字符串需要判断
//		char[] cs = name.toCharArray();
//		cs[0] -= 32;
//		return String.valueOf(cs);
	}
	
	/**
	 * 下划线命名转驼峰命名
	 * @param field
	 * @return
	 * @author zJun
	 * @date 2018年1月10日 下午7:49:40
	 */
	public String getAndSet(String field){
		return captureName(underline2Camel(field,true));
	}
	
	/**
	 * 数据类型转换
	 * @author zJun
	 * @date 2018年1月10日 下午8:54:52
	 */
	public String dataType(String type){
		switch (type) {
		case "int":
			return "Integer";
		case "varchar":
			return "String";
		case "date":
			return "Date";
		case "datetime":
			return "Date";
		case "timestamp":
			return "Date";
		case "double":
			return "Double";
		case "float":
			return "Float";
		case "bit":
			return "Boolean";
		case "tinyint":
			return "Integer";
		default:
			return "类型缺失";
		}
	}
	
	public String jdbcDataType(String type){
		switch (type) {
		case "int":
			return "INTEGER";
		case "varchar":
			return "VARCHAR";
		case "datetime":
			return "DATE";
		case "date":
			return "DATE";
		case "timestamp":
			return "TIMESTAMP";
		case "double":
			return "DOUBLE";
		case "float":
			return "FLOAT";
		case "bit":
			return "BIT";
		case "tinyint":
			return "TINYINT";
		default:
			System.out.println("类型缺失:"+type);
			return "类型缺失";
		}
	}
	
	public String centerLine(String str){
		return str.replaceAll("_", "-");
	}
    
    public static void main(String[] args) {
    	Underline2Camel uc = new Underline2Camel();
        String line="I_HAVE_AN_IPANG3_PIG";
        String camel=uc.underline2Camel(line,true);
        System.out.println(camel);
        System.out.println(uc.camel2Underline(camel));
        System.out.println("set"+uc.captureName(camel));
        System.out.println("get"+uc.getAndSet("username"));
        System.out.println(uc.captureName("Adsa"));
    }
}