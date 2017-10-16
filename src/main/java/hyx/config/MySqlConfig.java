package hyx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:dbconfig.properties")
@Import({MapperScannerConfiguration.class})
public class MySqlConfig {

    /**
     * 在使用@Value方式对properties进行映射的时候，必须保证${}里的字段在properties里有，否则会导致所有的属性都无法读到
     */
    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String connectUrl;
    @Value("${db.username}")
    private String userName;

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(dbDriver);
        ds.setPassword(password);
        ds.setJdbcUrl(connectUrl);
        ds.setUser(userName);
        return ds;
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(ds);
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sfb;
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(ds);
        return manager;
    }

}
