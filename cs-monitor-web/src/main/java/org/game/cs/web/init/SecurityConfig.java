package org.game.cs.web.init;

import org.game.cs.web.handler.CustomLogoutHandler;
import org.game.cs.web.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
@ImportResource(value = "WEB-INF/spring/spring-security.xml")
public class SecurityConfig {

    @Bean(name = "authenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint getLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean(name = "customAuthenticationSuccessHandler")
    public SimpleUrlAuthenticationSuccessHandler getSimpleUrlAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/");
    }

    @Bean(name = "customAuthenticationFailureHandler")
    public SimpleUrlAuthenticationFailureHandler getSimpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/denied");
    }

    @Bean(name = "sas")
    public ConcurrentSessionControlStrategy getConcurrentSessionControlStrategy() {
        ConcurrentSessionControlStrategy controlStrategy = new ConcurrentSessionControlStrategy(getSessionRegistryImpl());
        controlStrategy.setMaximumSessions(1);
        controlStrategy.setExceptionIfMaximumExceeded(true);
        return controlStrategy;
    }

    @Bean(name = "sessionRegistry")
    public SessionRegistryImpl getSessionRegistryImpl() {
        return new SessionRegistryImpl();
    }

    @Bean(name = "concurrencyFilter")
    public ConcurrentSessionFilter getConcurrentSessionFilter() {
        return new ConcurrentSessionFilter(getSessionRegistryImpl());
    }

    @Bean(name = "logoutFilter")
    public LogoutFilter getLogoutFilter() {
        return new LogoutFilter(getSimpleUrlLogoutSuccessHandler(), getTokenBasedRememberMeServices(), getCustomLogoutHandler());
    }

    @Bean(name = "customLogoutHandler")
    public CustomLogoutHandler getCustomLogoutHandler() {
        return new CustomLogoutHandler();
    }
    
    @Bean(name = "rememberMeService")
    public TokenBasedRememberMeServices getTokenBasedRememberMeServices(){
    	return new TokenBasedRememberMeServices("springRocks", getMyUserDetailsService());
    }
    
    @Bean(name = "rememberMeAuthenticationProvider")
    public RememberMeAuthenticationProvider getRememberMeAuthenticationProvider(){
    	return new RememberMeAuthenticationProvider("springRocks");
    }
    
    @Bean(name = "myUserDetailsService")
    public MyUserDetailsService getMyUserDetailsService(){
    	return new MyUserDetailsService();
    }

    @Bean(name = "simpleUrlLogoutSuccessHandler")
    public SimpleUrlLogoutSuccessHandler getSimpleUrlLogoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl("/login");
        return handler;
    }
}
