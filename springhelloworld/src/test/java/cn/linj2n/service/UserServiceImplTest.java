package cn.linj2n.service;

import cn.linj2n.domain.User;
import cn.linj2n.service.impl.UserServiceImpl;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.testng.Assert.*;

@ContextConfiguration("classpath*:/context.xml")
public class UserServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testHasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(),"admin");
    }

    @Test
    public void testAddLoginLog() {
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
