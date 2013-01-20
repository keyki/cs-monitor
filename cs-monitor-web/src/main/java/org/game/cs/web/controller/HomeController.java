package org.game.cs.web.controller;

import org.game.cs.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showHomePage() {
        return "default";
    }
    
    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/denied")
    public String showDeniedPage() {
        return "denied";
    }

    @RequestMapping("/createmasterusers")
    public String createMasterusers() {
        userService.createMasterUsers();
        return "redirect:/";
    }
    
    @RequestMapping(value="/errors/404")
    public String handle404() {
    	System.out.println("pina");
        return "404page";
    }
    

}
