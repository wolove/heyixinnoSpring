package hyx;


import hyx.config.SpringConfig;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hyx 
 * 一个过去搭的可以测试多数据源的UT基础框架类
 *@使用方法 测试服务可以直接注入service进行测试
 *集成测试请参照  {@link BaseTest}
 */

@Transactional
@WebAppConfiguration	
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
@TestExecutionListeners(listeners = { TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
public class MultiDatasourceBaseTest {

	@Rollback
//	@Test
	public void test(){
		//该方法里的所有操作在测试完成后都会回滚
		
	}
}
