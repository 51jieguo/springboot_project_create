package cn.yangtengfei.createProject.api.bean;

import lombok.Data;

import java.util.List;

@Data
public class Module {

    private String name;

    private String businessPackage;

    private String dbName;

    List<Model> models;

    List<ClassBean> classes;
}
