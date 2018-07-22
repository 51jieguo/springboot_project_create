package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.ClassBean;
import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Slf4j
@Service
public class FileCreateService {

    @Autowired
    private Config config;

    public void creatPom(Map<String,String> pathMap, SystemBean systemBean){
        String freemarkDirecotry =   config.getfreemarkDirecotry();
        FileUtil.createFileByFtl(config.getPomPath(pathMap.get("rootDirectoryPath")),freemarkDirecotry,"fatherPom.ftl",systemBean);
    }

    public void createController(Map<String,String> pathMap, ClassBean classBean){
        String path = config.getfreemarkDirecotry();
        //classBean.setBaseUrl("/v1/admin");
        String name=classBean.getClassName().substring(0)+classBean.getClassName().substring(1,classBean.getClassName().length())+"Controller.java";
        FileUtil.createFileByFtl(pathMap.get("controllerDirecotryPath")+File.separator+name,path,"controllerClass.ftl",classBean);
    }

    public void createService(){

    }

    public void createRespository(){

    }
}
