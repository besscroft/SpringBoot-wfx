package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "商品类别对象", description = "包含商品类别信息的实体对象")
public class GoodType {
    @ApiModelProperty(value = "商品类别id", dataType = "String", required = true)
    private String typeId;
    @ApiModelProperty(value = "商品类别名称", dataType = "String", required = true)
    private String typeName;
    private String parentId;
    private String typeTag;
    private int orderNo;
    private String alisaCode;
}
