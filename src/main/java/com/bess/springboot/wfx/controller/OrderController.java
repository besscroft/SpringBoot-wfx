package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.config.MyConfig;
import com.bess.springboot.wfx.pojo.*;
import com.bess.springboot.wfx.service.AddressService;
import com.bess.springboot.wfx.service.OrderService;
import com.bess.springboot.wfx.util.JWTUtil;
import com.bess.springboot.wfx.vo.ResultGetVO;
import com.bess.springboot.wfx.vo.ResultVO;
import com.github.wxpay.sdk.WXPay;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 11:45
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(tags = "订单信息接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private AddressService addressService;

    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @ApiOperation(value = "订单信息查询接口" , notes = "查询全部订单信息的接口，需要分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultGetVO list(int size, int current, @RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            List<Order> orders = orderService.listOrderByCustomerId(customerId, start, size);
            if (orders != null) {
                int count = orderService.getCount(customerId, null);
                return new ResultGetVO(0, "查询成功", count, orders);
            } else {
                return new ResultGetVO(1, "查询失败", 0, null);
            }
        } else {
            return new ResultGetVO(1, "查询失败，权限校验未通过", 0, null);
        }
    }

    @RequestMapping(value = "/liststate", method = RequestMethod.GET)
    @ApiOperation(value = "订单信息查询接口" , notes = "根据订单状态查询订单信息的接口，需要分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
            @ApiImplicitParam(name = "state", value = "订单状态", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultGetVO listOrderState(int size,int current,int state,@RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            List<Order> orders = orderService.listOrderByState(customerId, state, start, size);
            if (orders != null) {
                int count = orderService.getCountByState(customerId, state);
                return new ResultGetVO(0, "查询成功", count, orders);
            } else {
                return new ResultGetVO(1, "查询失败", 0, null);
            }
        } else {
            return new ResultGetVO(1, "查询失败，权限校验未通过", 0, null);
        }
    }

    @RequestMapping(value = "/listmemeber", method = RequestMethod.GET)
    @ApiOperation(value = "订单信息查询接口" , notes = "查询全部自媒体用户订单信息的接口，需要分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultGetVO listMemeber(int size,int current,@RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memeberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            List<Order> orders = orderService.getOrderByMemeber(memeberId, start, size);
            if (orders != null) {
                int count = orderService.getCount(null, memeberId);
                return new ResultGetVO(0, "查询成功", count, orders);
            } else {
                return new ResultGetVO(1, "查询失败", 0, null);
            }
        } else {
            return new ResultGetVO(1, "查询失败，权限校验未通过", 0, null);
        }
    }

    @RequestMapping(value = "/updatestate", method = RequestMethod.POST)
    @ApiOperation(value = "订单状态修改接口" , notes = "修改订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, type = "String"),
            @ApiImplicitParam(name = "state", value = "订单状态", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO updateState(String orderId,int state,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            boolean b = orderService.updateOrderState(orderId, state);
            if (b) {
                return new ResultVO(0, "更新成功", null);
            } else {
                return new ResultVO(1, "更新失败", null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/updatememeber", method = RequestMethod.POST)
    @ApiOperation(value = "订单状态修改接口" , notes = "自媒体用户修改订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, type = "String"),
            @ApiImplicitParam(name = "state", value = "订单状态", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO updateMemeberState(String orderId,int state,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer) && (state == 0 || state == 3)) {
            boolean b = orderService.updateOrderState(orderId, state);
            if (b) {
                return new ResultVO(0, "更新成功", null);
            } else {
                return new ResultVO(1, "更新失败", null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/insertorder", method = RequestMethod.POST)
    @ApiOperation(value = "录单接口" , notes = "自媒体用户录单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "buyerPhone", value = "买家电话", required = true, type = "String"),
            @ApiImplicitParam(name = "buyNum", value = "购买数量", required = true, type = "int"),
            @ApiImplicitParam(name = "province", value = "省份", required = true, type = "String"),
            @ApiImplicitParam(name = "city", value = "市", required = true, type = "String"),
            @ApiImplicitParam(name = "region", value = "区", required = true, type = "String"),
            @ApiImplicitParam(name = "buyerReamrk", value = "留言", required = true, type = "String"),
            @ApiImplicitParam(name = "buyerName", value = "买家姓名", required = true, type = "String"),
            @ApiImplicitParam(name = "address", value = "详细状态", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO insertOrderByMemeber(String goodId,String buyerPhone,int buyNum,String province,String city,String region,String buyerReamrk,String buyerName,String address,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memeberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            Province province1 = addressService.getProvince(province);
            City city1 = addressService.getCity(city);
            Region region1 = addressService.getRegion(region);
            Order order = new Order(0, UUID.randomUUID().toString().replace("-",""),buyerPhone,goodId,new Date(),"123",0,buyNum,province1.getProvinceName(),city1.getCityName(),region1.getRegionName(),buyerReamrk,1,buyerName,new GoodSku("sku41700"),new Memeber(memeberId),address,"sf20200904","等待卖家发货","顺丰速运",0,"备注","审核员1",new Date(),"192.168.0.1",new Date(),"1",0,0,"新订单");
            boolean b = orderService.insertOrder(order);
            if (b) {
                return new ResultVO(0, "录单成功", null);
            } else {
                return new ResultVO(1, "录单失败", null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ApiOperation(value = "订单状态修改接口" , notes = "自媒体用户修改订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO pay(String orderId,@RequestHeader(required = true) String token) throws Exception {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memeberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            String goodName = orderService.getOrderGoodNameByOrderIdAndMemeberId(memeberId, orderId);
            if (goodName != null) {
                //2.向微信平台发起支付请求，获取支付短连接
                WXPay wxPay = new WXPay(new MyConfig());
                Map<String, String> data = new HashMap<>();
                data.put("body", goodName);
                data.put("out_trade_no", orderId);
                data.put("device_info", "");
                data.put("fee_type", "CNY");
                data.put("total_fee", "1");
                data.put("spbill_create_ip", "123.12.12.123");
                //付款状态的回调接口
                data.put("notify_url", "http://poken.free.idcfengye.com/pay/callback");
                data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
                data.put("product_id", "12");
                //发送支付请求，resp就是微信支付平台的返回的数据（包含支付链接）
                Map<String, String> resp = wxPay.unifiedOrder(data);
                String payUrl = resp.get("code_url");
                System.out.println("payUrl:" + payUrl );
                //3. 将支付短连接传递到前端
                return new ResultVO(0,payUrl,orderId);
            } else {
                return new ResultVO(1,"失败",null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }
}
