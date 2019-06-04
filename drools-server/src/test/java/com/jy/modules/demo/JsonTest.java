package com.jy.modules.demo;

import com.alibaba.fastjson.JSONObject;
import com.jy.modules.boot.util.MyJSONUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by apple on 2019/6/4.
 */
public class JsonTest {

    private static Logger logger = LoggerFactory.getLogger(JsonTest.class);

    @Test
    public void testJson() {
        String jsonStr1 = "{\"application\":{\"applicationNumber\":\"13023456789\",\"apprProductName\":\"商悦贷A\"},\"applicant\":{},\"contactInfos\":[{\"id\":1234,\"applicationNumber\":\"13023456789\",\"conName\":\"张三\",\"conPhone\":\"8888345\"},{\"id\":123,\"applicationNumber\":\"13023456789\",\"conName\":\"张三\",\"conPhone\":\"8888345\"}]}";
        String jsonStr2 = "{\"application\":{\"applicationNumber\":\"13023456789\",\"apprProductName\":\"商悦贷A\"},\"applicant\":{},\"contactInfos\":[{\"id\":1234,\"applicationNumber\":\"13023456789\",\"conName\":\"张三\",\"conPhone\":\"8888345\"},{\"id\":1235,\"applicationNumber\":\"13023456789\",\"conName\":\"张三\",\"conPhone\":\"8888345\"},{\"id\":1236,\"applicationNumber\":\"13023456789\",\"conName\":\"田七\",\"conPhone\":\"8888345\"}]}";
        JSONObject jsonObjectA = JSONObject.parseObject(jsonStr1);
        JSONObject jsonObjectB = JSONObject.parseObject(jsonStr2);
        Instant inst1 = Instant.now();
        boolean result = MyJSONUtil.compareTwoJSONObject(jsonObjectA, jsonObjectB);
        Instant inst2 = Instant.now();
        logger.info("比较结果:{},耗时:{}毫秒", result, Duration.between(inst1, inst2).toMillis());
    }
}
