package ${basePackage}.api.view.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class PageResultModel<T>  implements Serializable{

        private long total = 0;

        private List<T> rows;

        private Object otherData;

}
