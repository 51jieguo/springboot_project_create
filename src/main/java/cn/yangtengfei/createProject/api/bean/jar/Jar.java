package cn.yangtengfei.createProject.api.bean.jar;

import lombok.Data;

import java.util.List;

@Data
public class Jar {

    private String artifactId;

    private String groupId;

    private String version;

    private List<Exclusion> exclusions;
}
