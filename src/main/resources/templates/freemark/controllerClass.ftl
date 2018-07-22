package cn.yangtengfei.api.controller;

import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "${url}/${name}")
@Slf4j
public class ${name?cap_first}Controller {

        <#list methods as method>
        @RequestMapping(value="${method.url}<#list method.params as param>/{${param.name}}</#list>",method = RequestMethod.GET)
        public ${method.returnType} ${method.name}${name?cap_first}(<#list method.params as param>@PathVariable("${param.name}")String ${param.name} <#if param_has_next>,</#if></#list>){
			
        }

        </#list>
}
