package com.jy.modules.boot.contract;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <br> 自定义Contract翻译器
 * <br> MyContract 继承了 SpringMvcContract，在重写 processAnnotationOnMethod 方法时，
 * 调用了父类的 processAnnotationOnMethod。简单点说 ， 我们实现的这个 Contract，除了支
 * 持 Spring 的注解外，还支持我们自定义的＠MyUrl注解.
 */
public class MyContract extends SpringMvcContract {

    /**
     * 用于处理方法级的注解
     */
    protected void processAnnotationOnMethod(MethodMetadata data,
                                             Annotation annotation, Method method) {
        // 调用父类的方法时,让其支持@RequestMapping注解
        super.processAnnotationOnMethod(data, annotation, method);
        // 是MyUrl注解才进行处理
        if (MyUrl.class.isInstance(annotation)) {
            //获取注解的实例
            MyUrl myUrlAnn = method.getAnnotation(MyUrl.class);
            //获取配置的HTTP方法
            String httpMethod = myUrlAnn.method();
            //获取服务的url
            String url = myUrlAnn.url();
            //将值设置到模板中
            data.template().method(httpMethod);
            data.template().append(url);
        }
    }
}
