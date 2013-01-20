package org.game.cs.web.controller;

import java.util.Date;
import java.util.List;

import org.game.cs.core.model.enums.UserState;
import org.game.cs.core.service.SourceServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduleController{

    @Autowired
    private SourceServerService sourceServerService;
    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * expires the connection to the server if the last action performed more
     * than 10 minutes ago checks it every minute
     */
    @Scheduled(fixedRate = 60000)
    public void clear() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object o : allPrincipals) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(o, false);
            for (SessionInformation si : allSessions) {
                String userName = ((org.springframework.security.core.userdetails.User) o).getUsername();
                if (checkIfShouldExpire(si, userName)) {
                    sourceServerService.expireConnection(userName);
                }
            }
        }
    }

    private boolean checkIfShouldExpire(SessionInformation si, String userName) {
        return getTheDifferenceInMs(si) >= 600000 && UserState.CONNECTED.equals(sourceServerService.getUserState(userName));
    }

    private long getTheDifferenceInMs(SessionInformation si) {
        return new Date().getTime() - si.getLastRequest().getTime();
    }

}
