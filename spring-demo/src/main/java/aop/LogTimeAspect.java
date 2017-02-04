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
 *������չʾ�˼���advice����ǿ������ʹ�ã�pointcutʹ��ע�ⷽʽ
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
	 * @param pjp ���������ʹ��around����Ҫ�еģ�ʹ������ִ��Ŀ�귽��
	 * �ر�ע�⣬��ʹ����after returning ����ʹ��around�ᵼ��result��ֵʧ�ܣ��������������ʵ�advice����ҪäĿ��around,���ܻ��в���Ԥ���bug
	 * 
	 */
//	@Around(value="perfmonAspect()")
	
//	public void aroundAdvice(ProceedingJoinPoint pjp){
//		 //����ִ��ǰ
//		try {
//			pjp.proceed();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		//����ִ�к�
//	}
	
}
