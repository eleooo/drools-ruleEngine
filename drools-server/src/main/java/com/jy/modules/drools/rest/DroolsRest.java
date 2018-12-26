package com.jy.modules.drools.rest;

import com.jy.modules.drools.domain.*;
import com.jy.modules.drools.entity.DroolsResultDTO;
import com.jy.modules.drools.service.DroolsService;
import com.jy.modules.drools.service.DroolsService2;
import org.apache.commons.lang3.StringUtils;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.drools.examples.helloworld.Message;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class DroolsRest {
    @Autowired
    private DroolsService droolsService;

    @Autowired
    private DroolsService2 droolsService2;



    @RequestMapping("/declareNewType")
    public String declareNewType() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService2.executeRuleFile("typeDeclare-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
        }
        return result;
    }

    /**
     * @methodName: declareNewTypeExtends
     * @param: []
     * @describe: 测试类型声明的继承
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/declareNewTypeExtends")
    public String declareNewTypeExtends() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService2.executeRuleFile("typeDeclareExtends-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
        }
        return result;
    }

    /**
     * @methodName: declareNewTypeExtends
     * @param: []
     * @describe: 测试声明元数据
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/declareTypeMetadata")
    public String declareTypeMetadata() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService2.executeRuleFile("typeMetadata-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
        }
        return result;
    }

    /**
     * @methodName: declareNewTypeExtends
     * @param: []
     * @describe: 测试查询函数
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/queryFuncRule")
    public String queryFuncRule() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService2.executeRuleFile("funcQuery-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("执行耗时:" + (endTime - startTime));
        return result;
    }


    /**
     * @methodName: funcRule
     * @param: []
     * @describe: 测试函数定义与使用
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/funcRule")
    public String funcRule() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService2.executeRuleFile("function-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
        }
        return result;
    }

    /**
     * @methodName: testGlobalProperty
     * @param: []
     * @describe: 测试全局变量
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testGlobalProperty")
    public String testGlobalProperty() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        Person p1 = new Person("P1", 33, new BigDecimal(0));
        Person p2 = new Person("P2", 32, new BigDecimal(0));
        Person p3 = new Person("P3", 25, new BigDecimal(0));
        inputList.add(p1);
        inputList.add(p2);
        inputList.add(p3);
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        List <Person> maxThan30List = new ArrayList <Person>();
        globalMap.put("maxThan30", maxThan30List);
        //执行规则文件
        String result = droolsService2.executeRuleFile("globalProperty-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        return result;
    }


    /**
     * @methodName: testRuleProperty
     * @param: []
     * @describe: 测试规则属性
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testRuleProperty")
    public String testRuleProperty() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        // 测试no-loop
        PropertyPerson p1 = new PropertyPerson("person 1", 17);
        // 测试lock-on-active和agenda-group
        PropertyPerson p2 = new PropertyPerson("person 2", 30);
        // 测试activation-group
        PropertyPerson p3 = new PropertyPerson("person 3", 40);
        // 测试Duration
        PropertyPerson p4 = new PropertyPerson("person 4", 50);
        // 测试salience
        PropertyPerson p5 = new PropertyPerson("person 5", 60);
        inputList.add(p1);
        inputList.add(p2);
        inputList.add(p3);
        inputList.add(p4);
        inputList.add(p5);
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        //执行规则文件
        String result = droolsService2.executeRuleFile("ruleProperty-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        return result;
    }


    /**
     * @methodName: testLHSSyntax
     * @param: []
     * @describe: 测试条件语法
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testLHSSyntax")
    public String testLHSSyntax() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        // 条件中调用事实方法
        SyntaxPerson1 p2 = new SyntaxPerson1("person 2", 30, "b");
        // Java表达式
        SyntaxPerson1 p3 = new SyntaxPerson1("person 3", 40, "c");
        // 使用逗号隔开多个与条件
        SyntaxPerson1 p4 = new SyntaxPerson1("person 4", 50, "d");
        // 测试多实例条件
        SyntaxPerson1 p5 = new SyntaxPerson1("person 5", 11, "e");
        SyntaxPerson2 p6 = new SyntaxPerson2("person 6", 11);
        SyntaxPerson2 p7 = new SyntaxPerson2("person 7", 11);
        // 测试属性命名
        SyntaxPerson1 p8 = new SyntaxPerson1("person 8", 20, "f");
        // 字符串包含
        SyntaxPerson1 p9 = new SyntaxPerson1("person 9", 60, "g");
        // 判定集合元素值
        SyntaxPerson1 p10 = new SyntaxPerson1("person 10", 25, "Paris");
        inputList.add(p2);
        inputList.add(p3);
        inputList.add(p4);
        inputList.add(p5);
        inputList.add(p6);
        inputList.add(p7);
        inputList.add(p8);
        inputList.add(p9);
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        //执行规则文件
        String result = droolsService2.executeRuleFile("ruleLHSSyntax-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        long endTime = System.currentTimeMillis();
        System.out.printf("执行耗时:" + (endTime - startTime));
        return result;
    }

    /**
     * @methodName: testRHSSyntax
     * @param: []
     * @describe: 测试行为语法
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testRHSSyntax")
    public String testRHSSyntax() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        // 测试insert事实
        RHSPerson p1 = new RHSPerson("person 1", 11);
        // 测试update事实
        RHSPerson p2 = new RHSPerson("person 2", 20);
        inputList.add(p1);
        inputList.add(p2);
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        //执行规则文件
        String result = droolsService2.executeRuleFile("ruleRHSSyntax-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        long endTime = System.currentTimeMillis();
        System.out.printf("执行耗时:" + (endTime - startTime));
        return result;
    }


    /**
     * @methodName: testDecisiontable
     * @param: []
     * @describe: 测试决策表(电子表格)
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testDecisiontable")
    public void testDecisiontable(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "age") Integer age,
                                  @RequestParam(value = "priorClaims") Integer priorClaims,
                                  @RequestParam(value = "locationRiskProfile") String locationRiskProfile,
                                  @RequestParam(value = "type") String type
                                  )
            throws Exception {
        long startTime = System.currentTimeMillis();
        Driver driver = new Driver();
        driver.setName(name);
        driver.setAge(age);
        driver.setPriorClaims(priorClaims);
        driver.setLocationRiskProfile(locationRiskProfile);
        Policy policy = new Policy();
        policy.setApproved(false);
        policy.setType(type);
        policy.setDiscountPercent(0);
        droolsService.executeStatelessKSRule("DecisionTableKS",new Object[]{driver,policy});
        System.out.println( "BASE PRICE IS: " + policy.getBasePrice() );
        System.out.println( "DISCOUNT IS: " + policy.getDiscountPercent() );
        long endTime = System.currentTimeMillis();
        System.out.printf("执行耗时:" + (endTime - startTime));
    }


    /**
     * @methodName: testHelloWorld
     * @param: [msg, status]
     * @describe: 测试简单规则
     * @auther: dongdongchen
     * @date: 2018/12/26
     * @time: 14:10
     **/
    @RequestMapping("/testHelloWorld")
    public void testHelloWorld(@RequestParam(value = "msg") String msg,
                              @RequestParam(value = "status") Integer status) throws Exception {
        long startTime = System.currentTimeMillis();
        Message message = new Message();
        message.setMessage(msg);
        message.setStatus(status);
        droolsService.executeStatelessKSRule("HelloWorldKS",new Object[]{message});
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }



    @RequestMapping(value = "/index")
    public ModelAndView index(Map <String, Object> data) {
        data.put("name", "angus");
        return new ModelAndView("welcome");
    }

}