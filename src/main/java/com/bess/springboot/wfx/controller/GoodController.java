package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.service.GoodService;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 15:03
 */
@RestController
@CrossOrigin
@RequestMapping("/good")
@Api(tags = "商品信息接口")
public class GoodController {

    @Resource
    private GoodService goodService;

    @RequestMapping(value = "/listById", method = RequestMethod.GET)
    @ApiOperation(value = "商品信息查询接口" , notes = "根据商户的id查询该商户所有的商品，但是只有在登录后才能查询到，需要token")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO listGoodByCustomerId(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        System.out.println("解析后的id："+customerId);
        List<Good> goods = goodService.listGoodByCustomerId(customerId);
        if (goods != null) {
            return new ResultVO(0,"查询成功",goods);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }
}
