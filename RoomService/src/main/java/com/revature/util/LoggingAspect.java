package com.revature.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.revature.service.RoomService;

/*
 * This class will serve as our logging aspect. An aspect represents a modularization of a cross-cutting concern.
 * As logging is a concern that spans multiple layers of our application, we want to centralize the place where we
 * write the source code for our logging statements. We will then rely on AOP to inject our advice where we tell it to.
 * Recall that advice is code that will be injected elsewhere.
 * 
 * As Spring will create an instance of this LoggingAspect class, we
 * want it to be a Spring bean.
 */

@Aspect
@Component("loggingAspect")
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

	/*
	 * Our first order of business is to tell Spring where we would like to log. In other words, we're telling Spring
	 * where we would like our advice to be injected. We do this by defining "pointcuts". We simply annotate a method
	 * with @Pointcut and provide a value for the "value" attribute. That value should be a "pointcut expression". A
	 * pointcut specifies your target for injecting advice; yes, the pointcut expression does need to follow a proper
	 * syntax.
	 */
	@Pointcut("within(com.revature.service.*)")
	public void logWithinServicePackage() {}
	
	@Pointcut("within(com.revature.repository.*)")
	public void loginWithinRepositoryPackage() {}
	
	/*
	 * Our next step would be to create the actual advice; the actual advice is the source could we would normally
	 * write elsewhere. When creating the advice, you get to specify when the advice should be injected. For instance,
	 * you can specify that advice should be injected before a method invocation.
	 * 
	 * When working with advice, you have access to a special JoinPoint parameter. This JoinPoint gives you reflective
	 * access to the method that is being invoked. This means you can get information such as the method name, access
	 * level, return type, etc.
	 */
	@Before("logWithinServicePackage()")
	public void logBefore(JoinPoint jp){
		logger.info("Logging using Spring AOP. The method targeted is: " + jp.getSignature().getName());
	}
	
	@After("logWithinServicePackage()")
	public void logAfter(JoinPoint jp) {
		logger.info("Logging after: " + jp.getSignature().getName());
	}
	
	/*
	 * This advice is injected after a method returns.
	 */
	@AfterReturning(value = "logWithinServicePackage()", returning = "returnedObject")
	public void logAfterReturning(JoinPoint jp, Object returnedObject) {
		logger.info("Logging after the " + jp.getSignature().getName() + " method has returned " + returnedObject);
	}
	
	/*
	 * AfterThrowing advice is injected an exception is thrown. Just make sure that there really is an exception could
	 * be thrown from within the context of the targeted methods.
	 */
	@AfterThrowing(value = "logWithinServicePackage()", throwing = "thrownException")
	public void logAfterThrowing(JoinPoint jp, Exception thrownException) {
		logger.info("Logging after the " + jp.getSignature().getName() + " method has thrown a(n) " + thrownException);
	}
	
	/*
	 * Around advice is incredibly powerful advice. It can be used to inject advice before AND after a method is invoked.
	 * It is so powerful that it can completely stop a method from being invoked. In fact, if you want to call the underlying
	 * method, you have to manually do so inside of your advice by using the ProceedingJoinPoint. You can use your
	 * ProceedingJoinPoint to invoke the underlying method.
	 */
	@Around("logWithinServicePackage()")
	public Object logAround(ProceedingJoinPoint pjp) {
		Object returnedValue = null;
		logger.info("using around advice (before) for the service layer methods");
		
		try {
			returnedValue = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		logger.info("using around advice (after) for the service layer methods");

		return returnedValue;
	}

}