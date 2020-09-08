package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.service.OrderService;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/8 11:00
 */
@RestController
@CrossOrigin
@RequestMapping("/pay")
@Api(tags = "微信支付接口")
public class PayCallbackController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ApiOperation(value = "微信回调接口" , notes = "微信支付之后的回调接口")
    public String callback(HttpServletRequest request) throws Exception {
        System.out.println("-------------callback");
        //1.接收微信返回的支付状态
        ServletInputStream inputStream = request.getInputStream();
        byte[] bs = new byte[1024];
        int len = -1;
        StringBuilder builder = new StringBuilder();
        while((len = inputStream.read(bs))!=-1){
            builder.append( new String(bs,0,len) );
        }
        String res = builder.toString();
        System.out.println(res);
        Map<String, String> map = WXPayUtil.xmlToMap(res);
        if(map.get("result_code").equalsIgnoreCase("success")){
            //2.修改数据库订单状态为已支付
            String orderId = map.get("out_trade_no");
            boolean b = orderService.updateIsFK(orderId);
            if (b) {
                //3. 推送消息到付款页面
                WebSocket.sendMessage(orderId,"success");
                //3.响应微信平台
                return "<xml>" +
                        "   <return_code><![CDATA["+map.get("return_code")+"]]></return_code>" +
                        "   <return_msg><![CDATA[OK]]></return_msg>" +
                        "   <appid><![CDATA["+map.get("appid")+"]]></appid>" +
                        "   <mch_id><![CDATA["+map.get("mch_id")+"]]></mch_id>" +
                        "   <nonce_str><![CDATA["+map.get("nonce_str")+"]]></nonce_str>" +
                        "   <openid><![CDATA["+map.get("openid")+"]]></openid>" +
                        "   <sign><![CDATA["+map.get("sign")+"]]></sign>" +
                        "   <result_code><![CDATA[SUCCESS]]></result_code>" +
                        "  <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
                        "   <trade_type><![CDATA["+map.get("trade_type")+"]]></trade_type>" +
                        "</xml>";
            } else {
                return "";
            }
        }
        return "";
    }

}
