package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 10:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "订单对象", description = "包含订单信息的对象")
public class Order {
    private int oId;
    @ApiModelProperty(value = "订单id", dataType = "String", required = true)
    private String orderId;
    private String buyerPhone;
    @ApiModelProperty(value = "商品id", dataType = "String", required = true)
    private String goodId;
    private Date orderTime;
    @ApiModelProperty(value = "渠道编号", dataType = "String", required = true)
    private String channelId;
    private int state;
    private int buyNum;
    private String province;
    private String city;
    private String area;
    private String buyerReamrk;
    private int payType;
    private String buyerName;
//    private String skuId;
    @ApiModelProperty(value = "订单的套餐信息", dataType = "GoodSku", required = true)
    private GoodSku goodSku;
//    private String userId;
    @ApiModelProperty(value = "推荐的用户的信息", dataType = "Memeber", required = true)
    private Memeber memeber;
    private String address;
    private String courierId;
    private String orderRemark;
    private String senderType;
    @ApiModelProperty(value = "订单状态", dataType = "int", required = true)
    private int orderType;
    private String adminRemark;
    private String operator;
    private Date checkTime;
    private String orderIp;
    private Date updateTime;
    @ApiModelProperty(value = "支付宝支付状态", dataType = "String", required = true)
    private String alipayState;
    @ApiModelProperty(value = "是否付款", dataType = "int", required = true)
    private int isfk;
    private int kdzt;
    private String miaoshu;
}
