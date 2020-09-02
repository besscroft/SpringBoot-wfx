package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/1 20:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "商品文案对象", description = "包含商品文案信息的对象")
public class GoodCopy {
    @ApiModelProperty(value = "文案id", dataType = "String", required = true)
    private int copyId;
    @ApiModelProperty(value = "文案标题", dataType = "String", required = true)
    private String copyTitle;
    private String copyLink;
    @ApiModelProperty(value = "文案内容", dataType = "String", required = true)
    private String copyContent;
    private int orderNo;
    private int typeId;
    @ApiModelProperty(value = "商品id", dataType = "String", required = true)
    private String goodsId;
}
