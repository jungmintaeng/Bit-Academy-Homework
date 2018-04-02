package com.cafe24.mysite.aspect;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(MeasureExecutionTimeAspect.class);
	
	@Around("execution(* *..repository.*.*(..))")
	public Object roundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		//before advice code
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		
		//after advice code
		sw.stop();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		
		LOG.warn("[ "  +  className  +  " - " +  methodName  +  " ] time : " + sw.getTotalTimeMillis() + " (ms)");
		
		return result;
	}
}
