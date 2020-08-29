package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Customer;
import com.bess.springboot.wfx.pojo.Memeber;
import com.bess.springboot.wfx.service.MemeberService;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 11:03
 */
@RestController
@CrossOrigin
@RequestMapping("/memeber")
@Api("自媒体信息接口")
public class MemeberController {

    @Resource
    private MemeberService memeberService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "自媒体登录接口", notes = "接收account和password进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录账户", required = true, type = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, type = "String")
    })
    public ResultVO login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        Memeber memeber = memeberService.login(username, password);
        if (memeber != null) {
            // 登录成功后生成token
            String token = Jwts.builder()
                    .setSubject(memeber.getName()) // 设置商户信息
                    .setId(memeber.getMemeberId())    // 设置商户id
                    .setIssuedAt(new Date()) // 设置token创建时间
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 设置过期时间
                    .signWith(SignatureAlgorithm.HS256, "fadj@Jq4$fka")  // 设置加密方式和密码
                    .compact();
            return new ResultVO(0,"登录成功",token);
        } else {
            // 登录失败
            return new ResultVO(1,"登录失败",null);
        }
    }
}
