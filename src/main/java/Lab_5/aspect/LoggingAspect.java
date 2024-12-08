package Lab_5.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Before Advice: Executed before the method
    @Before("execution(* Lab_5.service.HouseholdService.*(..))")
    public void logBefore() {
        System.out.println("Before - Executing method in HouseholdService...");
    }

    // After Advice: Executed after the method (regardless of outcome)
    @After("execution(* Lab_5.service.HouseholdService.*(..))")
    public void logAfter() {
        System.out.println("After - Method execution completed in HouseholdService...");
    }

    // After Returning Advice: Executed after the method successfully returns
    @AfterReturning(pointcut = "execution(* Lab_5.service.HouseholdService.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("After Returning - Method executed successfully with result: " + result);
    }

    // After Throwing Advice: Executed if the method throws an exception
    @AfterThrowing(pointcut = "execution(* Lab_5.service.HouseholdService.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        System.out.println("After Throwing - An exception occurred: " + exception.getMessage());
    }

    // Around Advice: Executed before and after the method
    @Around("execution(* Lab_5.service.HouseholdService.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around - Before executing method: " + joinPoint.getSignature().getName());
        Object result;
        try {
            result = joinPoint.proceed(); // Proceed with method execution
        } catch (Exception e) {
            System.out.println("Around - Exception during method execution: " + e.getMessage());
            throw e; // Re-throw the exception to maintain behavior
        }
        System.out.println("Around - After executing method: " + joinPoint.getSignature().getName());
        return result;
    }
}
