package com.tz.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.bus.entity.BusCustomer;
import com.tz.warehouse.bus.mapper.BusCustomerMapper;
import com.tz.warehouse.bus.service.BusCustomerService;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【bus_customer】的数据库操作Service实现
 * @createDate 2022-11-05 16:31:26
 */
@Service
public class BusCustomerServiceImpl extends ServiceImpl<BusCustomerMapper, BusCustomer>
        implements BusCustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BusCustomer> queryWrapper = new LambdaQueryWrapper<>();
        String customername = (String) params.get("customername");
        if (StringUtils.isNotEmpty(customername)) {
            queryWrapper.likeRight(BusCustomer::getCustomername, customername);
        }
        String connectionpersion = (String) params.get("connectionpersion");
        if (StringUtils.isNotEmpty(connectionpersion)) {
            queryWrapper.likeRight(BusCustomer::getConnectionpersion, connectionpersion);
        }
        String phone = (String) params.get("phone");
        if (StringUtils.isNotEmpty(phone)) {
            queryWrapper.eq(BusCustomer::getPhone, phone);
        }
        IPage<BusCustomer> page = this.page(new Query<BusCustomer>().getPage(params), queryWrapper);

        return new PageUtils(page);
    }

}




