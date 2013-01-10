package org.game.cs.web.controller;

import javax.servlet.http.HttpServletRequest;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {RequestTimeoutException.class, InterruptedException.class})
    public String handleExceptions1(Exception exception, HttpServletRequest request) {
        request.setAttribute("error", "Could not connect to the server");
        return "default";
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public String handleExceptions2(Exception exception, HttpServletRequest request) {
        request.setAttribute("error", "Wrong format");
        return "default";
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleExceptions3(Exception exception, HttpServletRequest request) {
        request.setAttribute("error", "Wrong address");
        return "default";
    }

}
