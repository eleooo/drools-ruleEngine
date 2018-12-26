package com.jy.modules.drools.service;

import com.jy.modules.drools.domain.Person;
import com.jy.modules.drools.domain.Sale;
import com.jy.modules.drools.enumdef.RuleErrorEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.type.FactType;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("com.jy.modules.drools.service.DroolsService2")
public class DroolsService2 {

    private static final String TARGET_DIR = "drools.targetDir";

    private static final String FILE_SUFFIX = "drools.fileSuffix";

    private static Logger logger = LoggerFactory.getLogger(DroolsService2.class);

    @Autowired
    private Environment env;


    /**
     * @methodName: executeRuleFile
     * @param: [ruleFileName, inputList]
     * @describe: 执行规则文件
     * @auther: dongdongchen
     * @date: 2018/12/12
     * @time: 21:17
     **/
    public String executeRuleFile(String ruleFileName,List<Object> inputList,Map<String, Object> globalMap) throws IllegalAccessException, InstantiationException, InterruptedException {
        //创建一个KnowledgeBuilder，保存资源文件
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        String targetDir = env.getProperty(TARGET_DIR);//从Zookeeper配置中心获取规则文件存放目录
        String fileSuffix = env.getProperty(FILE_SUFFIX);//从Zookeeper配置中心获取规则文件后缀
        //添加规则资源到KnowledgeBuilder
        //解决规则文件乱码问题:设置解析编码格式
        Resource resource = ResourceFactory.newClassPathResource(targetDir + ruleFileName+ fileSuffix,"utf-8",DroolsService2.class);
        kBuilder.add(resource, ResourceType.DRL);
        //校验规则语法:输出错误信息
        String errorTip = checkRuleGrammarLegitimacy(kBuilder);
        if(StringUtils.isNotEmpty(errorTip)){
            return errorTip;
        }
        /**
         * 根据传入的类型来决定下一步的操作。
         * --DRL 若是规则文件，则会将这些规则文件进行解析、编译和注册，经过一系列的工作后，将会将这些规则的相关内容转换为一个注册包 (PackageRegistry)对象，
         *   并且在KnowledgeBuilder中会有一个Map来缓存这些PackageRegistry实例，在使用KnowledgeBuilder的getKnowledgePackages方法时，会使用全部
         *   PackageRegistry实例来创建一个知识包(KiePackage)集合返回。
         **/
        //获取知识包集合
        Collection <KiePackage> pkgs = kBuilder.getKnowledgePackages();
        //创建KnowledgeBase实例，保存全部应用的知识定义(业务规则定义)
        final InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        //将知识包部署到KnowledgeBase中
        kbase.addPackages(pkgs);
        if("typeDeclare-rules".equals(ruleFileName)){
            inputList = new ArrayList <Object>();
            //使用包名和类名获取事实对象类型
            FactType fact = kbase.getFactType("org.drools.domain", "Person");
            Object obj = fact.newInstance();
            // 设置名称
            fact.set(obj, "name", "crazyit");
            // 设置年龄
            fact.set(obj, "age", 20);
            // 设置体重
            fact.set(obj, "weight", new BigDecimal(70));
            inputList.add(obj);
        }else if("typeDeclareExtends-rules".equals(ruleFileName)){
            inputList = new ArrayList <Object>();
            //使用包名和类名获取事实对象类型
            FactType fact = kbase.getFactType("org.drools.domain", "Programmer");
            Object obj = fact.newInstance();
            // 设置名称
            fact.set(obj, "name", "crazyit");
            // 设置年龄
            fact.set(obj, "age", 33);
            // 设置体重
            fact.set(obj, "weight", new BigDecimal(70));
            fact.set(obj, "company", "疯狂Java联盟");
            inputList.add(obj);
        }else if("typeMetadata-rules".equals(ruleFileName)){
            inputList = new ArrayList <Object>();
            //使用包名和类名获取事实对象类型
            FactType fact = kbase.getFactType("org.drools.domain", "Person");
            Object obj = fact.newInstance();
            // 设置名称
            fact.set(obj, "name", "crazyit");
            // 设置年龄
            fact.set(obj, "age", 33);
            inputList.add(obj);
        }else if("funcQuery-rules".equals(ruleFileName)){
            inputList = new ArrayList <Object>();
            for (int i = 1; i <300000 ; i++) {
                Random  ramdom = new Random();
                int age = ramdom.nextInt(80);
                // 定义事实对象
                Person person= new Person("p"+i,age, new BigDecimal(50+i));
                // 插入到Working Memory
                inputList.add(person);
            }
        }else if("function-rules".equals(ruleFileName)){
            inputList = new ArrayList <Object>();
            Sale  sale1 = new Sale(new Date(),10,30,"001");
            Sale  sale2 = new Sale(new Date(),10,20,"002");
            inputList.add(sale1);
            inputList.add(sale2);
        }
        //使用KnowledgeBase创建KieSession(保存运行时的数据)
        KieSession kSession = kbase.newKieSession();

        //定义一个事实对象集合
        if(CollectionUtils.isNotEmpty(inputList)){
            //遍历每一个事实对象
            for (Iterator<Object> localIterator = inputList.iterator(); localIterator.hasNext();) {
                Object obj = localIterator.next();
                //向KieSession中加入事实
                kSession.insert(obj);
            }
        }
        //设置全局变量
        if (!"funcQuery-rules".equals(ruleFileName) && (null!=globalMap &&globalMap.size()>0)) {
            Iterator<Map.Entry<String, Object>> it = globalMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                kSession.setGlobal((String) entry.getKey(), entry.getValue());
            }
        }
        if(!"funcQuery-rules".equals(ruleFileName)){
            //执行规则
            kSession.fireAllRules();
        }
        //执行查询
        if("funcQuery-rules".equals(ruleFileName)){
            // 执行查询
            QueryResults results = kSession.getQueryResults( "Age between", new Object[]{25, 30} );
            System.out.println("查询结果：" + results.size());
            // 输出结果数据
            for (QueryResultsRow row : results) {
                Person p = (Person)row.get("p");
                System.out.println("名称：" + p.getName() + ", 年龄：" + p.getAge());
            }
        }else if("globalProperty-rules".equals(ruleFileName)){
            List<Person> maxThan30List = (List<Person>)kSession.getGlobal("maxThan30");
            errorTip = "执行规则后的结果数量:"+maxThan30List.size();
        }
        // 关闭当前session的资源
        kSession.dispose();
        return errorTip;
    }

     /**
      * @methodName: checkRuleGrammarLegitimacy
      * @param: []
      * @describe: 校验规则语法合法性
      * @auther: dongdongchen
      * @date: 2018/12/12
      * @time: 18:05
      **/
      public String checkRuleGrammarLegitimacy(KnowledgeBuilder kBuilder) {
          String errorTip = "";
          //校验规则语法:输出错误信息
          if (kBuilder.hasErrors()) {
              KnowledgeBuilderErrors kbuidlerErrors = kBuilder.getErrors();
              StringBuffer errorsSbf = new StringBuffer();
              for (Iterator iter = kbuidlerErrors.iterator(); iter.hasNext(); ) {
                  String errorInfo = iter.next().toString();
                  logger.error("执行规则编译异常,{}", errorInfo);
                  if (errorInfo.contains("[ERR")) {
                    int startIndex = errorInfo.indexOf("[ERR");
                    String errorCode = errorInfo.substring(startIndex, startIndex+9);
                    String errorMsg =  RuleErrorEnum.getValueByKey(errorCode);
                    if(StringUtils.isEmpty(errorMsg)){
                        errorMsg = errorCode;
                    }
                    errorsSbf.append(errorMsg).append(";");
                  }
              }
              if (errorsSbf != null && errorsSbf.length() > 0) {
                  errorTip = errorsSbf.toString();
              } else {
                  errorTip = "规则语法错误";
              }
          }
          return errorTip;
      }
}
