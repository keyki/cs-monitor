package org.game.cs.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.game.cs.common.exception.NotConnectedException;
import org.game.cs.core.model.enums.UserState;
import org.game.cs.core.service.ControlService;
import org.game.cs.web.annotation.CheckUserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckUserAspect {

    @Autowired
    private ControlService controlService;

    @Before(value = "@annotation(checkUserStatus)", argNames = "checkUserStatus")
    public void checkUserStatus(JoinPoint joinPoint, CheckUserState status) {
        if (!UserState.CONNECTED.equals(controlService.getUserState(getLoggedInUserName()))) {
            throw new NotConnectedException("You have to connect to a server first");
        }
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
