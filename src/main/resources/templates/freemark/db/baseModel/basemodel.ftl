package ${basePackage}.model.common;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class BaseModel implements Serializable{

    private Integer deleteFlg = 0;

    private String createUserId;

    private Date createTime = new Date();

    private String updateUserId;

    private Date updateTime = new Date();
}
