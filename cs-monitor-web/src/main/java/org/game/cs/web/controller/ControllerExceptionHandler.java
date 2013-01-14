package org.game.cs.web.controller;

import java.net.SocketTimeoutException;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;

import org.game.cs.common.exception.NotConnectedException;
import org.game.cs.common.exception.ServerAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private GlobalModelAttributes modelAttributes;

    @ExceptionHandler(value = {RequestTimeoutException.class, InterruptedException.class, SocketTimeoutException.class})
    public String handleExceptions1(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, "Could not connect to the server");
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public String handleExceptions2(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, "Wrong format");
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleExceptions3(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, "Wrong address");
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {NotConnectedException.class})
    public String handleExceptions4(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {FailedLoginException.class})
    public String handleExceptions5(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, "Wrong rcon password");
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {ServerAlreadyRegisteredException.class})
    public String handleExceptions6(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        return "errors";
    }

    private void addErrorMessageToRequest(HttpServletRequest request, String message) {
        request.setAttribute("error", message);
    }

    private void addServersToReqest(HttpServletRequest request) {
        request.setAttribute("servers", modelAttributes.getServers());
    }

}
