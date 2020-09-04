package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.City;
import com.bess.springboot.wfx.pojo.Province;
import com.bess.springboot.wfx.pojo.Region;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 10:59
 */
public interface AddressService {
    public List<Province> listProvince();   // 查询所有的省份
    public List<City> listCityByProvince(String provinceId); // 根据省份id查询城市
    public List<Region> listRegionByCity(String cityId);   // 根据城市id查区
    public Province getProvince(String provinceId); // 根据省份id查省份信息
    public City getCity(String cityId); // 根据城市id查询城市信息
    public Region getRegion(String regionId); // 根据区id查询区信息
}
