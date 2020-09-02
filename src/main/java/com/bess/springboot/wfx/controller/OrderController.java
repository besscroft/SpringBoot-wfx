package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Order;
import com.bess.springboot.wfx.service.OrderService;
import com.bess.springboot.wfx.util.JWTUtil;
import com.bess.springboot.wfx.vo.ResultGetVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        List<Order> orders = orderService.listOrderByCustomerId(customerId, start, size);
        if (orders != null) {
            int count = orderService.getCount(customerId, null);
            return new ResultGetVO(0,"查询成功",count,orders);
        } else {
            return new ResultGetVO(1,"查询失败",0,null);
        }
    }

//    @RequestMapping(value = "/listorderype", method = RequestMethod.GET)
//    @ApiOperation(value = "订单信息查询接口" , notes = "查询全部”已下单“订单信息的接口，需要分页")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
//            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
//            @ApiImplicitParam(name = "orderType", value = "订单状态", required = true, type = "int"),
//            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
//    })
//    public ResultVO listOrderType(int size,int current,int orderType,@RequestHeader(required = true) String token) {
//        int start = (current - 1) * size;   // 从第几行开始查
//        // 验证token
//        Jws<Claims> jws = JWTUtil.Decrypt(token);
//        // 获取解析的token中的用户名、id等
//        String customerId = jws.getBody().getId();
//        List<Order> orders = orderService.getOrderByOrderType(customerId,start,size,orderType);
//        if (orders != null) {
//            return new ResultVO(0,"查询成功",orders);
//        } else {
//            return new ResultVO(1,"查询失败",null);
//        }
//    }

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
        List<Order> orders = orderService.getOrderByMemeber(memeberId,start,size);
        if (orders != null) {
            int count = orderService.getCount(null, memeberId);
            return new ResultGetVO(0,"查询成功",count,orders);
        } else {
            return new ResultGetVO(1,"查询失败",0,null);
        }
    }
}
