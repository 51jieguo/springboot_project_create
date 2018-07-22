package cn.yangtengfei.createProject.api.controller;

import cn.yangtengfei.createProject.api.service.ExcelAnalyzeService;
import cn.yangtengfei.createProject.api.service.InitService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class IndexController {


    @Autowired
    private ExcelAnalyzeService excelAnalyzeService;

    @Autowired
    private InitService initService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String  index(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException, TemplateException {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/fileUploadExcel")
    public String fileUploadExcel(@RequestParam("fileName") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();

        String extString = fileName.substring(fileName.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        excelAnalyzeService.readExcel(inputStream,extString);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();

        //String extString = fileName.substring(fileName.lastIndexOf("."));
        //InputStream inputStream = file.getInputStream();
        //excelAnalyzeService.readExcel(inputStream,extString);
        //BufferedReader reader = new BufferedInputStream(file.getInputStream());

        InputStreamReader isr=new InputStreamReader(file.getInputStream(),"utf8");

        BufferedReader br=new BufferedReader(isr);

        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while((line=br.readLine()) != null){
            stringBuffer.append(line);
        }
        String result  = stringBuffer.toString();

        initService.initSystemConfig(result);

        return result;
    }

}
