package ${basePackage}.api.controller.${bussinessPackage};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ${basePackage}.api.view.${bussinessPackage}.${name?cap_first}View;
import ${basePackage}.service.${bussinessPackage}.${name?cap_first}Service;
import ${basePackage}.api.service.${bussinessPackage}.*;
import ${basePackage}.api.view.common.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
@RequestMapping(value = "${url}/${name}")
public class ${name?cap_first}Controller {

        @Autowired
        private ${name?cap_first}Service ${name}Service;

        @Autowired
        private ${name?cap_first}LogicService ${name}LogicService;

        @RequestMapping(value="/insert",method = RequestMethod.POST)
        public RestResult insert(@ModelAttribute ${name?cap_first}View ${name}View, HttpServletRequest request){
                RestResult  restResult = new RestResult();
                ${name}LogicService.insert(${name}View);
                return restResult;
        }

        @RequestMapping(value="/update",method = RequestMethod.POST)
        public RestResult update(@ModelAttribute ${name?cap_first}View ${name}View, HttpServletRequest request){
                RestResult  restResult = new RestResult();
                ${name}LogicService.update(${name}View);
                return restResult;
        }

        @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
        public RestResult delete(@PathVariable("id")String id){
                RestResult  restResult = new RestResult();
                ${name}LogicService.delete(id);
                return restResult;
        }

        @RequestMapping(value="/findById/{id}",method = RequestMethod.GET)
        public RestResult findById(@PathVariable("id")String id){
                RestResult  restResult = new RestResult();
                ${name?cap_first}View ${name}View = ${name}LogicService.findById(id);
                return restResult;
        }

        @RequestMapping(value = "/page", method = RequestMethod.GET)
        public PageResultModel findAll(HttpServletRequest request, Integer pageNumber , Integer pageSize) {
                int index = pageNumber-1;
                PageResultModel pageResultModel = ${name}LogicService.findByDeleteFlgOrderByUpdateTimeDesc(0, index, pageSize);
                return  pageResultModel;
        }
}
