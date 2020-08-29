package com.bess.springboot.wfx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回对象", description = "返回json字符串的VO对象")
public class ResultVO {
    @ApiModelProperty(value = "返回的执行结果状态码", dataType = "int", required = true)
    private int code;
    @ApiModelProperty(value = "返回的执行结果消息", dataType = "String", required = true)
    private String msg;
    @ApiModelProperty(value = "返回的执行结果", dataType = "Object", required = true)
    private Object data;

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
