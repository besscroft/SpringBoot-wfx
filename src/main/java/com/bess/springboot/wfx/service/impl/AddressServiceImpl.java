package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.AddressDAO;
import com.bess.springboot.wfx.pojo.City;
import com.bess.springboot.wfx.pojo.Province;
import com.bess.springboot.wfx.pojo.Region;
import com.bess.springboot.wfx.service.AddressService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Province> listProvince() {
        List<Province> provinces = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listProvince").get("provinces");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listProvince").get("provinces");
                    if (s == null) {
                        provinces = addressDAO.listProvince();
                        String jsonStr = mapper.writeValueAsString(provinces);
                        stringRedisTemplate.boundHashOps("listProvince").put("provinces",jsonStr);
                    }
                }
            } else {
                provinces = mapper.readValue(s, new TypeReference<List<Province>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provinces;
    }

    @Override
    public List<City> listCityByProvince(String provinceId) {
        List<City> cities = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listCityByProvince").get("cities-"+provinceId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listCityByProvince").get("cities-"+provinceId);
                    if (s == null) {
                        cities = addressDAO.listCityByProvince(provinceId);
                        String jsonStr = mapper.writeValueAsString(cities);
                        stringRedisTemplate.boundHashOps("listCityByProvince").put("cities-"+provinceId,jsonStr);
                    }
                }
            } else {
                cities = mapper.readValue(s, new TypeReference<List<City>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<Region> listRegionByCity(String cityId) {
        List<Region> regions = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listRegionByCity").get("regions-" + cityId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listRegionByCity").get("regions-" + cityId);
                    if (s == null) {
                        regions = addressDAO.listRegionByCity(cityId);
                        String jsonStr = mapper.writeValueAsString(regions);
                        stringRedisTemplate.boundHashOps("listRegionByCity").put("regions-" + cityId,jsonStr);
                    }
                }
            } else {
                regions = mapper.readValue(s, new TypeReference<List<Region>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

    @Override
    public Province getProvince(String provinceId) {
        Province province = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getProvince").get("province-" + provinceId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getProvince").get("province-" + provinceId);
                    if (s == null) {
                        province = addressDAO.getProvince(provinceId);
                        String jsonStr = mapper.writeValueAsString(province);
                        stringRedisTemplate.boundHashOps("getProvince").put("province-"+provinceId,jsonStr);
                    }
                }
            } else {
                province = mapper.readValue(s, Province.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return province;
    }

    @Override
    public City getCity(String cityId) {
        City city = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCity").get("city-" + cityId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCity").get("city-" + cityId);
                    if (s == null) {
                        city = addressDAO.getCity(cityId);
                        String jsonStr = mapper.writeValueAsString(city);
                        stringRedisTemplate.boundHashOps("getCity").put("city-"+cityId,jsonStr);
                    }
                }
            } else {
                city = mapper.readValue(s, City.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public Region getRegion(String regionId) {
        Region region = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getRegion").get("region-" + regionId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getRegion").get("region-" + regionId);
                    if (s == null) {
                        region = addressDAO.getRegion(regionId);
                        String jsonStr = mapper.writeValueAsString(region);
                        stringRedisTemplate.boundHashOps("getRegion").put("region-"+regionId,jsonStr);
                    }
                }
            } else {
                region = mapper.readValue(s, Region.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }
}
