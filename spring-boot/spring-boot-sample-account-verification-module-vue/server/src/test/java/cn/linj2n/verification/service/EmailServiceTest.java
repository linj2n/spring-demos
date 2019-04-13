package cn.linj2n.verification.service;

import cn.linj2n.verification.domain.User;
import cn.linj2n.verification.service.utils.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void test_SendActivationEmailMethod() {

        User user = new User();
        user.setEmail("linj2n@163.com");
        user.setLogin("linj2n");
        user.setActivationKey(RandomUtil.generateActivationKey());
        emailService.sendActivationEmail(user,"linj2n.cn");
    }
}