package ${basePackage}.api.service.${bussinessPackage};


import ${basePackage}.api.view.${bussinessPackage}.${name?cap_first}View;
import ${basePackage}.service.${bussinessPackage}.${name?cap_first}Service;
import ${basePackage}.model.${bussinessPackage}.${name?cap_first}Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import ${basePackage}.api.view.common.*;
import org.springframework.data.domain.Page;

@Slf4j
@Service
public class ${name?cap_first}LogicService {

        @Autowired
        private ${name?cap_first}Service ${name}Service;

        public void insert(${name?cap_first}View ${name}View){
            ${name?cap_first}Model ${name}Model = new ${name?cap_first}Model();
            BeanUtils.copyProperties(${name}View,${name}Model);
            ${name}Service.insert(${name}Model);
        }

        public void update(${name?cap_first}View ${name}View){
            ${name?cap_first}Model ${name}Model = new ${name?cap_first}Model();
            BeanUtils.copyProperties(${name}View,${name}Model);
            ${name}Service.update(${name}Model);
        }

        public void delete(String id){
            ${name}Service.delete(id);
        }

        public void delete(${name?cap_first}View ${name}View){
            ${name?cap_first}Model ${name}Model = new ${name?cap_first}Model();
            BeanUtils.copyProperties(${name}View,${name}Model);
            ${name}Service.delete(${name}Model);
        }

        public ${name?cap_first}View findById(String id){
            ${name?cap_first}Model ${name}Model = ${name}Service.findById(id);
            ${name?cap_first}View ${name}View = null;
            if(${name}Model!=null){
                ${name}View = new ${name?cap_first}View();
                BeanUtils.copyProperties(${name}Model,${name}View);
            }
            return ${name}View;
        }

        public PageResultModel findByDeleteFlgOrderByUpdateTimeDesc(Integer deleteFlg, int page, int pageSize){
            PageResultModel pageResultModel = new PageResultModel();
            Page<${name?cap_first}Model> ${name}Page = ${name}Service.findByDeleteFlgOrderByUpdateTimeDesc(deleteFlg,page,pageSize);
            pageResultModel.setTotal(${name}Page.getTotalElements());
            pageResultModel.setRows(${name}Page.getContent());
            return pageResultModel;
        }
}
