package controller;

import java.math.BigDecimal;
import java.math.BigInteger;

import model.NameModel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(path="/hello",method=RequestMethod.GET)
	public NameModel hello(){
		return new NameModel("greetings");
	}
	
	@RequestMapping(path="/bigdecimal",method=RequestMethod.GET)
	public BigDecimal bigd(){
		return BigDecimal.valueOf(0.00000000000005);
	}
}
