package com.gmail.arthurstrokov.aop.service;

import com.gmail.arthurstrokov.aop.aspects.LogExecutionTime;
import com.gmail.arthurstrokov.aop.aspects.LogInspectingMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeanService {

    public static final Logger logger = LoggerFactory.getLogger(BeanService.class);

    @LogExecutionTime
    public void doSomeFakeJob() throws InterruptedException {
        logger.info("Doing some job");
        Thread.sleep(1000);
        logger.info("Stop doing job");
    }

    @LogInspectingMethod
    public void getBeans(ApplicationContext context) {
        logger.info("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.stream(beanNames).forEach(logger::info);
    }
}
