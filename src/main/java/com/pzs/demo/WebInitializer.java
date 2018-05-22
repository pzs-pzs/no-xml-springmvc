package com.pzs.demo;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        WebApplicationContext ctx = getContext();
        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",new DispatcherServlet(ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("com.pzs.demo.config");
        return context;
    }
}
