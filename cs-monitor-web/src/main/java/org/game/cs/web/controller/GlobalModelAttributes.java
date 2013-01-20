package org.game.cs.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.game.cs.common.domain.Server;
import org.game.cs.dal.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class GlobalModelAttributes {

    @Autowired
    private ServerService serverService;

    @ModelAttribute("servers")
    public Collection<Server> getServers() {
        String userName = getLoggedInUserName();
        Collection<Server> list = new ArrayList<>();
        if (!"anonymousUser".equalsIgnoreCase(userName)) {
            list = serverService.findAllByUser(userName);
        }
        return list;
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
