自定义注释
@header 请求头
	参数： key notes 与RequestMapping结合使用
@param 请求头
	参数： key notes value Required
@choice 只生成选中的属性 空格分隔
	例如: id name age
@exclude 排除属性 空格分割
	例如: name age
@default 默认值 可加在类属性上
@contextPath 加在类注释上（单独生成某个模块或者项目可在全局类中指定）




以下是注解使用
路径用value不要用name
@PostMapping(value="refresh", headers= {"authorization"})
当没有headers时可以使用
@PostMapping("refresh")

如果类名上添加
@RequestMapping(value="/admin/weixin/api", headers= {"authorization"})
表示该类下的所有接口头部header必需包含authorization