package hyx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "hyx.repository.mysql",entityManagerFactoryRef = "entityManager",transactionManagerRef = "transactionsManager")
//@PropertySource(value = "classpath:dbconfig.properties")
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

    @Bean
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(dbDriver);
        ds.setPassword(password);
        ds.setJdbcUrl(connectUrl);
        ds.setUser(userName);
        return ds;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean getEntityManager() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(getDataSource());
        entityManager.setPackagesToScan(entityManagerPackageToScan);
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setJpaProperties(getHiberhateProperties());
        return entityManager;
    }

    private Properties getHiberhateProperties() {
        Properties p = new Properties();
        p.put(PROPERTY_NAME_HIBERNATE_DIALECT, hibernateDialect);
        p.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, hibernateShowSql);
        p.put(PROPERTY_NAME_HBM2DDL_AUTO, hibernateHbm2ddlAuto);
        return p;
    }

    @Bean(name = "transactionsManager")
    public JpaTransactionManager getJpaTransactionManager() throws PropertyVetoException {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(getEntityManager().getObject());
        return manager;
    }


}
