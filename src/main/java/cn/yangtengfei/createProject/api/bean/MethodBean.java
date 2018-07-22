package cn.yangtengfei.createProject.api.bean;

import lombok.Data;

import java.util.List;

@Data
public class MethodBean {

    private String url;

    private String name;

    private List<ParamBean> params;

    private String returnType;
}
