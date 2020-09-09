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
 * @DateTime 2020/8/31 14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "商品对象", description = "包含商品全部信息的对象")
public class Good {
    @ApiModelProperty(value = "商品id", dataType = "String", required = true)
    private String goodId;
    @ApiModelProperty(value = "商品名称", dataType = "String", required = true)
    private String goodName;
    @ApiModelProperty(value = "商户id", dataType = "String", required = true)
    private String customerId;
    private String goodPic1;
    private String goodPic2;
    private String goodPic3;
    private String promoteDesc;
    private String copyIds;
    private String copyDesc;
    private String forwardLink;
    private int orderNo;
//    private String typeId;
    @ApiModelProperty(value = "商品类别信息", dataType = "GoodType", required = true)
    private GoodType goodType;
    private String tags;
    private int state;
    private Date createTime;
    private int toped;
    private int recomed;
    private Date topedTime;
    private Date recomedTime;
    private String spcId;
    private String zonId;
    @ApiModelProperty(value = "商品库存信息", dataType = "int", required = true)
    private int sellNum;
    private String website;
    private String kfqq;
}
