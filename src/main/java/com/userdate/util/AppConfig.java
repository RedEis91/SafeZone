package com.userdate.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    //view resolver takes a view name, looks in the folder, and adds .jsp to it
    //there is a way to do this in XML, but this is the JAVA way to do things
    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setPrefix("WEB-INF/views/");
        r.setSuffix(".jsp");
        return r;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //resource handler, when you go to any website, request goes to dispatcher servlet. takes request, and looks for mapping
    //there's a method defined to handle "/", etc...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //if someone requests anything in the /resources directory, handle it
        //anything placed here will be served up from webapp
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(3600);
    }                                                                                                //^^^^instead of hitting the harddrive
    // keep these for x amount of time in broswer

}