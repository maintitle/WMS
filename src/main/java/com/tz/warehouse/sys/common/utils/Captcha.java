package com.tz.warehouse.sys.common.utils;


import com.wf.captcha.SpecCaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TangZhen on 2022/11/6
 * 生成验证码
 */
@Component
public class Captcha {

    @Bean
    public Map<String, Object> getCode() {
        SpecCaptcha captcha = new SpecCaptcha(117, 36, 4);
        String text = captcha.text();
        HashMap<String, Object> map = new HashMap<>();
        map.put("img", captcha.toBase64());
        map.put("key", text.toLowerCase());
        return map;
    }
}
