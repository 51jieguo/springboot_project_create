package cn.yangtengfei.createProject.api.bean;

import cn.yangtengfei.createProject.api.util.excel.Attribute;
import lombok.Data;

import java.util.List;

@Data
public class Module {

    private String name;

    List<Model> models;

    List<ClassBean> classes;
}
