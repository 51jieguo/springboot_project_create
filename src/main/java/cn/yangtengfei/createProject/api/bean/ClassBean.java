package cn.yangtengfei.createProject.api.bean;


import lombok.Data;

import java.util.List;

@Data
public class ClassBean {

    private String url;

    private String postffix;

    private String className;

    private String packagePath;

    private List<MethodBean> methods;

}
