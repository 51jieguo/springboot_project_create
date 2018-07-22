package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.java.ClassBean;
import cn.yangtengfei.createProject.api.bean.java.Module;
import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.util.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class InitService {

    @Autowired
    private DirecotryCreateService direcotryCreateService;

    @Autowired
    private Config config;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileCreateService fileCreateService;


    public void initSystemConfig(String configJson){
        SystemBean systemBean = JSON.parseObject(configJson,SystemBean.class);
        log.info(JSON.toJSONString(systemBean));
        //String basePackage = "com."+systemBean.getCompany()+"."+systemBean.getName();
        //systemBean.setBasePackage(basePackage);
        try {
            Map<String,String> pathMap = direcotryCreateService.createDirecotry(systemBean);

            fileCreateService.createPom(systemBean,pathMap);


            //fileUtil.createFileFromFreemark(config.getPomPath(pathMap.get("rootDirectoryPath")),"fatherPom.ftl",systemBean);
            //createModel(systemBean,pathMap);
            //createController(systemBean,pathMap);
            //createService(systemBean,pathMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createService(SystemBean systemBean,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        List<Module> modules  = systemBean.getModules();
        String path = config.getfreemarkDirecotry();
        if(modules!=null && modules.size()>0){
            for(Module module:modules) {
                String name = module.getName();
                List<ClassBean> classBeans = module.getClasses();
                for(ClassBean classBean:classBeans){
                    if("service".equals(classBean.getPostffix())){
                        map.put("name",name);
                        map.put("methods",classBean.getMethods());
                        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Service.java";
                        //FileUtil.createFileByFtl(pathMap.get("controllerDirecotryPath")+File.separator+fileName,path,"controllerClass.ftl",map);
                        fileUtil.createFileFromFreemark(pathMap.get("serviceDirecotryPath")+File.separator+fileName,"service.ftl",map);
                    }
                }
            }
        }
    }



    public void createModel(SystemBean systemBean,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();

        map.put("basePackage",systemBean.getBasePackage());

        List<Module> modules  = systemBean.getModules();
        String path = config.getfreemarkDirecotry();
        if(modules!=null && modules.size()>0){
            for(Module module:modules){
                String name = module.getName();
                map.put("name",name);
                map.put("models",module.getModels());
                String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Model.java";
                //FileUtil.createFileByFtl(pathMap.get("beanDirecotryPath")+File.separator+fileName,path,"model.ftl",map);
                fileUtil.createFileFromFreemark(pathMap.get("beanDirecotryPath")+File.separator+fileName,"model.ftl",map);
            }
        }
    }

    //@PostConstruct
    /*public void createProject() throws IOException {

        String projectName = "tensflowProject";
        String companyName="yangtengfei";
        String basePackage = "com."+companyName+"."+projectName;

        BaseBean baseBean = new BaseBean();
        baseBean.setProjectName(projectName);
        baseBean.setCompanyName(companyName);
        baseBean.setBasePackage(basePackage);

        ClassBean classBean = new ClassBean();
        classBean.setBaseBean(baseBean);

        //create project base directory
        Map<String,String> pathMap = direcotryCreateService.createDirecotry(classBean);

        //createprojectbaseFile
        fileCreateService.creatPom(pathMap,classBean);

        List<MethodBean> methodBeanList  = new ArrayList<MethodBean>();
        String name ="user";
        methodBeanList.add(getMethodBean("update"));
        methodBeanList.add(getMethodBean("insert"));
        methodBeanList.add(getMethodBean("delete"));
        methodBeanList.add(getMethodBean("find"));
        classBean.setClassName(name);
        classBean.setMethodBeanList(methodBeanList);
        fileCreateService.createController(pathMap,classBean);
    }

    public MethodBean getMethodBean(String name){
        MethodBean methodBean = new MethodBean();
        methodBean.setMethodName(name);
        methodBean.setUrl("/"+name);
        List<ParamBean> paramBeanList = new ArrayList<>();
        ParamBean paramBean = new ParamBean();
        paramBean.setName("id");
        paramBean.setType("int");
        paramBeanList.add(paramBean);
        paramBean = new ParamBean();
        paramBean.setName("name");
        paramBean.setType("String");
        paramBeanList.add(paramBean);
        methodBean.setParamBeanList(paramBeanList);
        return methodBean;
    }*/
}
