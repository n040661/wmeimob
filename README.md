# 自定义注释
## @header 参数名 备注
需与注解@RequestMapping结合使用，也就是说如果接口必需包含指定的头部header注解必需添加headers属性

## @param 参数名 备注 默认值 是否必需
    /**
     * 登录
     * @param username 登录账号 410919244
     * @param password 登录密码 123456
     * @return Token
     * @author zJun
     * @date 2018年3月27日 上午10:10:50
     */
    @PostMapping("token")
    public ResponseEntity<Token> auth(String username, String password) {
        return ResponseEntity.ok(token);
    }

必需和参数对应，参数可以是对象。除参数名其它都可为空，建议至少加上备注。默认值只是为了方便测试调用接口用的

## @choice 选中属性 空格分隔
	/**
	 * 新增系统用户
	 * @param user
	 * @choice fullName account password
	 * @return 返回新增后系统用户ID
	 * @author zJun
	 * @date 2018年7月3日 下午4:44:16
	 */
	@PostMapping
	public BigInteger add(AdminUser user) {
		checkAuth();
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		iAdminUserDao.save(user);
		return user.getId(); 
	}
当参数是对象时使用，表示该接口只需要fullName、account、password三个参数

## @exclude 排除属性 空格分割
	/**
	 * 查询UserDTO
	 * @param dto 测试
	 * @exclude cars job
	 * @return UserDTO
	 * @author zJun
	 * @date 2018年7月4日 下午9:25:11
	 */
	@GetMapping("select")
	public UserDTO select(UserDTO dto) {
		return new UserDTO();
	}
当参数是对象时使用，表示该接口排除的字段

## @default 默认值 可加在类属性上
    /**
	 * 性别
	 * @default SIR
	 */
	private Sex sex;
在类中字段上使用，测试接口时会默认给input赋值，Sex为枚举类型

## @contextPath 加在类注释上（单独生成某个模块或者项目可在全局类中指定）
    /**
     * 应用管理
     * @contextPath /admin-sass
     * @author zJun
     * @date 2018年4月26日 下午7:02:23
     */
在Controller类上使用，指定请求上下文。如果生成的文档是单个项目可指定全局contextPat。该注释在同时生成多个模块(maven模块开发)时使用

# 注解使用规范
## 当接口不需要指定header时
    @PostMapping("refresh")

## 当接口需要指定header参数token时
    @PostMapping(value="refresh", headers= {"token"})
    
路径使用value属性，不要使用name属性

# 在Controller类上使用
    @RequestMapping(value="/admin/weixin/api", headers= {"token"})
表示该类下的所有接口头部header必需包含token


# 前端说明
## 项目列表

## 环境列表


# 效果展示

- 请求参数
    <img src="userDTO.png" width="600">
- 返回说明
    <img src="response.png" width="600">
- 环境变量
    <img src="env.png" width="600">


### 注意不要使用循环属性，否则会出现死循环。
比如 A类中有一个属性是A类型。或者A中有B，B中有A这样

## 有事扫码
- 支付宝
    
    <img src="ali.png" width="200">

- 微信
    
    <img src="wechat.png" width="200">

- 发红包
    
    <img src="hongbao.png" width="200">