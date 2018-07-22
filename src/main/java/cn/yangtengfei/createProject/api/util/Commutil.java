package cn.yangtengfei.createProject.api.util;

import org.apache.commons.lang3.StringUtils;

public class Commutil {

    public static String converObjectToString(Object object){
        if(object==null){
            return StringUtils.EMPTY;
        }
        return String.valueOf(object);
    }
}
