package ${basePackage}.api.view.${bussinessPackage};


import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class ${name?cap_first}View implements Serializable{

    <#list models as model>
    private ${model.type} ${model.name};

    </#list>

}
