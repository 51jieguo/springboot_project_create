package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.config.Config;
import cn.yangtengfei.createProject.api.service.normalDefine.NormalFileCreateService;
import cn.yangtengfei.createProject.api.util.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class InitService {

    @Autowired
    private DirecotryCreateService direcotryCreateService;

    @Autowired
    private Config config;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private NormalFileCreateService normalFileCreateService;


    public void initSystemConfig(String configJson){
        SystemBean systemBean = JSON.parseObject(configJson,SystemBean.class);
        log.info(JSON.toJSONString(systemBean));
        try {
            Map<String,String> pathMap = direcotryCreateService.createDirecotry(systemBean);

            //fileCreateService.createController(systemBean,"api",pathMap);
            //fileCreateService.createApplicationProperties(systemBean,"api",pathMap);
            //fileCreateService.createService(systemBean,"service",pathMap);
            //fileCreateService.createModel(systemBean,"service",pathMap);
            normalFileCreateService.createPom(systemBean,pathMap);
            normalFileCreateService.createClass(systemBean,pathMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
