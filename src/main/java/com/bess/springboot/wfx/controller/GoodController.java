package com.bess.springboot.wfx.controller;

import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.pojo.GoodType;
import com.bess.springboot.wfx.service.ESService;
import com.bess.springboot.wfx.service.GoodService;
import com.bess.springboot.wfx.util.JWTUtil;
import com.bess.springboot.wfx.util.RandomId;
import com.bess.springboot.wfx.vo.ResultGetVO;
import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
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

    @Resource
    private ESService esService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "商品信息查询接口" , notes = "根据商户的id查询该商户所有的商品，不分页")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO listGood(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            List<Good> goods = goodService.listGoodById(customerId);
            if (goods != null) {
                return new ResultVO(0, "查询成功", goods);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/listbyid", method = RequestMethod.GET)
    @ApiOperation(value = "商品信息查询接口" , notes = "根据商户的id查询该商户所有的商品，但是只有在登录后才能查询到，需要token，分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
        @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
        @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultGetVO listGoodByCustomerId(int size, int current, @RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            List<Good> goods = goodService.listGoodByCustomerId(customerId,start,size);
            if (goods != null) {
                int count = goodService.getCount(customerId);
                return new ResultGetVO(0,"查询成功",count,goods);
            } else {
                return new ResultGetVO(1,"查询失败",0,null);
            }
        } else {
            return new ResultGetVO(1,"查询失败，权限校验未通过",0,null);
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
            @ApiImplicitParam(name = "sellNum", value = "商品库存", required = true, type = "int"),
            @ApiImplicitParam(name = "website", value = "产品网址", required = true, type = "String"),
            @ApiImplicitParam(name = "kfqq", value = "客服QQ", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO insertGood(String goodName,String goodPic1,String promoteDesc,String copyDesc,String forwardLink,String typeId,int sellNum,String website,String kfqq,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            Good good = new Good(RandomId.getNum(8), goodName, customerId, goodPic1, "", "", promoteDesc, "wenan" + RandomId.getNum(10), copyDesc, forwardLink, 2, new GoodType(typeId, "", "", "", 0, ""), "tags", 0, new Date(), 0, 0, new Date(), new Date(), "", "", sellNum, website, kfqq);
            boolean b = goodService.insertGood(good);
            if (b) {
                return new ResultVO(0, "添加成功", null);
            } else {
                return new ResultVO(1, "添加失败", null);
            }
        } else {
            return new ResultVO(1, "添加失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "商品删除接口", notes = "根据提交的商品id进行商品的删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO deleteGood(String goodId,@RequestHeader(required = true) String token){
        boolean b = goodService.deleteGood(goodId);
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            if (b) {
                return new ResultVO(0, "删除成功", null);
            } else {
                return new ResultVO(1, "删除失败", null);
            }
        } else {
            return new ResultVO(1, "删除失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "商品更新接口", notes = "根据提交的数据进行商品的更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId", value = "商品id", required = true, type = "String"),
            @ApiImplicitParam(name = "goodName", value = "商品名称", required = true, type = "String"),
            @ApiImplicitParam(name = "goodPic1", value = "商品图片", required = true, type = "String"),
            @ApiImplicitParam(name = "promoteDesc", value = "推广说明", required = true, type = "String"),
            @ApiImplicitParam(name = "copyDesc", value = "文案说明", required = true, type = "String"),
            @ApiImplicitParam(name = "forwardLink", value = "跳转链接", required = true, type = "String"),
            @ApiImplicitParam(name = "typeId", value = "商品分类", required = true, type = "String"),
            @ApiImplicitParam(name = "sellNum", value = "商品库存", required = true, type = "int"),
            @ApiImplicitParam(name = "website", value = "产品网址", required = true, type = "String"),
            @ApiImplicitParam(name = "kfqq", value = "客服QQ", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO updateGood(String goodId,String goodName,String goodPic1,String promoteDesc,String copyDesc,String forwardLink,String typeId,int sellNum,String website,String kfqq,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String customerId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("customer".equals(issuer)) {
            Good good = new Good(goodId, goodName, customerId, goodPic1, "", "", promoteDesc, "wenan" + RandomId.getNum(10), copyDesc, forwardLink, 2, new GoodType(typeId, "", "", "", 0, ""), "tags", 0, new Date(), 0, 0, new Date(), new Date(), "", "", sellNum, website, kfqq);
            boolean b = goodService.updateGood(good);
            if (b) {
                return new ResultVO(0, "更新成功", null);
            } else {
                return new ResultVO(1, "更新失败", null);
            }
        } else {
            return new ResultVO(1, "更新失败，权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    @ApiOperation(value = "商品信息查询接口" , notes = "查询所有的商品，在登录后才能查询到，需要token，分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每一页的数量", required = true, type = "int"),
            @ApiImplicitParam(name = "current", value = "页码", required = true, type = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultGetVO listGood(int size,int current,@RequestHeader(required = true) String token) {
        int start = (current - 1) * size;   // 从第几行开始查
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            List<Good> goods = goodService.listGood(start, size);
            if (goods != null) {
                int count = goodService.getCount(null);
                return new ResultGetVO(0, "查询成功", count, goods);
            } else {
                return new ResultGetVO(1, "查询失败", 0, null);
            }
        } else {
            return new ResultGetVO(1, "查询失败，权限校验未通过", 0, null);
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "图片上传接口" , notes = "添加商品时上传图片的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", required = true, type = "MultipartFile"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO uploadImg(MultipartFile file, @RequestHeader(required = true) String token) {
        System.out.println(file);
        System.out.println("上传方法被触发");
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        System.out.println(fileName);
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("39.99.143.143",21);
            boolean state = ftpClient.login("root", "abc123...");
            int replyCode = ftpClient.getReplyCode();
            System.out.println("replyCode:"+replyCode);
            // 如果响应码在200到299之间，表示与FTP站点的连接是成功的
            if (FTPReply.isPositiveCompletion(replyCode)) {
                // 设置编码UTF-8
                ftpClient.setControlEncoding("UTF-8");
                // 设置被动模式（腾讯云必须添加，其它云待测试
                ftpClient.enterLocalPassiveMode();

                // a.设置接受文件类型为二进制文件
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // b.在服务器上创建images文件夹
                //ftpClient.makeDirectory("images");
                // c.切换进入到images文件夹
                ftpClient.changeWorkingDirectory("/usr/local/webserver/nginx/resources/wfx/imgs");
                // 将文件上传到ftp服务器
                InputStream inputStream = file.getInputStream();
                boolean b = ftpClient.storeFile(fileName, inputStream);
                System.out.println("文件上传结果"+b);

                inputStream.close();
                // 2.退出登录
                ftpClient.logout();
                return new ResultVO(0,"success","http://39.99.143.143/wfx/imgs/"+fileName);
            } else {
                System.out.println("上传失败了");
                return new ResultVO(0,"fail","http://39.99.143.143/wfx/imgs/fail.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultVO(0,"fail","http://39.99.143.143/wfx/imgs/fail.png");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "商品查询接口" , notes = "根据关键词查询商品的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyName", value = "查询关键字", required = true, type = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO search(String keyName,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        System.out.println("issuer:" + issuer);
        if ("memeber".equals(issuer)) {
            SearchHits searchGood = esService.searchGood(keyName);
            if (searchGood != null) {
                return new ResultVO(0,"查询成功",searchGood);
            } else {
                return new ResultVO(1,"查询失败",null);
            }
        } else {
            return new ResultVO(1, "查询失败，权限校验未通过", null);
        }
    }
}
