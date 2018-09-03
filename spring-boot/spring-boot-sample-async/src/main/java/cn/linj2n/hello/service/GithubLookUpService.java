package cn.linj2n.hello.service;

import cn.linj2n.hello.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GithubLookUpService {

    private static final Logger logger = LoggerFactory.getLogger(GithubLookUpService.class);

    private final RestTemplate restTemplate;

    private static final String LOOK_UP_USER_URL = "https://api.github.com/users/";

    public GithubLookUpService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser(String username) throws Exception {
        logger.info("Start Looking up " + username);
        String url = LOOK_UP_USER_URL + username;
        logger.info("Request " + url);
        User results = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
