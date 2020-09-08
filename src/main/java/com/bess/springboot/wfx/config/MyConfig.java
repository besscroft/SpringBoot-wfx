package com.bess.springboot.wfx.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/8 10:13
 */
public class MyConfig implements WXPayConfig {
    /**
     * 返回给商户的appId
     * @return
     */
    @Override
    public String getAppID() {
        return "wx632c8f211f8122c6";
    }

    /**
     * 返回商户编号
     * @return
     */
    @Override
    public String getMchID() {
        return "1497984412";
    }

    /**
     * 返回商户key
     * @return
     */
    @Override
    public String getKey() {
        return "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
