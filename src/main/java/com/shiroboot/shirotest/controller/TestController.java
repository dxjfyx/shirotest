package com.shiroboot.shirotest.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Strings;
import com.shiroboot.shirotest.dao.TabUserMapper;
import com.shiroboot.shirotest.model.TabUser;
import javafx.scene.control.Tab;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.cert.X509CertSelector;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private TabUserMapper userMapper;

    @RequestMapping("/findAll")
    @ResponseBody
    public Object findAll(){
        List<TabUser> tabUsers = userMapper.selectList(new EntityWrapper<TabUser>());
        return tabUsers;
    }

    @RequestMapping("/regestPage")
    public String regestPage(){


        return "regist";
    }

    @RequestMapping("/regest")
    @ResponseBody
    public String regest(TabUser user){
        ByteSource bytes = ByteSource.Util.bytes(user.getColName());

        //user.setColSalt(s);

        String hashAlgorithmName = "MD5";
        int hashIterations = 1024;

        String obj = new SimpleHash(hashAlgorithmName, user.getColPassword(), bytes, hashIterations).toHex();

        user.setColPassword(obj);
        user.setColStatus(0);
        userMapper.insert(user);

        return "添加成功";
    }

    @RequestMapping("/login")
    public String loginUser(HttpServletRequest request, String username, String password, boolean rememberMe, Model model, HttpSession session) {

        //对密码进行加密
        //password=new SimpleHash("md5", password, ByteSource.Util.bytes(username.toLowerCase() + "shiro"),2).toHex();

        //如果有点击  记住我
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password,rememberMe);

        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录操作
            subject.login(usernamePasswordToken);
            TabUser user=(TabUser) subject.getPrincipal();
            //更新用户登录时间，也可以在ShiroRealm里面做
            session.setAttribute("user", user);
            model.addAttribute("user",user);
            return "index";
        } catch(Exception e) {
            //登录失败从request中获取shiro处理的异常信息 shiroLoginFailure:就是shiro异常类的全类名
            String exception = (String) request.getAttribute("shiroLoginFailure");
            model.addAttribute("msg",e.getMessage());
            //返回登录页面
            return "login";
        }
    }


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 测试密码加密
     * @param args
     */
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";

        int hashIterations = 1024;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
    }
}
