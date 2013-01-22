package org.game.cs.web.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.game.cs.core.service.SourceServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryAspect {

    @Autowired
    SourceServerService sourceServerService;

    @Around("exceptionHandlers() && handleSteamCondenserException()")
    public void retry(ProceedingJoinPoint joinPoint) throws Throwable {
        Throwable cause = (Throwable) joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[2];
        if (cause.getMessage().equalsIgnoreCase("An existing connection was forcibly closed by the remote host")) {
            sourceServerService.reconnect(getLoggedInUserName());
            response.sendRedirect(request.getContextPath() + "/admin/control");
        } else {
            joinPoint.proceed();
        }
    }

    @Pointcut("within(org.game.cs.web.controller.ControllerExceptionHandler)")
    private void exceptionHandlers() {
    }

    @Pointcut("execution(String org.game.cs.web.controller.ControllerExceptionHandler.handleSteamCondenserException(..))")
    private void handleSteamCondenserException() {
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
