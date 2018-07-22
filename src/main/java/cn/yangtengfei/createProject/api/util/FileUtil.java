package cn.yangtengfei.createProject.api.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Component
public class FileUtil {

    @Autowired
    private Configuration configuration;

    public  void createFileFromFreemark(String aimFilePath,String freemarkName,Object object){
        try {
            Template template = configuration.getTemplate("freemark/"+freemarkName);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, object);
            FileWriter fileWriter = new FileWriter(new File(aimFilePath));
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    public static  void createFileByFtl(String aimFilePath,String freemarkDirecotry,String freemarkName,Object object){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File(freemarkDirecotry));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
            Template temp = cfg.getTemplate(freemarkName);
            File file = new File(aimFilePath);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(object, bw);
            bw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
