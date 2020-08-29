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
 * @DateTime 2020/8/29 9:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "自媒体用户对象", description = "包含自媒体用户信息的实体对象")
public class Memeber {
    private int mid;
    private String memeberId;
    @ApiModelProperty(value = "自媒体登录账户", dataType = "String", required = true)
    private String account;
    private String qqNum;
    private String email;
    private String phone;
    private String recomUser;
    private Date registerTime;
    private String payAccount;
    private String name;
    @ApiModelProperty(value = "自媒体登录密码", dataType = "String", required = true)
    private String password;
    private String visitCode;
    private int useRecom;
    private String levelCode;
    private Date updateTime;
}
