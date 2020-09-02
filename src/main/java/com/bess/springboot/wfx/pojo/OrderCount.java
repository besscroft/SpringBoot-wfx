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
 * @DateTime 2020/9/2 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "订单状态对象", description = "包含订单状态的表")
public class OrderCount {
    private String ocId;
    @ApiModelProperty(value = "订单id", dataType = "String", required = true)
    private String orderId;
    private double orderMoney;
    private double orderPmoney;
    @ApiModelProperty(value = "订单状态", dataType = "int", required = true)
    private int stateId;
    private Date createTime;
    private String remark;
    private Date expPayDate;
}
