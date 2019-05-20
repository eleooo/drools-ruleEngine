package com.jy.modules.drools.enumdef;

import org.apache.commons.lang3.StringUtils;

/**
 * @className: RuleErrorEnum
 * @package: com.jy.module.drools.enumdef
 * @describe: 定义 规则错误信息 枚举类
 * @auther: dongdongchen
 * @date: 2018/12/12
 * @time: 16:36
 **/




public enum RuleErrorEnum {
    ERR_101("[ERR 101]","没有可行的选择"),
    ERR_102("[ERR 102]","输入不匹配"),
    ERR_103("[ERR 103]","谓词失败"),
    ERR_104("[ERR 104]","不容许以分号结束"),
    ERR_105("[ERR 105]","子规则至少要被匹配选择一次"),
    ERR_107("[ERR 107]","语法解析器无法将其识别成一个软关键字");
    private String key;
    private String value;

    private RuleErrorEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**
     * @methodName: getValueByKey
     * @param: [key]
     * @describe: 根据key获取对应的value
     * @auther: dongdongchen
     * @date: 2018/12/12
     * @time: 16:39
     **/
    public static String getValueByKey(String key) {
        String value = null;

        if (StringUtils.isEmpty(key)) {
            return value;
        }

        for (RuleErrorEnum s : values()) {
            if (s.getKey().equals(key)) {
                value = s.getValue();
                break;
            }
        }

        return value;
    }
}
