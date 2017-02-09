package hyx;

import hyx.config.SpringConfig;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
public class BaseTest {

	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext appContext;

	// 必须是static no-arg method,该方法只调用一次，由于是静态方法，所以内部不能出现动态变量，比如appContext
	// @BeforeClass
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();
	}
}
