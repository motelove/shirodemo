package com.yun.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author G
 * @description（）
 * @date 2018/6/22.
 */
public class IniRealmTest {


    @Test
    public void testAuthentication() {

        //0 我们用inirealm来实现
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

//   1. 获取securityManager对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        //   2. 主体提交认证请求 security提供了一个工具类用来设置securityManager 环境
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
       //        获取到主题后 可以提交我们的认证  就是要登录了
       //        登录的时候要带上我们的认证数据  这里我们用usernamePasswordToken
       //        usernamePasswordToken 有默认的构造器 也可以传我们的的数据（就是用户名和密码“）

        UsernamePasswordToken token = new UsernamePasswordToken("XiaoMi", "123456");
        subject.login(token);

        //subject 还提供了一个是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //我们在认证完之后检查一下 用户是否具有这样的角色
        subject.checkRoles("admin");



//       查看用户有没有用户删除的权限
        subject.checkPermission("user:update");

    }
}
