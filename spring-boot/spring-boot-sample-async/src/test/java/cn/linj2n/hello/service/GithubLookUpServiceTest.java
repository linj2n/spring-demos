package cn.linj2n.hello.service;

import cn.linj2n.hello.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubLookUpServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GithubLookUpServiceTest.class);

    @Autowired
    private GithubLookUpService githubLookUpService;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<User> page1 = githubLookUpService.findUser("linj2n");
        CompletableFuture<User> page2 = githubLookUpService.findUser("Spring-Projects");

        CompletableFuture.allOf(page1,page2).join();
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("---> " + page1.get());
        logger.info("---> " + page2.get());
    }
}