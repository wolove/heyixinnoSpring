package hyx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
//@EnableJpaRepositories(basePackages = "hyx.repository.jpa",entityManagerFactoryRef = "entityManager",transactionManagerRef = "transactionsManager")
@PropertySource(value = "classpath:dbconfig.properties")
public class MySqlConfig {

    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String connectUrl;
    @Value("${db.username}")
    private String userName;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("${mysql.entitymanager.packages.to.scan}")
    private String entityManagerPackageToScan;

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

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(ds );
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sfb;
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException {
        DataSourceTransactionManager manager = new  DataSourceTransactionManager();
        manager.setDataSource(ds);
        return manager;
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        msc.setBasePackage("hyx.repository.mybatis.mapper");
        return msc;
    }

//    @Bean(name = "entityManager")
//    public LocalContainerEntityManagerFactoryBean getEntityManager() throws PropertyVetoException {
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(getDataSource());
//        entityManager.setPackagesToScan(entityManagerPackageToScan);
//        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        entityManager.setJpaProperties(getHiberhateProperties());
//        return entityManager;
//    }
//
//    private Properties getHiberhateProperties() {
//        Properties p = new Properties();
//        p.put(PROPERTY_NAME_HIBERNATE_DIALECT, hibernateDialect);
//        p.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, hibernateShowSql);
//        p.put(PROPERTY_NAME_HBM2DDL_AUTO, hibernateHbm2ddlAuto);
//        return p;
//    }
//
//    @Bean(name = "transactionsManager")
//    public JpaTransactionManager getJpaTransactionManager() throws PropertyVetoException {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(getEntityManager().getObject());
//        return manager;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
