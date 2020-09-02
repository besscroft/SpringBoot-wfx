package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.GoodCopy;
import com.bess.springboot.wfx.service.GoodCopyService;
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
 * @DateTime 2020/9/1 21:16
 */
@RestController
@CrossOrigin
@RequestMapping("/goodcopy")
@Api(tags = "商品文案信息接口")
public class GoodCopyController {

    @Resource
    private GoodCopyService goodCopyService;

    @RequestMapping("/list")
    @ApiOperation(value = "商品文案信息查询接口" , notes = "查询商品文案信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO list(int size,int current,@RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        List<GoodCopy> goodCopies = goodCopyService.listGoodCopyByPage(customerId,start,size);
        if (goodCopies != null) {
            return new ResultVO(0,"查询成功",goodCopies);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.PUT)
    @ApiOperation(value = "文案添加接口", notes = "根据提交的数据进行文案的添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "copyTitle", value = "文案标题", required = true, type = "String"),
            @ApiImplicitParam(name = "copyLink", value = "文案链接", required = true, type = "String"),
            @ApiImplicitParam(name = "copyContent", value = "文案内容", required = true, type = "String"),
            @ApiImplicitParam(name = "typeId", value = "文案类型", required = true, type = "int"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO insertGoodCopy(String copyTitle,String copyLink,String copyContent,int typeId,String goodsId,@RequestHeader(required = true) String token) {
        boolean b = goodCopyService.insertGoodCopy(new GoodCopy(0,copyTitle,copyLink,copyContent,1,typeId,goodsId));
        if (b) {
            return new ResultVO(0,"添加成功",null);
        } else {
            return new ResultVO(1,"添加失败",null);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "文案删除接口", notes = "根据提交的文案id进行商品的删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "copyId", value = "文案id", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO deleteGoodCopy(int copyId,@RequestHeader(required = true) String token){
        boolean b = goodCopyService.deleteGoodCopy(copyId);
        if (b) {
            return new ResultVO(0,"删除成功",null);
        } else {
            return new ResultVO(1,"删除失败",null);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "商品更新接口", notes = "根据提交的数据进行商品的更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "copyId", value = "文案id", required = true, type = "int"),
            @ApiImplicitParam(name = "copyTitle", value = "文案标题", required = true, type = "String"),
            @ApiImplicitParam(name = "copyLink", value = "文案链接", required = true, type = "String"),
            @ApiImplicitParam(name = "copyContent", value = "文案内容", required = true, type = "String"),
            @ApiImplicitParam(name = "typeId", value = "文案类型", required = true, type = "int"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO updateGoodCopy(int copyId,String copyTitle,String copyLink,String copyContent,int typeId,String goodsId,@RequestHeader(required = true) String token) {
        boolean b = goodCopyService.updateGoodCopy(new GoodCopy(copyId, copyTitle, copyLink, copyContent, 1, typeId, goodsId));
        if (b) {
            return new ResultVO(0,"更新成功",null);
        } else {
            return new ResultVO(1,"更新失败",null);
        }
    }
}
