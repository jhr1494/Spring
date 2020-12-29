package com.team404.common.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect //AOP를 적용시킬 클래스 --- 빈생성 필수 !
@Component
public class LogAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	/*
	@(Before, After, AfterThrowing, Around) ("execution(* 패키지주소.클래스명.메서드)")
	- Berfore : 타겟메서드 실행 전
	- After : 타겟메서드 실행 후
	- AfterThrowing : 타겟메서드 에러 발생 시
	- Around : 타겟 메서드와 결합 → ProceedingJoinPoint 매개변수 필수, Object반환타입 필수
		*proceed()(→타겟메서드의 실행) 메서드 기준으로 이전은 @Before, 이후는 @After라고 보면된다.
		*Before, After, AfterThrowing을 한번에 처리할수 있음
	 */
	
	//맨 앞에 있는 *는 접근제어자를 의미, 맨뒤에 *는 메서드를 의미함
//	@Before("execution(* com.team404.controller.*Controller.*(..))")
	
//	@Before("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public void beforeLog() {
		System.out.println("----- 메서드 실행전 -----");
	}
//	
//	@After("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public void afterLog() {
		System.out.println("----- 메서드 실행이후 -----");
	}
	
	//에러가발생했을 때 동작
//	@AfterThrowing(pointcut = "execution(* com.team404.*.service.*ServiceImpl.*(..))", throwing = "e")
	public void errorLog(Exception e) {
		System.out.println("에러로그 : " + e);
	}
	
	//로그레벨로 추가
	/*
	 <logger name="com.team404.common.util.aop">
		<level value="info" />
	</logger> 
	 */
	
	//Before, After, AfterThrowing 세가지를 한번에 처리할 수 있게 해주는 Around
	//메서드실행 권한을 가지고, 타겟메서드랑 접목시켜서 사용
	//규칙 - 반환은 Object, 매개변수로 메서드의 실행시점을 결정짓는 ProceedingJoinPoint를 선언합니다.
	@Around("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public Object aroundLog(ProceedingJoinPoint jp) {
		
		//이전(Before) --- 메서드에 전달되는 매개값 확인, 실행까지의 시간체크
		long start = System.currentTimeMillis();
		
		logger.info("실행클래스 : " + jp.getTarget()); //실행 클래스
		logger.info("실행메서드 : " + jp.getSignature().toString() ); //Target안의 메서드
		logger.info("매개값 : " + Arrays.toString( jp.getArgs() )); //전달되는 매개값

		
//		System.out.println("메서드 실행 전");
		Object result = null;
		
		try {
			result = jp.proceed(); //타겟 메서드의 실행
		} catch (Throwable e) {
//			System.out.println("에러로그 : " + e);
			e.printStackTrace(); //에러(AfterThrowing)
		}

		//이후(After)
//		System.out.println("메서드 실행 후");
		long end = System.currentTimeMillis();
		
		logger.info("메서드 소요시간 : " + (end-start)*0.001);
		return result; //proceed()의 결과를 반환해야 메서드의 정상 흐름으로 돌아갑니다
	}

}
