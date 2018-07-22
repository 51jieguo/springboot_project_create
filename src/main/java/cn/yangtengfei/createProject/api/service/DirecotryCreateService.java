package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.ClassBean;
import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.util.DirectoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String mainDirectoryPath  = config.getMainPath(rootDirectoryPath);
        String javaDirectoryPath = config.getJavePath(mainDirectoryPath);
        String resourceDirectoryPath = config.getResourcesPath(mainDirectoryPath);
        String testDirectoryPath = config.getTestPath(mainDirectoryPath);
        String basePackageDirecotryPath = config.getBaseBusinessPath(javaDirectoryPath,systemBean.getBasePackage());
        String controllerDirecotryPath = config.getControllerDirecotoryPath(basePackageDirecotryPath);
        String serviceDirecotryPath = config.getServiceDirecotoryPath(basePackageDirecotryPath);
        String respositoryDirecotryPath = config.getRespositoryDirecotoryPath(basePackageDirecotryPath);
        String configDirecotryPath = config.getConfigDirecotoryPath(basePackageDirecotryPath);
        String beanDirecotryPath = config.getBeanDirecotoryPath(basePackageDirecotryPath);
        String viewDirecotryPath = config.getViewDirecotoryPath(basePackageDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"rootDirectoryPath",rootDirectoryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"mainDirectoryPath",mainDirectoryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"javaDirectoryPath",javaDirectoryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"resourceDirectoryPath",resourceDirectoryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"testDirectoryPath",testDirectoryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"basePackageDirecotryPath",basePackageDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"controllerDirecotryPath",controllerDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"serviceDirecotryPath",serviceDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"respositoryDirecotryPath",respositoryDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"configDirecotryPath",configDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"beanDirecotryPath",beanDirecotryPath);
        createDirectoryAndSetPathIntoMap(pathMap,"viewDirecotryPath",viewDirecotryPath);
        return pathMap;
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
