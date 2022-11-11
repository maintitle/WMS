package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.entity.SysNotice;
import com.tz.warehouse.sys.service.SysNoticeService;
import com.tz.warehouse.sys.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenovo
* @description 针对表【sys_notice】的数据库操作Service实现
* @createDate 2022-11-05 16:31:58
*/
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
    implements SysNoticeService{

    @Override
    public List<SysNotice> getInfo() {
        return list();
    }
}




