package com.spring.boot.annotation;

import java.awt.Point;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodRunningTimeAspect {

	@Around("@annotation(MethodRunningTime)")// interface ile alakali
	public Object  basla(ProceedingJoinPoint point,MethodRunningTime methodRunningTime)throws Throwable {
		if (!methodRunningTime.timeCalculation()) {
			return point.proceed();
		}
		String ClassName=point.getSignature().getDeclaringType().getSimpleName();
		String MethodName=point.getSignature().getName();
		StopWatch stopWatch=new StopWatch();
		stopWatch.start();
		Object resualt=point.proceed();
		stopWatch.stop();
		System.out.println("ClassName"+ClassName+"MethodName"+MethodName+" "+ stopWatch.getTotalTimeMillis()+" Ms " );
		return resualt;
		
	}
	
}
