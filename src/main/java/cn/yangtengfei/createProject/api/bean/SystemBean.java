package cn.yangtengfei.createProject.api.bean;

import lombok.Data;

import java.util.List;

@Data
public class SystemBean {

    private String name;

    private String company;

    private String domain;

    List<Module> modules;

    private BaseBean root;

    private String basePackage;

    private String version;


}
