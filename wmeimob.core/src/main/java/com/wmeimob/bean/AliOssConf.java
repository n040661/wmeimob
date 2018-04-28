package com.wmeimob.bean;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 阿里云OSS配置
 * @author zJun
 * @date 2018年4月28日 下午4:58:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliOssConf {

    /** endpoint */
    private String endpoint;
    /** accessId */
    private String accessId;
    /** accessKey */
    private String accessKey;
    /** bucket */
    private String bucket;
    /** 目录，必须斜杠/结尾 */
    private String dir;
    
    
    /**
     * 返回阿里云OSS文件上传凭证
     * @param conf
     * @return
     * @throws UnsupportedEncodingException
     * @author zJun
     * @date 2018年4月28日 下午5:06:29
     */
    public Map<String, String> aliPolicy()
            throws UnsupportedEncodingException {
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, endpoint, accessKey);
        long expireTime = 600;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);
        
        Map<String, String> respMap = new LinkedHashMap<String, String>(6);
        respMap.put("accessid", endpoint);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }
    
    /**
     * 返回阿里云OSS文件上传凭证
     * @param dir 子目录，必须已斜杠/结尾。
     * @return
     * @throws UnsupportedEncodingException
     * @author zJun
     * @date 2018年4月28日 下午5:21:49
     */
    public Map<String, String> aliPolicy(String subdirectory) throws UnsupportedEncodingException {
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, endpoint, accessKey);
        long expireTime = 600;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir + subdirectory);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>(6);
        respMap.put("accessid", endpoint);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }
}
