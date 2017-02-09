package hyx.aop;

import hyx.aop.annotation.LogTime;
import hyx.model.NameModel;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hyx 
 * 	execution(modifiers-pattern? ret-type-pattern  declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
 *  ��ȡ��spring�����ĵ���
 */
@Component
@Aspect
public class AllMethodAspect {

	/**
	 * �·���һ��*��ʾ����ֵ���ñ��ʽ��ʾcontroller�������Ӱ������еķ�������һ������param) 
	 * �����args�������ã�
	 * 1.��ʾ����������method����һ������ 
	 * 2.���ڴ������������advice
	 * 
	 * args��Ĳ�������������advice������Ĳ�������Ҫ�ϸ�ͳһ������ᱨ�����ǲ�Ҫ��ͱ�adviced�����Ĳ�����һ��
	 */
	
	@Pointcut("execution(* controller..*.*(..))")
	private void commonMethod(){
		
	}
	
	@Pointcut("commonMethod()&&args(param)")
	private void methodPointcut(NameModel param) {
		// ��signature �Ĳ��������ͻ���Ϊ������ɸѡ��adviced�ķ�������Ҫ��expression ���args�������һ��
	}
	
	@Pointcut("commonMethod()&&@annotation(anno)")
	private void annoParamTransport(LogTime anno){
		//�������ϵ�ע������
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
