package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.Module;
import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.util.DirectoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DirecotryCreateService {


    @Autowired
    private Config config;


    public Map<String,String> createDirecotry(SystemBean systemBean) throws IOException {
        Map<String,String> pathMap = new HashMap<String,String>();
        String rootDirectoryPath = config.getRootPath(systemBean.getName());
        createDirectoryAndSetPathIntoMap(pathMap,"rootPath",rootDirectoryPath);

        //创建API 项目
        String apiPath = rootDirectoryPath + File.separator+systemBean.getName()+"-api";
        createDirectoryAndSetPathIntoMap(pathMap,"apiRootPath",apiPath);
        //String pre,String rootPath,SystemBean systemBean,Map<String,String> pathMap
        createCildDirectory("api",apiPath,systemBean,pathMap);

        //创建service项目
        String servicePath = rootDirectoryPath + File.separator+systemBean.getName()+"-service";
        createDirectoryAndSetPathIntoMap(pathMap,"serviceRootPath",servicePath);
        createCildDirectory("service",servicePath,systemBean,pathMap);
        return pathMap;
    }

    public void createCildDirectory(String pre,String rootPath,SystemBean systemBean,Map<String,String> pathMap){
        String mainPath = rootPath + File.separator+"src"+File.separator+"main";
        String javaPath =mainPath+File.separator+"java";
        createDirectoryAndSetPathIntoMap(pathMap,pre+"javaPath",javaPath);

        String basePackagePath = javaPath+File.separator+File.separator+systemBean.getBasePackage().replace(".","/");
        List<Module> moduleList = systemBean.getModules();
        if("api".equals(pre)){
            String reourcesPath =mainPath+File.separator+"resources";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"reourcesPath",reourcesPath);
            String testPath =mainPath+File.separator+"test";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"testPath",testPath);
            basePackagePath = basePackagePath +File.separator+pre;

            String controllerPath = basePackagePath + File.separator +"controller";
            String viewPath = basePackagePath + File.separator +"view";
            String configPath = basePackagePath + File.separator +"config";
            String servicePath = basePackagePath + File.separator +"service";

            createDirectoryAndSetPathIntoMap(pathMap,pre+"basePackagePath",basePackagePath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"controllerPath",controllerPath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"viewPath",viewPath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"configPath",configPath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"servicePath",servicePath);

            //创建view 文件夹
            for(Module module:moduleList){


                String tempPathKey = pre+module.getName()+"viewPath";
                String tempPathPath = viewPath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);

                tempPathKey = pre+module.getName()+"servicePath";
                tempPathPath = servicePath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);

                tempPathKey = pre+module.getName()+"controllerPath";
                tempPathPath = controllerPath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);
            }
            String tempPathKey = pre+"common"+"viewPath";
            String tempPathPath = viewPath + File.separator + "common";
            createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);


        }else{

            String modelPath = basePackagePath + File.separator +"model";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"modelPath",modelPath);
            String servicePath = basePackagePath + File.separator +"service";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"servicePath",servicePath);
            String repositoryPath = basePackagePath + File.separator +"repository";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"repositoryPath",repositoryPath);
            String configPath = basePackagePath + File.separator +"config";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"configPath",configPath);

            for(Module module:moduleList){
                String tempPathKey = pre+module.getName()+"modelPath";
                String tempPathPath = modelPath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);

                tempPathKey = pre+module.getName()+"servicePath";
                tempPathPath = servicePath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);

                tempPathKey = pre+module.getName()+"repositoryPath";
                tempPathPath = repositoryPath + File.separator + module.getBusinessPackage();
                createDirectoryAndSetPathIntoMap(pathMap,tempPathKey,tempPathPath);
            }
        }
    }


    public Map<String,String> createDirectoryAndSetPathIntoMap(Map<String,String> map,String key,String path){
        DirectoryUtils.createDirectory(path);
        map.put(key,path);
        return map;
    }
}
