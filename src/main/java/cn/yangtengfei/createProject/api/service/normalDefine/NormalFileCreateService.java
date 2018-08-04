package cn.yangtengfei.createProject.api.service.normalDefine;


import cn.yangtengfei.createProject.api.bean.ClassBean;
import cn.yangtengfei.createProject.api.bean.Model;
import cn.yangtengfei.createProject.api.bean.Module;
import cn.yangtengfei.createProject.api.bean.SystemBean;
import cn.yangtengfei.createProject.api.bean.jar.Dependencie;
import cn.yangtengfei.createProject.api.bean.jar.Jar;
import cn.yangtengfei.createProject.api.util.FileUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class NormalFileCreateService {

    @Autowired
    private FileUtil fileUtil;

    public void createPom(SystemBean systemBean, Map<String,String> pathMap){
        String rootPath = pathMap.get("rootPath");
        String apiRootPath = pathMap.get("apiRootPath");
        String serviceRootPath = pathMap.get("serviceRootPath");
        //createParentPom
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",systemBean.getName());
        map.put("version",systemBean.getVersion());

        List<String> childs = new ArrayList<String>();
        childs.add(systemBean.getName()+"-api");
        childs.add(systemBean.getName()+"-service");
        map.put("childs",childs);

        fileUtil.createFileFromFreemark(rootPath+ File.separator+"pom.xml","pom/pom.ftl",map);

        List<Dependencie> dependencies = systemBean.getDependencies();
        List<Jar> jars = new ArrayList<>();
        map.put("parentName",systemBean.getName());
        map.put("name",systemBean.getName()+"-service");

        for(Dependencie dependencie:dependencies){
            if("service".equals(dependencie.getName())){
                jars.addAll(dependencie.getJars());
            }
        }
        map.put("jars",jars);
        fileUtil.createFileFromFreemark(serviceRootPath+ File.separator+"pom.xml","pom/childPom.ftl",map);

        jars = new ArrayList<>();
        map.put("name",systemBean.getName()+"-api");
        for(Dependencie dependencie:dependencies){
            if("api".equals(dependencie.getName())){
                jars.addAll(dependencie.getJars());
            }
        }
        map.put("jars",jars);
        fileUtil.createFileFromFreemark(apiRootPath+ File.separator+"pom.xml","pom/childPom.ftl",map);
    }

    public void createClass(SystemBean systemBean,Map<String,String> pathMap){

        //api startProject
        startClass(systemBean,"api",pathMap);


        List<Module> modules  = systemBean.getModules();
        if(modules!=null && modules.size()>0){
            for(Module module:modules){
                createController(systemBean,module,"api",pathMap);
                createApplicationProperties(systemBean,"api",pathMap);
                createView(systemBean,module,"api",pathMap);
                createLogicService(systemBean,module,"api",pathMap);

                createModel(systemBean,module,"service",pathMap);
                createService(systemBean,module,"service",pathMap);
                createRepository(systemBean,module,"service",pathMap);


            }
        }

        createDbConfig(systemBean,modules,"service",pathMap);
        createCommonModel(systemBean,"service",pathMap);
        createCommonClass(systemBean,"api",pathMap);

    }

    public void startClass(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        String name = "application";
        map.put("name",name);
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+".java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+"basePackagePath")+File.separator+fileName,"java/Application.ftl",map);

    }


    public void createCommonClass(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        String name = "RestResult";
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",name);
        String fileName = name+".java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+"commonviewPath")+File.separator+fileName,"java/RestResult.ftl",map);

        name = "PageResultModel";
        map.put("name",name);
        fileName = name+".java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+"commonviewPath")+File.separator+fileName,"java/PageResultModel.ftl",map);

    }

    public void createView(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        String name = module.getName();
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",name);
        map.put("models",module.getModels());
        map.put("bussinessPackage",module.getBusinessPackage());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"View.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"viewPath")+File.separator+fileName,"java/view.ftl",map);
    }

    public void createController(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        String name = module.getName();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",name);
        map.put("url","/"+systemBean.getName());
        map.put("bussinessPackage",module.getBusinessPackage());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Controller.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"controllerPath")+File.separator+fileName,"java/controllerClass.ftl",map);
    }

    public void createLogicService(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        String name = module.getName();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",name);
        map.put("bussinessPackage",module.getBusinessPackage());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"LogicService.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"servicePath")+File.separator+fileName,"java/logicService.ftl",map);
    }


    public void createService(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        String name = module.getName();
        map.put("name",name);
        map.put("bussinessPackage",module.getBusinessPackage());
        map.put("dbName",module.getDbName());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Service.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"servicePath")+File.separator+fileName,"db/service.ftl",map);
    }


    public void createModel(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        String name = module.getName();
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        map.put("name",name);
        map.put("models",module.getModels());
        map.put("bussinessPackage",module.getBusinessPackage());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Model.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"modelPath")+File.separator+fileName,"db/mongo/model.ftl",map);
    }

    public void createCommonModel(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        String fileName = "BaseModel.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+"baseModel"+"modelPath")+File.separator+fileName,"db/baseModel/basemodel.ftl",map);
    }

    public void createRepository(SystemBean systemBean,Module module,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        map.put("basePackage",systemBean.getBasePackage());
        String name = module.getName();
        map.put("name",name);
        map.put("bussinessPackage",module.getBusinessPackage());
        map.put("dbName",module.getDbName());
        String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"Respository.java";
        fileUtil.createFileFromFreemark(pathMap.get(pre+module.getBusinessPackage()+"repositoryPath")+File.separator+fileName,"db/mongo/respository.ftl",map);
    }

    public void createDbConfig(SystemBean systemBean,List<Module> modules,String pre,Map<String,String> pathMap){
        String dbName = "";

        Set<String> dbNameSet = new HashSet<String>();
        int index = 0;
        for(Module module:modules){
            String name = module.getDbName();
            boolean isContains =  dbNameSet.contains(name);
            if(!isContains){
                index ++;
                Map<String,Object> map = new HashMap<>();
                map.put("basePackage",systemBean.getBasePackage());

                map.put("name",name);
                map.put("bussinessPackage",module.getBusinessPackage());
                map.put("dbName",module.getDbName());


                dbNameSet.add(name);

                System.out.println("index=============="+index);
                if(index==1){
                    map.put("isPrimary",true);
                }else{
                    map.put("isPrimary",false);
                }

                System.out.println(JSON.toJSONString(map));
                String fileName = name.substring(0,1).toUpperCase()+name.substring(1,name.length())+"MongoConfig.java";
                fileUtil.createFileFromFreemark(pathMap.get(pre+"configPath")+File.separator+fileName,"db/mongo/config.ftl",map);
            }
        }
    }





    public void createApplicationProperties(SystemBean systemBean,String pre,Map<String,String> pathMap){
        Map<String,Object> map = new HashMap<>();
        String reourcesPath = pathMap.get(pre+"reourcesPath");

        List<Module> moduleList = systemBean.getModules();
        Set<String> dbs = new HashSet<String>();
        moduleList.forEach(module -> dbs.add(module.getDbName()));
        map.put("dbs",dbs);
        fileUtil.createFileFromFreemark(reourcesPath+ File.separator+"application.properties","application.ftl",map);
    }
}
