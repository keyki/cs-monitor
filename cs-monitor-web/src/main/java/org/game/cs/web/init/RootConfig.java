package org.game.cs.web.init;

import org.game.cs.dal.init.DaoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Import(value = {DaoConfig.class})
@ImportResource(value = "WEB-INF/spring/spring-security.xml")
public class RootConfig {

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:META-INF/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

}
