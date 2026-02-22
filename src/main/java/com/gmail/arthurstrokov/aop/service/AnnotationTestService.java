package com.gmail.arthurstrokov.aop.service;

import com.gmail.arthurstrokov.aop.annotations.LogException;
import com.gmail.arthurstrokov.aop.annotations.LogExecutionTime;
import com.gmail.arthurstrokov.aop.annotations.LogInspectingMethod;
import org.springframework.stereotype.Service;

@Service
public class AnnotationTestService {

    @LogExecutionTime
    public String executeWithLogExecutionTime() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Executed with @LogExecutionTime";
    }

    @LogInspectingMethod
    public String executeWithLogInspectingMethod(String param1, int param2) {
        return "Executed with @LogInspectingMethod: " + param1 + ", " + param2;
    }

    @LogExecutionTime
    @LogInspectingMethod
    public String executeWithBothAnnotations(String data) {
        return "Executed with both: " + data;
    }

    @LogException
    public void throwException() {
        throw new RuntimeException("Test exception for AOP");
    }

    @LogExecutionTime
    @LogException
    public void throwExceptionWithTime() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        throw new RuntimeException("Exception after some time");
    }
}
