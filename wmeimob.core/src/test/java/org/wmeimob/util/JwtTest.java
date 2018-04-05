package org.wmeimob.util;

import java.text.SimpleDateFormat;

import com.wmeimob.util.JwtTokenUtil;
import com.wmeimob.util.JwtUser;

import junit.framework.TestCase;

/**
 * jwt生成token测试
 * @author zJun
 * @date 2018年3月25日 下午3:19:42
 */
public class JwtTest extends TestCase {

    public void testJwt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ID = "1002";
        String USER_NAME = "admin";
        JwtTokenUtil util = new JwtTokenUtil();
        util.setHeader("authorization");
        util.setSecret("adsaqw221321#44328sdf");
        util.setExpiration(604800L);
        JwtUser user = new JwtUser(ID, USER_NAME);
        String token = util.generateToken(user);
        System.out.println("创建时间:\t" + sdf.format(util.getCreatedDateFromToken(token)));
        System.out.println("过期时间:\t" + sdf.format(util.getExpirationDateFromToken(token)));
        System.out.println("生成token:\t" + token);
        assertEquals(ID, util.getIdFromToken(token));
        assertEquals(USER_NAME, util.getUsernameFromToken(token));
    }
}
