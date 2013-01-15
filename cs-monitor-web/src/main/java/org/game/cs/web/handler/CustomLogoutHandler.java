package org.game.cs.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.game.cs.core.service.SourceServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class CustomLogoutHandler extends SecurityContextLogoutHandler  {

    @Autowired
    private SourceServerService controlService;
    
	@Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        controlService.removeServer(getLoggedInUserName());
        super.logout(request, response, authentication);
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
