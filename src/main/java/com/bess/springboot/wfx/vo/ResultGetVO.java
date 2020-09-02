package com.bess.springboot.wfx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 21:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回对象", description = "返回json字符串的VO对象，同时会返回总条数")
public class ResultGetVO {
    @ApiModelProperty(value = "返回的执行结果状态码", dataType = "int", required = true)
    private int code;
    @ApiModelProperty(value = "返回的执行结果消息", dataType = "String", required = true)
    private String msg;
    @ApiModelProperty(value = "查询的总条数", dataType = "int", required = true)
    private int count;
    @ApiModelProperty(value = "返回的执行结果", dataType = "Object", required = true)
    private Object data;
}
