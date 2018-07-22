package cn.yangtengfei.createProject.api.config;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Component
public class Config {


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }


    @Value("${freemarkDirecotry:}")
    public String freemarkDirecotry;

    @Autowired
    private FreeMarkerProperties freeMarkerProperties;


    public String getExceDirecotryh(){
        if(StringUtils.isBlank(freemarkDirecotry)){
            return this.getClass().getResource("/templates").getPath()+"/excel";
        }
        return freemarkDirecotry;
    }


    public String getfreemarkDirecotry(){
        if(StringUtils.isBlank(freemarkDirecotry)){
            return freeMarkerProperties.getTemplateLoaderPath()[0]+"/freemark";
        }
        return freemarkDirecotry;
    }
    /**
     * 项目路径
     *
     * @param projectName
     * @return
     */
    public String getRootPath(String projectName) {
        return this.getClass().getResource("/").getPath()+"temp"+File.separator  + projectName;
    }

    public String getMainPath(String rootPath) {
        return rootPath + File.separator + "src" + File.separator + "main";
    }

    public String getJavePath(String mainPath) {
        return mainPath + File.separator + "java";
    }

    public String getResourcesPath(String mainPath) {
        return mainPath + File.separator + "resources";
    }

    public String getTestPath(String mainPath) {
        return mainPath + File.separator + "test";
    }

    public String getPomPath(String path) {
        return path + File.separator + "pom.xml";
    }

    public String propertiesPath(String path) {
        return path + File.separator + "application.properties";
    }

    public String getBaseBusinessPath(String javaPath,String basePackage){
        return javaPath+ File.separator+ basePackage.replace(".","/");
    }

    public String getControllerDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"controller";
    }

    public String getServiceDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"service";
    }


    public String getRespositoryDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"respository";
    }

    public String getConfigDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"config";
    }

    public String getBeanDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"bean";
    }

    public String getViewDirecotoryPath(String basePakagePath){
        return basePakagePath+File.separator+"view";
    }


}
