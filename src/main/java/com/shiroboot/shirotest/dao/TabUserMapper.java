package com.shiroboot.shirotest.dao;

import com.shiroboot.shirotest.model.TabRole;
import com.shiroboot.shirotest.model.TabUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dxjfyx
 * @since 2019-01-29
 */
public interface TabUserMapper extends BaseMapper<TabUser> {

    /**
     * 根据用户名查询角色名
     * @param colName
     * @return
     */
    List<String> findRolesByUsername(String colName);
}
