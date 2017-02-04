package controller;

import model.NameModel;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aop.annotation.LogTime;

@RestController
public class HelloController {

	@RequestMapping(path="/hello",method=RequestMethod.GET)
	public NameModel hello(){
		return new NameModel("greetings");
	}
	
	@RequestMapping(path="/bigdecimal",method=RequestMethod.GET)
	@LogTime(description="logTimeAspectAnno")
	public String bigd(){
		System.out.println("sdssssds"+System.currentTimeMillis());
		
//		return BigDecimal.valueOf(0.00000000000005).toString();
		return String.valueOf(System.currentTimeMillis());
	}
	
	@RequestMapping(path="/throw",method=RequestMethod.GET)
	@LogTime
	public String throwE(){
		 return throwsExc();
	}
	
	private String throwsExc(){
		throw new RuntimeException("something w"+System.currentTimeMillis());
	}
	
	@RequestMapping(path="/string",method=RequestMethod.POST)
	public  String getMessage(@RequestBody NameModel name){
		return name.getName();
	}
	@RequestMapping(path="/string",method=RequestMethod.GET)
	public  String getString(@RequestParam String name){
		return name;
	}
}
