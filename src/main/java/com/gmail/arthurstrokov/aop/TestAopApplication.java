package com.gmail.arthurstrokov.aop;

import com.gmail.arthurstrokov.aop.entity.Agent;
import com.gmail.arthurstrokov.aop.proxy.MethodInterceptorImpl;
import com.gmail.arthurstrokov.aop.proxy.UserRepositoryInvocationHandler;
import com.gmail.arthurstrokov.aop.repository.Repository;
import com.gmail.arthurstrokov.aop.repository.UserRepository;
import com.gmail.arthurstrokov.aop.service.BeanService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

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

            // UserRepositoryInvocationHandler
            Class<?> userRepositoryClass = UserRepository.class;
            Repository repositoryObject = (Repository) Proxy.newProxyInstance(
                    userRepositoryClass.getClassLoader(),
                    userRepositoryClass.getInterfaces(),
                    new UserRepositoryInvocationHandler()
            );

            repositoryObject.save(new Object());
            repositoryObject.get(0);

            // MethodInterceptorImpl
            Agent target = new Agent();

            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.addAdvice(new MethodInterceptorImpl());
            proxyFactory.setTarget(target);

            Agent proxy = (Agent) proxyFactory.getProxy();
            target.speak();
            proxy.speak();

        };
    }
}
