package com.jy.modules.drools.rest;

import com.jy.modules.drools.domain.*;
import com.jy.modules.drools.entity.DroolsResultDTO;
import com.jy.modules.drools.service.DroolsService;
import com.sample.driver_license.Application;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DroolsRest {
    @Autowired
    private DroolsService droolsService;

    @RequestMapping("/exeRuleFile")
    public String exeRuleFile(@RequestParam(value = "msg") String msg,
                              @RequestParam(value = "status") Integer status) throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        Message message = new Message();
        message.setMsg(msg);
        message.setStatus(status);
        //存放每一个事实对象
        inputList.add(message);
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService.executeRuleFile("ksession-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        if (StringUtils.isEmpty(result)) {
            //返回规则处理结果
            result = droolsResultDTO.toString();
            //result = new String(result.getBytes("GBK"), "utf-8");

        }
        return result;
    }

    @RequestMapping("/declareNewType")
    public String declareNewType() throws Exception {
        //定义一个事实对象集合
        List <Object> inputList = new ArrayList <Object>();
        //定义全局变量，存放规则结果
        Map <String, Object> globalMap = new HashMap <String, Object>();
        DroolsResultDTO droolsResultDTO = new DroolsResultDTO();
        globalMap.put("droolsResultDTO", droolsResultDTO);
        //执行规则文件
        String result = droolsService.executeRuleFile("typeDeclare-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("typeDeclareExtends-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("typeMetadata-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("funcQuery-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("function-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("globalProperty-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("ruleProperty-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("ruleLHSSyntax-rules", inputList, globalMap);
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
        String result = droolsService.executeRuleFile("ruleRHSSyntax-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        long endTime = System.currentTimeMillis();
        System.out.printf("执行耗时:" + (endTime - startTime));
        return result;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(Map <String, Object> data) {
        data.put("name", "angus");
        return new ModelAndView("welcome");
    }

    /**
     * @methodName: testRHSSyntax
     * @param: []
     * @describe: 测试规则-满18周岁才可以开车
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/testLicenseRule")
    public String  testLicenseRule(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "age") Integer age){
       String result =" ";
       // 读取jar包信息，并用Kcontainer封装，并创建KieSession
       KieServices ks = KieServices.Factory.get();
       ReleaseId releaseId = ks.newReleaseId( "com.sample", "driver-license", "1.0.0" );
       KieContainer kContainer = ks.newKieContainer(releaseId);
       KieSession kSession = kContainer.newKieSession();
       //初始化Applicant对象，进行规则匹配，判断是否可以申请驾照
       Application driver = new Application();
       driver.setName(name);
       driver.setAge(age);
       kSession.insert(driver);
       kSession.fireAllRules();
       if (null!=driver.getValid()&&driver.getValid()) {
            System.out.println("Can Drive");
            result ="Can Drive";
       } else {
            System.out.println("Can't Drive");
            result ="Can't Drive";
       }
        return result;
    }
}