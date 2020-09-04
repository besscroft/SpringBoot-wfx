package com.bess.springboot.wfx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 10:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "区级对象", description = "包含各个区信息的对象")
public class Region {
    @ApiModelProperty(value = "区id", dataType = "int", required = true)
    private int regionId;
    @ApiModelProperty(value = "区名称", dataType = "String", required = true)
    private String regionName;
    @ApiModelProperty(value = "城市id", dataType = "int", required = true)
    private int cityId;
}
