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
 * @DateTime 2020/8/29 9:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "商户信息对象", description = "包含商户信息的实体对象")
public class Customer {
    private String customerId;
    private String customerName;
    private String qq;
    private String wxh;
    private String phone;
    private Date createTime;
    @ApiModelProperty(value = "商户登录账户", dataType = "String", required = true)
    private String loginName;
    @ApiModelProperty(value = "商户登录密码", dataType = "String", required = true)
    private String loginPwd;
    private int state;
    private int level;
    private double accBalance;
    private Date updateTime;
    private String website;
}
