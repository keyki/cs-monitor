package org.game.cs.web.controller;

import java.util.concurrent.TimeoutException;

import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.service.ControlService;
import org.game.cs.web.annotation.CheckUserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class RconController {

    @Autowired
    private ControlService controlService;

    @CheckUserState
    @RequestMapping("/players/kick/{id}")
    public String kickPlayer(@PathVariable int id) throws TimeoutException, SteamCondenserException {
        controlService.kickPlayer(getLoggedInUserName(), id);
        return "redirect:/admin/players";
    }
    
    @CheckUserState
    @RequestMapping("/players/ban/{id}")
    public String banPlayer(@PathVariable int id) throws TimeoutException, SteamCondenserException {
        controlService.banPlayer(getLoggedInUserName(), id);
        return "redirect:/admin/players";
    }
    
    @CheckUserState
    @RequestMapping(value = "/changelevel", method = RequestMethod.POST)
    public String changeLevel(@RequestParam String map) throws TimeoutException, SteamCondenserException {
        controlService.changeMap(getLoggedInUserName(), map);
        return "redirect:/admin/changelevel";
    }
    
    @CheckUserState
    @RequestMapping(value = "/executerconcommand", method = RequestMethod.POST)
    public String setRcon(@RequestParam String rcon_command) throws TimeoutException, SteamCondenserException {
        controlService.executeCommand(getLoggedInUserName(), rcon_command);
        return "redirect:/admin/control";
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
