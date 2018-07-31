package ${basePackage}.service.${bussinessPackage};

import ${basePackage}.model.${bussinessPackage}.${name?cap_first}Model;
import ${basePackage}.repository.${bussinessPackage}.${name?cap_first}Respository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import javax.annotation.Resource;

@Slf4j
@Service
public class ${name?cap_first}Service {

        @Autowired
        private ${name?cap_first}Respository ${name}Respository;

        @Resource(name = "${dbName}MongoTemplate")
        private MongoTemplate mongoTemplate;


        public ${name?cap_first}Model insert(${name?cap_first}Model ${name}Model){
                return ${name}Respository.insert(${name}Model);
        }

        public ${name?cap_first}Model update(${name?cap_first}Model ${name}Model){
                return ${name}Respository.save(${name}Model);
        }

        public void delete(String id){
                ${name}Respository.delete(id);
        }

        public void delete(${name?cap_first}Model ${name}Model){
                ${name}Respository.delete(${name}Model);
        }

        public ${name?cap_first}Model findById(String id){
                return ${name}Respository.findOne(id);
        }
}
