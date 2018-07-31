package ${basePackage}.repository.${bussinessPackage};

import ${basePackage}.model.${bussinessPackage}.${name?cap_first}Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(mongoTemplateRef = "${dbName}MongoTemplate")
public interface ${name?cap_first}Respository  extends MongoRepository<${name?cap_first}Model,String> {

}