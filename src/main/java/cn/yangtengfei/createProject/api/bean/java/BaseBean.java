package cn.yangtengfei.createProject.api.bean.java;


import lombok.Data;

@Data
public class BaseBean {


    private String projectName;

    private String basePackage;

    private String companyName;

    private String packageType="jar";
}
