package aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hyx
 *这个类简单展示了几种advice的增强方法的使用，pointcut使用注解方式
 */
@Component
@Aspect
public class LogTimeAspect  {
	
	@Pointcut("@annotation(aop.annotation.LogTime)")
	private void perfmonAspect(){
		//nothing need to do ,just a signature
	}

	@Before(value="perfmonAspect()")
	public void beforeAdvice(){
		System.out.println("before Advice"+System.currentTimeMillis());
	}
	
	@After(value="perfmonAspect()")
	public void afterAdvice(){
		System.out.println("after Advice"+System.currentTimeMillis());
	}
	
	@AfterReturning(value="perfmonAspect()",returning="result")
	public void afterPerfmonAspect(Object result){
		System.out.println("after returning advice"+System.currentTimeMillis());
		System.out.println("result is " + result);
	}
	
	@AfterThrowing(value="perfmonAspect()",throwing="ex")
	public void afterThrowingAdvice(Exception ex){
		System.out.println("after throwing advice"+System.currentTimeMillis());
	}
	
	
	/**
	 * @param pjp 这个参数是使用around必须要有的，使用它来执行目标方法
	 * 特别注意，在使用了after returning 后，再使用around会导致result传值失败，所以最好用最合适的advice，不要盲目用around,可能会有不可预测的bug
	 * 
	 */
//	@Around(value="perfmonAspect()")
	
//	public void aroundAdvice(ProceedingJoinPoint pjp){
//		 //方法执行前
//		try {
//			pjp.proceed();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		//方法执行后
//	}
	
}
