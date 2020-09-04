package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 10:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "城市对象", description = "包含城市信息的实体对象")
public class City {
    @ApiModelProperty(value = "城市id", dataType = "String", required = true)
    private String cityId;
    @ApiModelProperty(value = "城市名称", dataType = "String", required = true)
    private String cityName;
    @ApiModelProperty(value = "省名称", dataType = "String", required = true)
    private String provinceId;
    private String firstLetter;
    private int isHot;
    private int state;
}
