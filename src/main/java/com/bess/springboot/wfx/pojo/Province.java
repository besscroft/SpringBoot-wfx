package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "省份对象", description = "包含省份信息的对象")
public class Province {
    @ApiModelProperty(value = "省份id", dataType = "String", required = true)
    private String provinceId;
    @ApiModelProperty(value = "省份名称", dataType = "String", required = true)
    private String provinceName;
}
