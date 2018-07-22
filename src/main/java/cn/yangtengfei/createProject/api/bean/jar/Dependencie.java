package cn.yangtengfei.createProject.api.bean.jar;

import lombok.Data;

import java.util.List;


@Data
public class Dependencie {

    private String name;

    List<Jar> jars;
}
