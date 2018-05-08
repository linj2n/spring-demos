package cn.linj2n.hellospring;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class HelloSpringImplTest {

    @Test
    public void sayHello() {

/*     传统的程序设计
        // 1. 创建一个 HelloSpringImpl 实例
        HelloSpringApi helloSpring = new HelloSpringImpl();

        // 2. 调用 helloSpring 的 sayHello() 方法。
        helloSpring.sayHello();
*/

        // 1. 读取配置文件实例一个 IoC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("HelloSpring.xml");

        // 2. 从容器中获取 HelloSpringImpl 类对应的 Bean
        HelloSpringApi helloSpring = context.getBean("hello",HelloSpringApi.class);

        // 3. 执行业务逻辑
        helloSpring.sayHello();
    }
}