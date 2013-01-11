package org.game.cs.web.controller;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;

import org.game.cs.core.model.enums.UserState;
import org.game.cs.core.service.ControlService;
import org.game.cs.web.annotation.CheckUserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class GameController {

    @Autowired
    private ControlService controlService;
    @Autowired
    private SessionRegistry sessionRegistry;

    @CheckUserState
    @RequestMapping("/control")
    public String showControlPage(Model model) throws RequestTimeoutException, IOException, InterruptedException {
        model.addAttribute("info", controlService.getBasicInformation(getLoggedInUserName()));
        return "control";
    }

    @CheckUserState
    @RequestMapping("/changelevel")
    public String showChangeLevelPage(Model model) throws FailedLoginException, SocketTimeoutException {
        model.addAttribute("maps", controlService.getAvailableMaps(getLoggedInUserName()));
        return "changelevel";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connect(@RequestParam String ip, @RequestParam String port, @RequestParam(required = false) String rcon)
        throws NumberFormatException, RequestTimeoutException, IOException, InterruptedException {
        if (rcon != null) {
            controlService.connect(getLoggedInUserName(), ip, Integer.valueOf(port), rcon);
        } else {
            controlService.connect(getLoggedInUserName(), ip, Integer.valueOf(port));
        }
        return "redirect:/admin/control";
    }

    @CheckUserState
    @RequestMapping(value = "/executerconcommand", method = RequestMethod.POST)
    public String setRcon(@RequestParam String rcon, @RequestParam String rcon_command) throws FailedLoginException, SocketTimeoutException {
        controlService.executeCommand(getLoggedInUserName(), rcon, rcon_command);
        return "redirect:/admin/control";
    }

    /**
     * expires the connection to the server if the last action performed more than 10 minutes ago
     * checks it every minute
    */
    @Scheduled(fixedRate = 60000)
    public void clear() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object o : allPrincipals) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(o, false);
            for (SessionInformation si : allSessions) {
                String userName = ((org.springframework.security.core.userdetails.User) o).getUsername();
                if (checkIfShouldExpire(si, userName)) {
                    controlService.expireConnection(userName);
                }
            }
        }
    }

    private boolean checkIfShouldExpire(SessionInformation si, String userName) {
        return getTheDifferenceInMs(si) >= 600000 && UserState.CONNECTED.equals(controlService.getUserState(userName));
    }

    private long getTheDifferenceInMs(SessionInformation si) {
        return new Date().getTime() - si.getLastRequest().getTime();
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
