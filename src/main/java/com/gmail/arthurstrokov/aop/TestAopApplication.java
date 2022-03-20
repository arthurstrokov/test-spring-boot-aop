package com.gmail.arthurstrokov.aop;

import com.gmail.arthurstrokov.aop.service.BeanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            BeanService beanService = (BeanService) context.getBean("beanService");
            beanService.getBeans(context);
            beanService.doSomeFakeJob();
        };
    }
}
