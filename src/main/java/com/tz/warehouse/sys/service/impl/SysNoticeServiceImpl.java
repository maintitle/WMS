package com.tz.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tz.warehouse.sys.common.utils.PageUtils;
import com.tz.warehouse.sys.common.utils.Query;
import com.tz.warehouse.sys.entity.SysNotice;
import com.tz.warehouse.sys.service.SysNoticeService;
import com.tz.warehouse.sys.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<SysNotice> queryWrapper = new LambdaQueryWrapper<>();
        String title = (String) params.get("title");
        if(StringUtils.isNotEmpty(title)){
            queryWrapper.likeRight(SysNotice::getTitle, title);
        }
        String opername = (String) params.get("opername");
        if(StringUtils.isNotEmpty(opername)){
            queryWrapper.likeRight(SysNotice::getOpername,opername);
        }

        IPage<SysNotice> page = page(new Query<SysNotice>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}




