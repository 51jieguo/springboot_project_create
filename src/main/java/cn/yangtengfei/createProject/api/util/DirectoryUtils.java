package cn.yangtengfei.createProject.api.util;

import java.io.File;
import java.io.IOException;

public class DirectoryUtils {

    public static void createDirectory(String ...paths){
        for(String path:paths){
            File file = new File(path);
            file.mkdirs();
        }
    }

    public static void createFile(String... paths)  {

        for(String path:paths){
            File f = new File(path);
            if(!f.exists()){
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
