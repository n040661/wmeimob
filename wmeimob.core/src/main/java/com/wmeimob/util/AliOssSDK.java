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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 阿里云OSS文件上传
 * @author zJun
 * @date 2018年5月2日 下午2:55:49
 */
@AllArgsConstructor
@NoArgsConstructor
public class AliOssSDK {

    private AliOssConf conf;
    
    /**
     * 返回阿里云OSS文件上传凭证
     * @param conf
     * @return
     * @author zJun
     * @date 2018年4月28日 下午5:06:29
     */
    public Map<String, String> aliPolicy() {
        String host = "http://" + this.conf.getBucket() + "." + this.conf.getEndpoint();
        OSSClient client = new OSSClient(this.conf.getEndpoint(), this.conf.getAccessId(), this.conf.getAccessKey());
        long expireTime = 600;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, this.conf.getDir());

        Map<String, String>  respMap = new LinkedHashMap<String, String>(6);
        try {
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            respMap.put("accessid", this.conf.getAccessId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", this.conf.getDir());
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding失败", e);
        }
        return respMap;
    }
    
    /**
     * 返回阿里云OSS文件上传凭证
     * @param dir 子目录，必须已斜杠/结尾。
     * @return
     * @author zJun
     * @date 2018年4月28日 下午5:21:49
     */
    public Map<String, String> aliPolicy(String subdirectory) {
        String dir = this.conf.getDir() + subdirectory;
        String host = "http://" + this.conf.getBucket() + "." + this.conf.getEndpoint();
        OSSClient client = new OSSClient(this.conf.getEndpoint(), this.conf.getAccessId(), this.conf.getAccessKey());
        long expireTime = 600;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        Map<String, String>  respMap = new LinkedHashMap<String, String>(6);
        try {
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            respMap.put("accessid", this.conf.getAccessId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding失败", e);
        }
        return respMap;
    }
}
