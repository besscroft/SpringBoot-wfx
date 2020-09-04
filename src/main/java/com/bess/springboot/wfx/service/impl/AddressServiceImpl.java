package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.AddressDAO;
import com.bess.springboot.wfx.pojo.City;
import com.bess.springboot.wfx.pojo.Province;
import com.bess.springboot.wfx.pojo.Region;
import com.bess.springboot.wfx.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/4 11:00
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDAO addressDAO;

    @Override
    public List<Province> listProvince() {
        return addressDAO.listProvince();
    }

    @Override
    public List<City> listCityByProvince(String provinceId) {
        return addressDAO.listCityByProvince(provinceId);
    }

    @Override
    public List<Region> listRegionByCity(String cityId) {
        return addressDAO.listRegionByCity(cityId);
    }

    @Override
    public Province getProvince(String provinceId) {
        return addressDAO.getProvince(provinceId);
    }

    @Override
    public City getCity(String cityId) {
        return addressDAO.getCity(cityId);
    }

    @Override
    public Region getRegion(String regionId) {
        return addressDAO.getRegion(regionId);
    }
}
