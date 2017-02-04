package aop;

import model.NameModel;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import aop.annotation.LogTime;

/**
 * @author hyx 
 * 	execution(modifiers-pattern? ret-type-pattern  declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
 *  截取自spring官网文档。
 */
@Component
@Aspect
public class AllMethodAspect {

	/**
	 * 下方第一个*表示返回值，该表达式表示controller包及其子包下所有的方法（有一个参数param) 
	 * 后面的args两个作用：
	 * 1.表示满足条件的method包含一个参数 
	 * 2.用于传递这个参数给advice
	 * 
	 * args里的参数名字与后面的advice方法里的参数名字要严格统一，否则会报错，但是不要求和被adviced方法的参数名一致
	 */
	
	@Pointcut("execution(* controller..*.*(..))")
	private void commonMethod(){
		
	}
	
	@Pointcut("commonMethod()&&args(param)")
	private void methodPointcut(NameModel param) {
		// 该signature 的参数的类型会作为条件来筛选被adviced的方法，且要与args里的名字一致
	}
	
	@Pointcut("commonMethod()&&@annotation(anno)")
	private void annoParamTransport(LogTime anno){
		//传方法上的注解属性
	}
	
	@Before("annoParamTransport(anno)")
	public void annoMesage(LogTime anno){
		System.out.println("getAnnoMessages by advice  "+anno.description());
	}
	
	@Before(value = "methodPointcut(param)")
	public void transParam(NameModel param) {
		System.out.println(param.getName() + " in aspect");
	}
}
