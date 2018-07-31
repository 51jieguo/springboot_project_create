package ${basePackage}.config;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableMongoRepositories(basePackages = "${basePackage}.repository.${dbName}", mongoTemplateRef = "${dbName}MongoTemplate")
public class ${dbName?cap_first}MongoConfig {

    <#if isPrimary==true>
    @Primary
    </#if>
    @Bean(name = "${dbName}MongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.${dbName}")
    public MongoProperties ${dbName}MongoProperties() {
        return new MongoProperties();
    }

    <#if isPrimary==true>
    @Primary
    </#if>
    @Bean(name = "${dbName}MongoTemplate")
    public MongoTemplate danmuMongoTemplate() throws Exception {
        MongoProperties mongoProperties = ${dbName}MongoProperties();
        MongoClient mongoClient = mongoProperties.createMongoClient(null,null);
        return new MongoTemplate(mongoClient, mongoProperties.getDatabase());
    }
}
