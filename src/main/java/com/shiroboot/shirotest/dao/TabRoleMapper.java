package com.shiroboot.shirotest.dao;

import com.shiroboot.shirotest.model.TabRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dxjfyx
 * @since 2019-01-29
 */
public interface TabRoleMapper extends BaseMapper<TabRole> {

    /**
     * 获取角色权限
     * @param
     * @return
     */
    List<String> findAuthority(@Param("roleName") String roleName);
}
