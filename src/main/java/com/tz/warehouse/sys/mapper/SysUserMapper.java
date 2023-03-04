package com.tz.warehouse.sys.mapper;

import com.tz.warehouse.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
* @author lenovo
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2022-11-05 16:31:58
* @Entity com.tz.warehouse.sys.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    ArrayList<String> selectLoginName();

}




