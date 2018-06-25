package com.yun.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author G
 * @description（）
 * @date 2018/6/21.
 */

//测试
public class AuthenticationTest {

    //   先用这个realm 是个自带的简单的
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    //    先在这个realm中添加一个用户
    @Before
    public void addUser() {
        //认证的时候只需要用户名和密码
//      simpleAccountRealm.addAccount("Andy", "123456");
//      但是在授权的时候是需要 用户的角色的 我们给用户添加一个角色
        simpleAccountRealm.addAccount("Andy", "123456","admin","user");

    }

    @Test
    public void testAuthentication() {

//   1. 获取securityManager对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //在securityManager中设置realm
        securityManager.setRealm(simpleAccountRealm);

        //   2. 主体提交认证请求 security提供了一个工具类用来设置securityManager 环境
//       和获取主体

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

//        获取到主题后 可以提交我们的认证  就是要登录了
//        登录的时候要带上我们的认证数据  这里我们用usernamePasswordToken
//        usernamePasswordToken 有默认的构造器 也可以传我们的的数据（就是用户名和密码“）


        UsernamePasswordToken token = new UsernamePasswordToken("Andy", "123456");
        subject.login(token);

//subject 还提供了一个是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //我们在认证完之后检查一下 用户是否具有这样的角色
        subject.checkRoles("admin","user");



    }


}
