package org.game.cs.web.controller;

import java.io.IOException;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;

import org.game.cs.core.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class GameController {

    @Autowired
    private ControlService controlService;

    @RequestMapping("/control")
    public String showControlPage() {
        return "control";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connect(@RequestParam String ip, @RequestParam String port, @RequestParam String rcon, RedirectAttributes flash) {
        try {
            controlService.connect(ip, Integer.valueOf(port));
            flash.addFlashAttribute("response", "success");
        } catch (NumberFormatException | RequestTimeoutException | IOException | InterruptedException e) {
            flash.addFlashAttribute("response", "error");
            e.printStackTrace();
        }
        return "redirect:/admin/control";
    }

    @RequestMapping(value = "getbasicinfo", method = RequestMethod.GET)
    public String showBasicInfo() {
        System.out.println(controlService.getBasicInformation());
        return "redirect:/admin/control";
    }
}
