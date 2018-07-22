package cn.yangtengfei.createProject.api.bean;

import cn.yangtengfei.createProject.api.bean.jar.Dependencie;
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

    private List<Dependencie> dependencies;


}
