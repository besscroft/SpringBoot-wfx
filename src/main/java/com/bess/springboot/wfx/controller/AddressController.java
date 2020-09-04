package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.City;
import com.bess.springboot.wfx.pojo.Province;
import com.bess.springboot.wfx.pojo.Region;
import com.bess.springboot.wfx.service.AddressService;
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
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 10:48
 */
@RestController
@CrossOrigin
@RequestMapping("/address")
@Api(tags = "地址查询接口")
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping(value = "/province", method = RequestMethod.GET)
    @ApiOperation(value = "省份查询接口", notes = "用户查询所有省份的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getProvince(@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            List<Province> provinces = addressService.listProvince();
            if (provinces != null) {
                return new ResultVO(0,"查询成功",provinces);
            } else {
                return new ResultVO(1,"查询失败",null);
            }
        } else {
            return new ResultVO(1,"查询失败，权限校验未通过",null);
        }
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ApiOperation(value = "城市查询接口", notes = "用户查询所有城市的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceId", value = "省份id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getCity(String provinceId,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            List<City> cities = addressService.listCityByProvince(provinceId);
            if (cities != null) {
                return new ResultVO(0,"查询成功",cities);
            } else {
                return new ResultVO(1,"查询失败",null);
            }
        } else {
            return new ResultVO(1,"查询失败，权限校验未通过",null);
        }
    }

    @RequestMapping(value = "/region", method = RequestMethod.GET)
    @ApiOperation(value = "市区查询接口", notes = "用户查询所有市区的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityId", value = "城市id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getRegion(String cityId,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            List<Region> regions = addressService.listRegionByCity(cityId);
            if (regions != null) {
                return new ResultVO(0,"查询成功",regions);
            } else {
                return new ResultVO(1,"查询失败",null);
            }
        } else {
            return new ResultVO(1,"查询失败，权限校验未通过",null);
        }
    }
}
