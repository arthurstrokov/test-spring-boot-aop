package com.gmail.arthurstrokov.aop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class AnnotationAopTest {

    @Autowired
    private AnnotationTestService annotationTestService;

    @Test
    void testLogExecutionTimeAnnotation(CapturedOutput output) {
        String result = annotationTestService.executeWithLogExecutionTime();
        
        assertEquals("Executed with @LogExecutionTime", result);
        assertOutputContains(output, "executed in");
    }

    @Test
    void testLogInspectingMethodAnnotation(CapturedOutput output) {
        String result = annotationTestService.executeWithLogInspectingMethod("test", 123);
        
        assertEquals("Executed with @LogInspectingMethod: test, 123", result);
        assertOutputContains(output, "Executable Class:", "Executable Method Signature:", "Executable Method Name: executeWithLogInspectingMethod");
    }

    @Test
    void testBothAnnotations(CapturedOutput output) {
        String result = annotationTestService.executeWithBothAnnotations("data");
        
        assertEquals("Executed with both: data", result);
        assertOutputContains(output, "executed in", "Executable Method Name: executeWithBothAnnotations");
    }

    @Test
    void testLogExceptionAnnotation(CapturedOutput output) {
        assertThrows(RuntimeException.class, () -> annotationTestService.throwException());
        assertOutputContains(output, "Exception in method:", "throwException", "Test exception for AOP");
    }

    @Test
    void testLogExceptionWithTime(CapturedOutput output) {
        assertThrows(RuntimeException.class, () -> annotationTestService.throwExceptionWithTime());
        assertOutputContains(output, "Exception in method:", "throwExceptionWithTime", "executed in");
    }

    private void assertOutputContains(CapturedOutput output, String... expectedLogs) {
        for (String log : expectedLogs) {
            assertTrue(output.getOut().contains(log), "Output should contain: " + log);
        }
    }
}
