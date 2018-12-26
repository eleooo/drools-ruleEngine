package com.jy.modules.drools.service;

import com.jy.modules.drools.enumdef.RuleErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;

@Service("com.jy.modules.drools.service.DroolsService")
public class DroolsService {
    private static Logger logger = LoggerFactory.getLogger(DroolsService.class);
    /**
     * @methodName: executeStatelessKSRule
     * @param: [kSessionName, factObject]
     * @describe: 执行无状态kSession规则
     * @auther: dongdongchen
     * @date: 2018/12/26
     * @time: 13:07
     **/
    public void executeStatelessKSRule(String kSessionName,Object [] factObject){
        //使用kieservice创建新的KieContainers,
        //KieContainer对所有的业务资产(规则、流程、电子表格、PMML文档等)都有引用，当我们创建新的规则引擎实例时，这些资产将被加载
        KieContainer kContainer = KieServices.Factory.get().getKieClasspathContainer();
        //verify()方法将允许我们确保我们的业务资产是正确的
        Results results = kContainer.verify();
        results.getMessages().stream().forEach((message) -> {
            logger.info(">> Message ( "+message.getLevel()+" ): "+message.getText());
        });
        StatelessKieSession ksession = kContainer.newStatelessKieSession(kSessionName);
      /*  ksession.addEventListener( new DebugAgendaEventListener());
        ksession.addEventListener( new DebugRuleRuntimeEventListener());*/
        ksession.execute(Arrays.asList(factObject));
    }
}
