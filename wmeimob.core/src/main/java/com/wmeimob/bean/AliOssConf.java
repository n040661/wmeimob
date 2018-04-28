package com.wmeimob.bean;

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
    /** 目录 */
    private String dir;
    
}
