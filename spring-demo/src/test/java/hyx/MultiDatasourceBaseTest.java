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
 * һ����ȥ��Ŀ��Բ��Զ�����Դ��UT���������
 *@ʹ�÷��� ���Է������ֱ��ע��service���в���
 *���ɲ��������  {@link BaseTest}
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
		//�÷���������в����ڲ�����ɺ󶼻�ع�
		
	}
}
