package ru.vlados.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { // Конфигурация Spring
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //все запросы http посылаем на DispatcherServlet
        return new String[]{"/"};
    }
}
