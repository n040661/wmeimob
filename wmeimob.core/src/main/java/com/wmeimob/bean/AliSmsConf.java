package com.wmeimob.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliSmsConf {

    /** accessId */
    private String accessId;
    /** accessKey */
    private String accessKey;
    /** 短信签名 */
    private String signName;
    
}
