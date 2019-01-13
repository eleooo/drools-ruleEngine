package com.jy.modules.drools.service;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("com.jy.modules.drools.service.DroolsService")
public class DroolsService {
    private static Logger logger = LoggerFactory.getLogger(DroolsService.class);
    /**
     * @methodName: executeStatelessKSRule
     * @param: [kSessionName, factObjList, globalVariable]
     * @describe: 执行无状态session规则(支持设置多个全局变量)
     * @auther: dongdongchen
     * @date: 2018/12/28
     * @time: 18:22
     **/
    public boolean executeStatelessKSRule(String kSessionName, List <Object> factObjList, Map <String, Object> globalVariable){
        boolean  feedBack = false;
        try {
            //使用kieservice创建新的KieContainers,
            //KieContainer对所有的业务资产(规则、流程、电子表格、PMML文档等)都有引用，当我们创建新的规则引擎实例时，这些资产将被加载
            KieContainer kContainer = KieServices.Factory.get().getKieClasspathContainer();
            //verify()方法将允许我们确保我们的业务资产是正确的
            Results results = kContainer.verify();
            results.getMessages().stream().forEach((message) -> {
                logger.info(">> Message ( " + message.getLevel() + " ): " + message.getText());
            });
            //与KIESession相反，StatelessKIESession隔离了每次与规则引擎的交互，不会维护会话的状态。
            StatelessKieSession ksession = kContainer.newStatelessKieSession(kSessionName);
            //遍历全局变量，并加入KieSession中
            if(globalVariable != null && globalVariable.size()>0){
                Iterator <Map.Entry <String, Object>> it = globalVariable.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry <String, Object> entry = (Map.Entry <String, Object>) it.next();
                    ksession.setGlobal((String) entry.getKey(), entry.getValue());
                }
            }
            //设置事实对象集合，并执行相应规则
            ksession.execute(factObjList);
            feedBack = true;
        } catch (Exception e) {
            feedBack = false;
            logger.error("执行无状态session规则异常,kSessionName:{}",kSessionName,e);
        }
        return feedBack;
    }


    /**
     * @methodName: executeStatefulKSRule
     * @param: [kSessionName, factObjList, globalVariable]
     * @describe: 执行有状态session规则
     * @auther: dongdongchen
     * @date: 2018/12/26
     * @time: 19:30
     **/
    public boolean executeStatefulKSRule(String kSessionName, List <Object> factObjList,Map <String, Object> globalVariable) {
        boolean  feedBack = false;
        KieSession ksession = null;
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kContainer = kieServices.getKieClasspathContainer();
            //KIESession 会在多次与规则引擎进行交互中，维护会话的状态。
            ksession = kContainer.newKieSession(kSessionName);
            //遍历事实对象集合，并加入KieSession中
            Iterator var3 = factObjList.iterator();
            while (var3.hasNext()) {
                Object object = var3.next();
                ksession.insert(object);
            }
            //遍历全局变量，并加入KieSession中
            //遍历每一个全局变量，并加入KieSession中
            if(globalVariable!= null && globalVariable.size()>0){
                Iterator <Map.Entry <String, Object>> it = globalVariable.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry <String, Object> entry = (Map.Entry <String, Object>) it.next();
                    ksession.setGlobal((String) entry.getKey(), entry.getValue());
                }
            }
            //执行规则
            ksession.fireAllRules();
            feedBack = true;
        } catch (Exception e) {
            feedBack = false;
            logger.error("执行有状态session规则异常,kSessionName:{}", kSessionName,e);
        } finally {
            //关闭当前session的资源
            if(ksession!=null){
                ksession.dispose();
            }
        }
        return feedBack;
    }
}
