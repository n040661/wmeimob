package com.wmeimob.util;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wmeimob.bean.AliOssConf;


/**
 * 阿里云OSS文件上传获取凭证
 * @author zJun
 * @date 2018年4月28日 下午4:50:22
 */
public class AliOssPolicyUtil {
    
    /**
     * 返回阿里云OSS文件上传凭证
     * @param conf
     * @return
     * @throws UnsupportedEncodingException
     * @author zJun
     * @date 2018年4月28日 下午5:06:29
     */
    public static Map<String, String> aliPolicy(AliOssConf conf)
            throws UnsupportedEncodingException {
        String host = "http://" + conf.getBucket() + "." + conf.getEndpoint();
        OSSClient client = new OSSClient(conf.getEndpoint(), conf.getAccessId(), conf.getAccessKey());
        long expireTime = 600;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, conf.getDir());

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);
        
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", conf.getAccessId());
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", conf.getDir());
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }
}
