package com.jy.modules.drools.rest;

import com.jy.modules.drools.entity.Applicant;
import com.jy.modules.drools.service.DroolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/anti-fraud/rule")
public class AntifraudRuleRest {

    private static Logger logger = LoggerFactory.getLogger(AntifraudRuleRest.class);

    @Autowired
    private DroolsService droolsService;

    /**
     * @methodName: testRuleA001
     * @param: []
     * @describe: 测试规则A001(申请人中文姓名比对到黑名单姓名且生日一致)
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/A001")
    public void  testRuleA001(){
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        //定义当前申请人信息
        Applicant current = new Applicant();
        Date birthdate = new Date();
        current.setCustName("张三1");
        current.setBirthDate(birthdate);
        factObjList.add(current);
        //定义历史申请人信息
        List<Applicant>  list = new ArrayList<Applicant>();
        for (int i = 0; i < 1000000; i++) {
            Applicant history = new Applicant();
            history.setCustName("张三"+i);
            history.setBirthDate(birthdate);
            list.add(history);
        }
        factObjList.add(list);
        droolsService.executeStatelessKSRule("RuleA001KS",factObjList,null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时(单位:秒):" + (endTime - startTime)/1000);
    }

    /**
     * @methodName: testRuleA002
     * @param: []
     * @describe: 测试规则A002(申请人身份证号码比对到黑名单身份证号)
     * @auther: dongdongchen
     * @date: 2018/12/14
     * @time: 14:39
     **/
    @RequestMapping("/A002")
    public void  testRuleA002(){
        long startTime = System.currentTimeMillis();
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<Object>();
        //定义当前申请人信息
        Applicant current = new Applicant();
        Date birthdate = new Date();
        current.setCustName("张三1");
        current.setBirthDate(birthdate);
        current.setCardId("110101199003070199");
        factObjList.add(current);
        //定义历史申请人信息
        List<Applicant>  list = new ArrayList<Applicant>();
        for (int i = 0; i < 100000; i++) {
            Applicant history = new Applicant();
            history.setCustName("张三"+i);
            history.setBirthDate(birthdate);
            history.setCardId("11010119900307"+i);
            list.add(history);
        }
        factObjList.add(list);
        droolsService.executeStatelessKSRule("RuleA002KS",factObjList,null);
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时(单位:秒):" + (endTime - startTime)/1000);
    }

}