package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.util.DirectoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
        if("api".equals(pre)){
            String reourcesPath =mainPath+File.separator+"reources";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"reourcesPath",reourcesPath);
            String testPath =mainPath+File.separator+"test";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"testPath",testPath);
            basePackagePath = basePackagePath +File.separator+pre;

            String controllerPath = basePackagePath + File.separator +"controller";
            String viewPath = basePackagePath + File.separator +"view";
            String configPath = basePackagePath + File.separator +"config";
            String servicePath = basePackagePath + File.separator +"service";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"controllerPath",controllerPath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"viewPath",viewPath);
            createDirectoryAndSetPathIntoMap(pathMap,pre+"configPath",configPath);

            createDirectoryAndSetPathIntoMap(pathMap,pre+"servicePath",servicePath);
        }else{
            String servicePath = basePackagePath + File.separator +"service";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"servicePath",servicePath);
            String modelPath = basePackagePath + File.separator +"model";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"modelPath",modelPath);
            String repositoryPath = basePackagePath + File.separator +"repository";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"repositoryPath",repositoryPath);
            String configPath = basePackagePath + File.separator +"config";
            createDirectoryAndSetPathIntoMap(pathMap,pre+"configPath",configPath);

        }



    }


    public Map<String,String> createDirectoryAndSetPathIntoMap(Map<String,String> map,String key,String path){
        DirectoryUtils.createDirectory(path);
        map.put(key,path);
        return map;
    }
    /*public void createFile(ClassBean classBean) throws IOException {
        String rootPath = config.getRootPath(projectName);
        System.out.println(rootPath);

        boolean isHasChild = false;
        //创建根目录
        DirectoryUtils.createDirectory(rootPath);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("package",basePackage);
        map.put("projectName",projectName);
        String parentRootPath = config.getRootPath(projectName);

        String freemarkDirecotry = config.getfreemarkDirecotryh();
        if(isHasChild){
            //TODO:有子项目处理
            map.put("packageType","pom");
        }else{
            map.put("packageType","jar");
            //创建main目录
            String mainPath  = config.getMainPath(parentRootPath);
            DirectoryUtils.createDirectory(config.getJavePath(mainPath));
            String resourcePath =config.getResourcesPath(mainPath);
            DirectoryUtils.createDirectory(resourcePath);
            DirectoryUtils.createDirectory(config.getTestPath(mainPath));

            String bussinessPath =config.getBaseBusinessPath(mainPath,basePackage);
            //创建controller service
            String controllerPath = bussinessPath + File.separator + "controller";
            String servicePath = bussinessPath + File.separator + "service";
            DirectoryUtils.createDirectory(controllerPath,servicePath);
            //创建properties
            String propertiesPath=config.propertiesPath(resourcePath);
            FileUtil.createFileByFtl(propertiesPath,freemarkDirecotry,map);

        }
        String pomPath = config.pomPath(rootPath);
        FileUtil.createFileByFtl(pomPath,freemarkDirecotry,map);
    }*/



}
