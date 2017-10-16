package hyx.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author hyx(610302) on 2017/10/16 0016.
 */
@Configuration
public class MapperScannerConfiguration {

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("mySqlSessionFactory");
        msc.setBasePackage("hyx.repository.mybatis.mapper");
        return msc;
    }
}
