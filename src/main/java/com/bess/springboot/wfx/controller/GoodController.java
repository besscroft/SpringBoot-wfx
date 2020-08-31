package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.pojo.GoodType;
import com.bess.springboot.wfx.service.GoodService;
import com.bess.springboot.wfx.util.RandomId;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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

    @RequestMapping(value = "/listbyid", method = RequestMethod.GET)
    @ApiOperation(value = "商品信息查询接口" , notes = "根据商户的id查询该商户所有的商品，但是只有在登录后才能查询到，需要token")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
        @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
        @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listGoodByCustomerId(int size,int current,@RequestHeader(required = true) String token) {
        System.out.println("size:" + size + "current:" + current);
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        System.out.println("解析后的id："+customerId);
        List<Good> goods = goodService.listGoodByCustomerId(customerId,start,size);
        if (goods != null) {
            return new ResultVO(0,"查询成功",goods);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.PUT)
    @ApiOperation(value = "商品添加接口", notes = "根据提交的数据进行商品的添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodName", value = "商品名称", required = true, type = "String"),
            @ApiImplicitParam(name = "goodPic1", value = "商品图片", required = true, type = "String"),
            @ApiImplicitParam(name = "promoteDesc", value = "推广说明", required = true, type = "String"),
            @ApiImplicitParam(name = "copyDesc", value = "文案说明", required = true, type = "String"),
            @ApiImplicitParam(name = "forwardLink", value = "跳转链接", required = true, type = "String"),
            @ApiImplicitParam(name = "typeId", value = "商品分类", required = true, type = "String"),
            @ApiImplicitParam(name = "website", value = "产品网址", required = true, type = "String"),
            @ApiImplicitParam(name = "kfqq", value = "客服QQ", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO insertGood(String goodName,String goodPic1,String promoteDesc,String copyDesc,String forwardLink,String typeId,String website,String kfqq,@RequestHeader(required = true) String token) {
        System.out.println(goodName);
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        Good good = new Good(RandomId.getNum(8),goodName,customerId,goodPic1,"","",promoteDesc,"wenan"+RandomId.getNum(10),copyDesc,forwardLink,2,new GoodType(typeId,"","","",0,""),"tags",0,new Date(),0,0,new Date(),new Date(),"","",0,website,kfqq);
        boolean b = goodService.insertGood(good);
        System.out.println(b);
        if (b) {
            return new ResultVO(0,"添加成功",null);
        } else {
            return new ResultVO(1,"添加失败",null);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "商品删除接口", notes = "根据提交的商品id进行商品的删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO deleteGood(String goodId,@RequestHeader(required = true) String token){
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        boolean b = goodService.deleteGood(goodId);
        System.out.println(b);
        if (b) {
            return new ResultVO(0,"删除成功",null);
        } else {
            return new ResultVO(1,"删除失败",null);
        }
    }
}
