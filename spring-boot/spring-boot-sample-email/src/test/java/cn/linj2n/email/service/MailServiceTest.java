package cn.linj2n.email.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceTest.class);

    @Autowired
    private MailService mailService ;

    @Test
    public void test() {
     mailService.sendEmail("linj2n@163.com","test","Hello, this is a test email from MailServiceTest. SENDING_TIME: " + ZonedDateTime.now().toString(),false,false);
    }
}