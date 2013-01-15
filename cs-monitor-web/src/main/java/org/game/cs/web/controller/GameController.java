package org.game.cs.web.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.service.ControlService;
import org.game.cs.dal.service.ServerService;
import org.game.cs.web.annotation.CheckUserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping("/admin")
public class GameController {

    @Autowired
    private ControlService controlService;
    @Autowired
    private ServerService serverService;
    @Autowired
    private ResourceLoader resourceLoader;

    @CheckUserState
    @RequestMapping("/control")
    public String showControlPage(Model model) throws SteamCondenserException, TimeoutException {
        model.addAttribute("info", controlService.getBasicInformation(getLoggedInUserName()));
        return "control";
    }

    @CheckUserState
    @RequestMapping("/players")
    public String showPlayersPage(Model model) throws SteamCondenserException, TimeoutException {
        model.addAttribute("players", controlService.getPlayers(getLoggedInUserName()));
        return "players";
    }

    @CheckUserState
    @RequestMapping(value = "/changelevel", method = RequestMethod.GET)
    public String showChangeLevelPage(Model model) throws TimeoutException, SteamCondenserException {
        Collection<String> availableMaps = controlService.getAvailableMaps(getLoggedInUserName());
        model.addAttribute("mapString", constructMapString((List<String>) htmlEscape(availableMaps)));
        model.addAttribute("maps", availableMapsWithPreviewPicture(availableMaps));
        return "changelevel";
    }

    private Collection<String> availableMapsWithPreviewPicture(Collection<String> availableMaps) {
        Iterator<String> iterator = availableMaps.iterator();
        while (iterator.hasNext()) {
            String map = iterator.next();
            if (!isPreviewAvailable(map)) {
                iterator.remove();
            }
        }
        return availableMaps;
    }

    private boolean isPreviewAvailable(String map) {
        return resourceLoader.getResource("resources/img/maps/" + map + ".jpg").exists();
    }

    private String constructMapString(List<String> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < collection.size(); i++) {
            if (i == collection.size() - 1) {
                stringBuilder.append("&quot;" + collection.get(i) + "&quot;");
            } else {
                stringBuilder.append("&quot;" + collection.get(i) + "&quot;,");
            }
        }
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connect(@RequestParam String ip, @RequestParam int port, @RequestParam(required = false) String rcon,
            @RequestParam(required = false) boolean register) throws SteamCondenserException, TimeoutException {
        if (rcon != null) {
            controlService.connect(getLoggedInUserName(), ip, Integer.valueOf(port), rcon);
        } else {
            controlService.connect(getLoggedInUserName(), ip, Integer.valueOf(port));
        }
        if (register) {
            serverService.registerServer(getLoggedInUserName(), ip, port, rcon);
        }
        return "redirect:/admin/control";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String deleteServer(@RequestParam String ip) {
        serverService.remove(getLoggedInUserName(), ip);
        return "redirect:/";
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Collection<String> htmlEscape(Collection<String> collection) {
        for (String s : collection) {
            s = htmlEscape(s);
        }
        return collection;
    }

    private String htmlEscape(String s) {
        return HtmlUtils.htmlEscape(s);
    }

}
