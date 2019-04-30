package com.jy.modules.drools.rest;

import com.jy.modules.drools.domain.*;
import com.jy.modules.drools.entity.DroolsResultDTO;
import com.jy.modules.drools.service.DroolsService;
import com.jy.modules.drools.service.DroolsService2;
import com.jy.modules.drools.util.FunctionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.drools.examples.cashflow.Account;
import org.drools.examples.cashflow.AccountPeriod;
import org.drools.examples.cashflow.CashFlow;
import org.drools.examples.cashflow.CashFlowType;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.drools.examples.helloworld.Message;
import org.drools.examples.honestpolitician.Politician;
import org.drools.examples.petstore.Order;
import org.drools.examples.petstore.Product;
import org.drools.examples.petstore.Purchase;
import org.drools.examples.state.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"规则管理"}, description = "规则管理")
@RestController
@RequestMapping(value = "/api/drools")
public class DroolsRest {

    private static Logger logger = LoggerFactory.getLogger(DroolsService.class);

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private DroolsService2 droolsService2;


    @ApiOperation(value = "类型声明", notes = "typeDeclare-rules.drl")
    @RequestMapping(value = "/declareNewType", method = RequestMethod.GET)
    public String declareNewType() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "类型声明的继承", notes = "typeDeclareExtends-rules.drl")
    @RequestMapping(value = "/declareNewTypeExtends", method = RequestMethod.GET)
    public String declareNewTypeExtends() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "声明元数据", notes = "typeMetadata-rules.drl")
    @RequestMapping(value = "/declareTypeMetadata", method = RequestMethod.GET)
    public String declareTypeMetadata() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "查询函数", notes = "funcQuery-rules.drl")
    @RequestMapping(value = "/queryFuncRule", method = RequestMethod.GET)
    public String queryFuncRule() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "函数定义与使用", notes = "function-rules.drl")
    @RequestMapping(value = "/funcRule", method = RequestMethod.GET)
    public String funcRule() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "全局变量使用", notes = "globalProperty-rules.drl")
    @RequestMapping(value = "/testGlobalProperty", method = RequestMethod.GET)
    public String testGlobalProperty() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        Person p1 = new Person("P1", 33, new BigDecimal(0));
        Person p2 = new Person("P2", 32, new BigDecimal(0));
        Person p3 = new Person("P3", 25, new BigDecimal(0));
        inputList.add(p1);
        inputList.add(p2);
        inputList.add(p3);
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
        List<Person> maxThan30List = new ArrayList<Person>();
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
    @ApiOperation(value = "测试规则属性", notes = "ruleProperty-rules.drl")
    @RequestMapping(value = "/testRuleProperty", method = RequestMethod.GET)
    public String testRuleProperty() throws Exception {
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
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
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "测试条件语法", notes = "ruleLHSSyntax-rules.drl")
    @RequestMapping(value = "/testLHSSyntax", method = RequestMethod.GET)
    public String testLHSSyntax() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
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
        Map<String, Object> globalMap = new HashMap<String, Object>();
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
    @ApiOperation(value = "测试条件语法", notes = "ruleRHSSyntax-rules.drl")
    @RequestMapping(value = "/testRHSSyntax", method = RequestMethod.GET)
    public String testRHSSyntax() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> inputList = new ArrayList<Object>();
        // 测试insert事实
        RHSPerson p1 = new RHSPerson("person 1", 11);
        // 测试update事实
        RHSPerson p2 = new RHSPerson("person 2", 20);
        inputList.add(p1);
        inputList.add(p2);
        //定义全局变量，存放规则结果
        Map<String, Object> globalMap = new HashMap<String, Object>();
        //执行规则文件
        String result = droolsService2.executeRuleFile("ruleRHSSyntax-rules", inputList, globalMap);
        //若result值不为空值，说明规则执行存在错误。
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
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
    @ApiOperation(value = "测试决策表(电子表格)", notes = "测试决策表(电子表格)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int"),
            @ApiImplicitParam(name = "priorClaims", value = "优先要求权", required = true, dataType = "int"),
            @ApiImplicitParam(name = "locationRiskProfile", value = "风险决策等级(LOW-低级,MED-中级,HIGH-高级)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "insuranceTypes", value = "保险类型(COMPREHENSIVE-综合保险,FIRE_THEFT-火险及盗窃险,THIRD_PARTY-第三方责任险)", required = true, dataType = "String")
    })
    @RequestMapping(value = "/testDecisiontable", method = RequestMethod.GET)
    public void testDecisiontable(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "age") Integer age,
                                  @RequestParam(value = "priorClaims") Integer priorClaims,
                                  @RequestParam(value = "locationRiskProfile") String locationRiskProfile,
                                  @RequestParam(value = "insuranceTypes") String insuranceTypes
    ) {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        Driver driver = new Driver();
        driver.setName(name);
        driver.setAge(age);
        driver.setPriorClaims(priorClaims);
        driver.setLocationRiskProfile(locationRiskProfile);
        Policy policy = new Policy();
        policy.setApproved(false);
        policy.setType(insuranceTypes);
        policy.setDiscountPercent(0);
        factObjList.add(driver);
        factObjList.add(policy);
        boolean feedBack = droolsService.executeStatelessKSRule("DecisionTableKS", factObjList, null);
        if (feedBack) {
            System.out.println("BASE PRICE IS: " + policy.getBasePrice());
            System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());
        }
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
    @ApiOperation(value = "测试简单规则", notes = "HelloWorldKS")
    @RequestMapping(value = "/testHelloWorld", method = RequestMethod.GET)
    public void testHelloWorld(@RequestParam(value = "msg") String msg,
                               @RequestParam(value = "status") Integer status) throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        Message message = new Message();
        message.setMessage(msg);
        message.setStatus(status);
        factObjList.add(message);
        droolsService.executeStatelessKSRule("HelloWorldKS", factObjList, null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }


    /**
     * @methodName: testStateSalienceKB
     * @param: [msg, status]
     * @describe:测试含有salience优先级的规则方法 使用PropertyChangeSupport来监听变量的变化
     * @auther: dongdongchen
     * @date: 2018/12/26
     * @time: 20:08
     **/
    @ApiOperation(value = "测试含有salience优先级的规则方法", notes = "StateSalienceKS")
    @RequestMapping(value = "/testStateSalienceKS", method = RequestMethod.GET)
    public void testStateSalienceKB() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        final State a = new State("A");
        final State b = new State("B");
        final State c = new State("C");
        final State d = new State("D");
        factObjList.add(a);
        factObjList.add(b);
        factObjList.add(c);
        factObjList.add(d);
        droolsService.executeStatelessKSRule("StateSalienceKS", factObjList, null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }

    /**
     * @methodName: testStateAgendaGroupKS
     * @param: []
     * @describe: 测试使用规则组
     * agenda-group：为规则设定所属的规则组，当规则组获得焦点时，会匹配组内的规则，
     * 如果规则组没有焦点，那么组内规则将不会触发，该属性默认为MAIN。
     * 实际应用中agenda-group可以和auto-focus属性一起使用
     * @auther: dongdongchen
     * @date: 2018/12/27
     * @time: 9:46
     **/
    @ApiOperation(value = "测试使用规则组", notes = "StateAgendaGroupKS")
    @RequestMapping(value = "/testStateAgendaGroupKS", method = RequestMethod.GET)
    public void testStateAgendaGroupKS() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        final State a = new State("A");
        final State b = new State("B");
        final State c = new State("C");
        final State d = new State("D");
        factObjList.add(a);
        factObjList.add(b);
        factObjList.add(c);
        factObjList.add(d);
        droolsService.executeStatefulKSRule("StateAgendaGroupKS", factObjList, null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }

    /**
     * @methodName: testHonestPoliticianKS
     * @param: []
     * @describe: 在示例中规定，只要存在诚实的政治家，一个整体才有希望。
     * exists Hope()表示只在乎是否存在而不在乎存在几个，即使存在多个RHS只生效一次。对Hope()，存在几个RHS就会执行几次；
     * insertLogical当没有更多的fact支持当前激发规则的真值状态时，对象自动删除
     * @auther: dongdongchen
     * @date: 2018/12/27
     * @time: 14:18
     **/
    @ApiOperation(value = "HonestPoliticianKS", notes = "HonestPoliticianKS")
    @RequestMapping(value = "/testHonestPoliticianKS", method = RequestMethod.GET)
    public void testHonestPoliticianKS() {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        final Politician p1 = new Politician("President of Umpa Lumpa", true);
        final Politician p2 = new Politician("Prime Minster of Cheeseland", true);
        final Politician p3 = new Politician("Tsar of Pringapopaloo", true);
        final Politician p4 = new Politician("Omnipotence Om", true);
        factObjList.add(p1);
        factObjList.add(p2);
        factObjList.add(p3);
        factObjList.add(p4);
        droolsService.executeStatelessKSRule("HonestPoliticianKS", factObjList, null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }


    /**
     * @methodName: testCashFlowKS
     * @param: []
     * @describe: 测试现金流转
     * @auther: dongdongchen
     * @date: 2018/12/27
     * @time: 15:52
     **/
    @ApiOperation(value = "测试现金流转", notes = "CashFlowKS")
    @RequestMapping(value = "/testCashFlowKS", method = RequestMethod.GET)
    public void testCashFlowKS() throws Exception {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        AccountPeriod acp = new AccountPeriod(FunctionUtil.date("2013-01-01"), FunctionUtil.date("2013-03-31"));
        Account ac = new Account(1, 0);
        CashFlow cf1 = new CashFlow(FunctionUtil.date("2013-01-12"), 100, CashFlowType.CREDIT, 1);
        CashFlow cf2 = new CashFlow(FunctionUtil.date("2013-02-2"), 200, CashFlowType.DEBIT, 1);
        CashFlow cf3 = new CashFlow(FunctionUtil.date("2013-05-18"), 50, CashFlowType.CREDIT, 1);
        CashFlow cf4 = new CashFlow(FunctionUtil.date("2013-03-07"), 75, CashFlowType.CREDIT, 1);
        factObjList.add(ac);
        factObjList.add(cf1);
        factObjList.add(cf2);
        factObjList.add(cf3);
        factObjList.add(cf4);
        droolsService.executeStatelessKSRule("CashFlowKS", factObjList, null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }

    /**
     * @methodName: testPetStore
     * @param: []
     * @describe: 测试宠物店规则
     * @auther: dongdongchen
     * @date: 2018/12/27
     * @time: 15:52
     **/
    @ApiOperation(value = "测试宠物店规则", notes = "PetStoreKS")
    @RequestMapping(value = "/testPetStore", method = RequestMethod.GET)
    public void testPetStore() {
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        Order order = new Order();
        //定义商品列表
        Product goldFish = new Product("Gold Fish", 5);
        Product fishTank = new Product("Fish Tank", 25);
        Product fishFood = new Product("Fish Food", 2);
        //将商品存储到购物车中
        List<Product> items = new ArrayList<Product>();
        //Purchase  purchase06 = new Purchase(order,fishTank);
        items.add(new Product("Gold Fish", 5));
        items.add(new Product("Gold Fish", 5));
        items.add(new Product("Gold Fish", 5));
        items.add(new Product("Gold Fish", 5));
        items.add(new Product("Gold Fish", 5));
        items.add(new Product("Gold Fish", 5));
        for (Product p : items) {
            order.addItem(new Purchase(order, p));
        }
        //存放商品信息
        factObjList.add(goldFish);
        factObjList.add(fishTank);
        factObjList.add(fishFood);
        //存放订单信息
        factObjList.add(order);
        //定义全局变量
        Map<String, Object> globalVariable = new HashMap<String, Object>();
        Map<String, Object> globalMap = new HashMap<String, Object>();
        globalVariable.put("globalMap", globalMap);
        boolean feedBack = droolsService.executeStatelessKSRule("PetStoreKS", factObjList, globalVariable);
        if (feedBack && null != globalMap && globalMap.size() > 0) {
            double discountedTotal = (double) globalMap.get("discountedTotal");
            double grossTotal = (double) globalMap.get("grossTotal");
            System.out.println("订单总价格:" + grossTotal + ",订单折后价格:" + discountedTotal);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时:" + (endTime - startTime));
    }
}