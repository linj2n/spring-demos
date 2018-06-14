package cn.linj2n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class Application  extends SpringBootServletInitializer implements WebApplicationInitializer {



    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
