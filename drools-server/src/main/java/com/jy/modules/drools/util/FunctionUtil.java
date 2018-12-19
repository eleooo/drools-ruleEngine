package com.jy.modules.drools.util;

import java.util.Calendar;
import java.util.Date;

public class FunctionUtil {

	/**
	 * @methodName: plusDay
	 * @param: [d, amount]
	 * @describe: 将传入的日期的天加入相应的数值，最后返回运算后的Date对象
	 * Calendar.DAY_OF_MONTH 获得这个月的第几天
	 * @auther: dongdongchen
	 * @date: 2018/12/15
	 * @time: 13:28
	 **/
	public static Date plusDay(Date d, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}
}
