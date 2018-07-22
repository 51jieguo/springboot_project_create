package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.bean.ClassBean;
import cn.yangtengfei.createProject.api.bean.Module;
import cn.yangtengfei.createProject.api.bean.jar.Dependencie;
import cn.yangtengfei.createProject.api.bean.jar.Jar;
import cn.yangtengfei.createProject.api.util.FileUtil;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileCreateService {

    @Autowired
    private FileUtil fileUtil;

    public void createFile(SystemBean systemBean,Map<String,String> pathMap){
        createPom(systemBean,pathMap);
    }

    public void createPom(SystemBean systemBean,Map<String,String> pathMap){
        String rootPath = pathMap.get("rootPath");
        String apiRootPath = pathMap.get("apiRootPath");
        String serviceRootPath = pathMap.get("serviceRootPath");
        //createParentPom
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",systemBean.getName());
        map.put("version",systemBean.getVersion());

        List<String> childs = new ArrayList<String>();
        childs.add(systemBean.getName()+"-api");
        childs.add(systemBean.getName()+"-service");
        map.put("childs",childs);
        fileUtil.createFileFromFreemark(rootPath+ File.separator+"pom.xml","pom/pom.ftl",map);
        map.put("parentName",systemBean.getName());
        map.put("name",systemBean.getName()+"-service");
        map.put("childs",null);
        fileUtil.createFileFromFreemark(serviceRootPath+ File.separator+"pom.xml","pom/childPom.ftl",map);
        createService(systemBean,"service",pathMap);
        createModel(systemBean,"service",pathMap);

        childs = new ArrayList<String>();
        childs.add(systemBean.getName()+"-service");
        map.put("childs",childs);
        map.put("name",systemBean.getName()+"-api");

        List<Jar> jars = new ArrayList<>();
        List<Dependencie> dependencies = systemBean.getDependencies();
        for(Dependencie dependencie:dependencies){
            if("api".equals(dependencie.getName())){
                jars.addAll(dependencie.getJars());
            }
        }
        map.put("jars",jars);
        fileUtil.createFileFromFreemark(apiRootPath+ File.separator+"pom.xml","pom/childPom.ftl",map);

        createController(systemBean,"api",pathMap);
    }

    public void createModel(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();

        map.put("basePackage",systemBean.getBasePackage());

        List<Module> modules  = systemBean.getModules();
        if(modules!=null && modules.size()>0){
            for(Module module:modules){
                String name = module.getName();
                map.put("name",name);
                map.put("models",module.getModels());
                String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Model.java";
                //FileUtil.createFileByFtl(pathMap.get("beanDirecotryPath")+File.separator+fileName,path,"model.ftl",map);
                fileUtil.createFileFromFreemark(pathMap.get(pre+"modelPath")+File.separator+fileName,"java/model.ftl",map);
            }
        }
    }


    public void createController(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage().concat("api".equals(pre)?".api":""));
        List<Module> modules  = systemBean.getModules();
        if(modules!=null && modules.size()>0){
            for(Module module:modules) {
                String name = module.getName();
                List<ClassBean> classBeans = module.getClasses();
                for(ClassBean classBean:classBeans){
                    if("controller".equals(classBean.getPostffix())){
                        map.put("name",name);
                        map.put("methods",classBean.getMethods());
                        map.put("url",classBean.getUrl());
                        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Controller.java";
                        //FileUtil.createFileByFtl(pathMap.get("controllerDirecotryPath")+File.separator+fileName,path,"controllerClass.ftl",map);
                        fileUtil.createFileFromFreemark(pathMap.get(pre+"controllerPath")+File.separator+fileName,"java/controllerClass.ftl",map);
                    }
                }
            }
        }
    }

    public void createService(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        List<Module> modules  = systemBean.getModules();
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
                        fileUtil.createFileFromFreemark(pathMap.get(pre+"servicePath")+File.separator+fileName,"java/service.ftl",map);
                    }
                }
            }
        }
    }


}
