package com.bess.springboot.wfx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 10:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodSku {
    private String skuId;
    private String skuName;
    private String skuCost;
    private String skuPrice;
    private String skuPmoney;
    private String goodId;
    private int orderNo;
    private String serviceMoney;
}
