package com.jy.modules.drools;

import com.sun.istack.internal.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*@EnableWebMvc
@Configuration*/
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger= Logger.getLogger(WebMvcConfig.class);

    /** 指定默认文件的地址，jsp页面引入js和css的时候就不用管项目路径了 */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
    }

    /**
     * 多模块的jsp访问，默认是src/main/webapp，但是多模块的目录只设置yml文件或propeerties文件不行
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        logger.info("****************/WEB-INF/jsp/*****************************************");
        return viewResolver;
    }

}
