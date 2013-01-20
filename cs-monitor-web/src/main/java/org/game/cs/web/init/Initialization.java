package org.game.cs.web.init;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initialization extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        
        Dynamic filter = servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy);
        filter.addMappingForUrlPatterns(null, true, "/*");
    }

}
