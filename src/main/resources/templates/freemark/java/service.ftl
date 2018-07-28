package ${basePackage}.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ${name?cap_first}Service {

        <#list methods as method>
        public ${method.returnType} ${method.name}${name?cap_first}(<#list method.params as param>String ${param.name}<#if param_has_next>,</#if></#list>){
			
        }

        </#list>
}
