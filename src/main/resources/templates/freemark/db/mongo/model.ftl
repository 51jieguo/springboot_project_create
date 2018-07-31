package ${basePackage}.model.${bussinessPackage};


import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;


@Document(collection = "${name}")
public class ${name?cap_first}Model implements Serializable{

    <#list models as model>
    private ${model.type} ${model.name};

    </#list>

    <#list models as model>
    public ${model.type} get${model.name?cap_first}() {
        return ${model.name};
    }

    public void set${model.name?cap_first}(${model.type} ${model.name}) {
        this.${model.name} = ${model.name};
    }

    </#list>
}
