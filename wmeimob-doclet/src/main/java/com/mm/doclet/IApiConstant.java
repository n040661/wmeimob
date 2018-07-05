package com.mm.doclet;

/**
 * 常量
 * @author zJun
 * @date 2018年6月27日 上午10:30:50
 */
public class IApiConstant {

	/**
	 * 表示：不适用
	 */
	public final static String NA = "N/A";
	
	/**
	 * 参数标签
	 */
	public final static String DOC_PARAM = "@param";
	
	/**
	 * 多个模块或者项目一起生成需要单独给每个controller加上该标签
	 */
	public final static String DOC_CONTEXT_PATH = "@contextPath";
	
	/**
	 * 作者标签
	 */
	public final static String DOC_AUTHOR = "@author";
	
	/**
	 * 时间标签
	 */
	public final static String DOC_DATE = "@date";
	
	/**
	 * see参考标签
	 */
	public final static String DOC_SEE = "@see";
	
	/**
	 * 返回标签
	 */
	public final static String DOC_RETURN = "@return";
	
	/**
	 * 请求header标签
	 */
	public final static String DOC_HEADER = "@header";
	
	/**
	 * 属性默认值标签
	 */
	public final static String DOC_DEFAULT = "@default";
	
	/**
	 * 选中属性标签
	 */
	public final static String DOC_CHOICE = "@choice";
	
	/**
	 * 排除属性标签
	 */
	public final static String DOC_EXCLUDE = "@exclude";
	
	
	public final static String TYPE_ENUM = "Enum";
	public final static String TYPE_ARRAY = "Array";
	public final static String TYPE_STRING = "String";
	public final static String TYPE_OBJECT = "Object";
	
	public final static String GET = "GET";
	public final static String POST = "POST";
	public final static String PUT = "PUT";
	public final static String DELETE = "DELETE";
	
	public final static String REQUEST_MAPPING = "RequestMapping";
	public final static String GET_MAPPING = "GetMapping";
	public final static String POST_MAPPING = "PostMapping";
	public final static String PUT_MAPPING = "PutMapping";
	public final static String DELETE_MAPPING = "DeleteMapping";
	public final static String REQUEST_BODY = "RequestBody";
	
}
