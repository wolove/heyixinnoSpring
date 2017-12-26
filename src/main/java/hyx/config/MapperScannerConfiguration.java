package hyx.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author hyx(610302) on 2017/10/16 0016.
 * 如果使用mapperScan注解就不需要另起一个类了
 */
public class MapperScannerConfiguration {

    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("mySqlSessionFactory");
        msc.setBasePackage("hyx.repository.mybatis.mapper");
        return msc;
    }
}
