package ${basePackage}.bean;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class ${name?cap_first}Model implements Serializable{

    <#list models as model>
    private ${model.type} ${model.name};
    </#list>
}
