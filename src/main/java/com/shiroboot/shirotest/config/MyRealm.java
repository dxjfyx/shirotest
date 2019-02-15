package com.shiroboot.shirotest.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.shiroboot.shirotest.dao.TabRoleMapper;
import com.shiroboot.shirotest.dao.TabUserMapper;
import com.shiroboot.shirotest.model.TabUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * shiro的配置类
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private TabUserMapper userMapper;
    @Autowired
    private TabRoleMapper roleMapper;

    /**
     * 角色权限和对应权限添加
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        // 获取当前登陆用户
        TabUser user = (TabUser) SecurityUtils.getSubject().getPrincipal();
        // 创建授权信息存储对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 查询用户角色名, 并添加到存储对象
        List<String> roles=userMapper.findRolesByUsername(user.getColName());
        for (String role : roles) {
            // 查询权限, 并存储
            List<String> auths=roleMapper.findAuthority(role);
            authorizationInfo.addStringPermissions(auths);
        }


        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = token.getPrincipal().toString();
        List<TabUser> tabUsers = userMapper.selectList(new EntityWrapper<TabUser>().eq("col_name", username));
        if(tabUsers==null||tabUsers.size()!=1){
            return null;
        }
        TabUser user = tabUsers.get(0);

        ByteSource bytes = ByteSource.Util.bytes(username);
        String salt = user.getColSalt();

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,user.getColPassword(),bytes,getName());
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        ByteSource bytes = ByteSource.Util.bytes("11111");

        String s = bytes.toHex();
        System.out.println("盐值转字符串"+s);

        Md5Hash md5Hash = Md5Hash.fromHexString(s);
        String s1 = md5Hash.toHex();
        ByteSource salt = md5Hash.getSalt();
        System.out.println(s1);
        // 测试git分支冲突
        // 制造git冲突
        // 本地修改
        
        // 再次制造冲突3
        // 本地新冲突
        // 新冲突1.0
    }

}
