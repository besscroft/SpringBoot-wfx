package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Memeber;
import com.bess.springboot.wfx.service.MemeberService;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 11:03
 */
@RestController
@CrossOrigin
@RequestMapping("/memeber")
@Api(tags = "自媒体信息接口")
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
        Memeber memeber = memeberService.login(username, password);
        if (memeber != null) {
            // 登录成功后生成token
            String token = Jwts.builder()
                    .setSubject(memeber.getAccount()) // 设置商户信息
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

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "用户信息查询接口", notes = "用户登录后进行个人信息查询的接口（不返回密码等敏感信息）")
    public ResultVO getInfo(@RequestHeader(required = true) String token){
        // 验证token
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        // 获取解析的token中的用户名、id等
        String name = jws.getBody().getSubject();
        System.out.println("解析后的用户名："+name);
        Memeber memeber = memeberService.getMemeberByLoginName(name);
        if (memeber != null) {
            return new ResultVO(0,"查询成功",memeber);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }
}
