package hyx.controller;

import hyx.aop.annotation.LogTime;
import hyx.model.NameModel;
import hyx.repository.mybatis.entity.Name;
import hyx.repository.mybatis.mapper.NameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

	@Autowired
	private NameMapper maper;

	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public NameModel hello() {
		LOG.info("hello");
		return new NameModel("greetings");
	}

	@RequestMapping(value = "/bigdecimal", method = RequestMethod.GET)
	@LogTime(description = "logTimeAspectAnno")
	public String bigd() {
		System.out.println("sdssssds" + System.currentTimeMillis());

		// return BigDecimal.valueOf(0.00000000000005).toString();
		return String.valueOf(System.currentTimeMillis());
	}

	@RequestMapping(value = "/throw", method = RequestMethod.GET)
	@LogTime
	public String throwE() {
		throw new RuntimeException("something w" + System.currentTimeMillis());
	}

	@RequestMapping(value = "/string", method = RequestMethod.POST)
	public String getMessage(@RequestBody NameModel name) {
		return name.getName();
	}

	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public String getString(@RequestParam String name) {
		return name;
	}

	@RequestMapping(value = "/testplus", method = RequestMethod.GET)
	public void test() {
		Name name = new Name();
		name.setName("hhhdh");
		name.setFamilyName("ffjjfl");
		name.setExt("ilove");
		//mybatis-plus 自动加入了id自动回填功能，nice!
		maper.insert(name);
	}
}
