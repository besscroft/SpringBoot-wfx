package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Customer;
import com.bess.springboot.wfx.service.CustomerService;
import com.bess.springboot.wfx.util.JWTUtil;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:38
 */
@RestController
@CrossOrigin
@RequestMapping("/customer")
@Api(tags = "商户信息接口")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "商户登录接口", notes = "接收account和password进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录账户", required = true, type = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, type = "String")
    })
    public ResultVO login(String username, String password) {
        Customer customer = customerService.login(username, password);
        if (customer != null) {
            // 登录成功后生成token
            String token = JWTUtil.encrypt(customer.getLoginName(), customer.getCustomerId());
            return new ResultVO(0,"登录成功",token);
        } else {
            // 登录失败
            return new ResultVO(1,"登录失败",null);
        }
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "用户信息查询接口", notes = "用户登录后进行个人信息查询的接口（不返回密码等敏感信息）")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getInfo(@RequestHeader(required = true) String token){
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String name = jws.getBody().getSubject();
        Customer customer = customerService.getCustomerByLoginName(name);
        if (customer != null) {
            return new ResultVO(0,"查询成功",customer);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }
}
