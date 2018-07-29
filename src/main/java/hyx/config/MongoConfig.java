package hyx.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hyx(610302) on 2017/7/5 0005.
 */
//@EnableJpaRepositories(basePackages = "hyx.repository.mongo", entityManagerFactoryRef = "")
@PropertySource(value = "classpath:mongoconfig.properties")
public class MongoConfig {

    @Value("${mongo.host}")
    private String mongoHost;
    @Value("${mongo.port}")
    private Integer mongoPort;
    @Value("${mongo.db.name}")
    private String mongoDbName;

    /**
     * Factory bean that creates the com.mongodb.MongoClient instance
     * <p>To access the com.mongodb.MongoClient object created by the MongoClientFactoryBean in other @Configuration or your own classes,
     * use a “private @Autowired Mongo mongo;” field.</p>
     * 适用于web连接远程数据库,如果用这种方式获取mongoClient，创建mongoDbFactory的时候要注入
     */
//  @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean client = new MongoClientFactoryBean();
        client.setHost(mongoHost);
        client.setPort(mongoPort);
        client.setCredentials(new MongoCredential[]{MongoCredential.createCredential("hyx", mongoDbName, "1234".toCharArray())});
        return client;
    }

    /**
     * 老老实实用mongoClientk
     */
    @Bean
    public MongoClient mongoClient() {
        List<MongoCredential> credentialList = new ArrayList<>();
        credentialList.add(MongoCredential.createCredential("hyx", mongoDbName, "1234".toCharArray()));
        //这一步不是必须的，就是想用一下，java泛型检查只在编译期执行，实际到运行期都是obj(使用强制类型转换）
        List<MongoCredential> unmodifiedCredentialList = Collections.unmodifiableList(credentialList);
        ServerAddress addr = new ServerAddress(mongoHost, mongoPort);
        return new MongoClient(addr, unmodifiedCredentialList);
    }

    /**
     * 如果这里的传参是直接new 出来的MongoClient ，默认是连接localhost 的27017端口
     *
     * @return
     */
    @Bean
    public MongoDbFactory mongodbFactory() {
        return new SimpleMongoDbFactory(mongoClient(), mongoDbName);
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoClient(), mongoDbName);
    }
}
