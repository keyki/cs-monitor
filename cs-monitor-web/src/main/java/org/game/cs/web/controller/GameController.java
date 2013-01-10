package org.game.cs.web.controller;

import java.io.IOException;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;

import org.game.cs.core.service.ControlService;
import org.game.cs.web.annotation.CheckUserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @CheckUserState
    @RequestMapping("/control")
    public String showControlPage(Model model) throws RequestTimeoutException, IOException, InterruptedException {
        model.addAttribute("info", controlService.getBasicInformation(getLoggedInUserName()));
        return "control";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connect(@RequestParam String ip, @RequestParam String port) throws NumberFormatException, RequestTimeoutException, IOException,
        InterruptedException {
        controlService.connect(getLoggedInUserName(), ip, Integer.valueOf(port));
        return "redirect:/admin/control";
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
