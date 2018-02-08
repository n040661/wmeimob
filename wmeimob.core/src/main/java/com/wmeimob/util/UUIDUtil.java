package com.wmeimob.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by ydd on 2014/4/29.
 */
public class UUIDUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    
    public static String generateOrderCode(String code){
    	return code + sdf.format(new Date())+ RandomUtils.generateStringNUM(4);
    }
    
    public static void main(String[] args) {
    	System.out.println(generateUUID());
    	System.out.println(generateOrderCode("PP"));
	}
}
