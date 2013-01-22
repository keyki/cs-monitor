package org.game.cs.web.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.game.cs.common.exception.NotConnectedException;
import org.game.cs.common.exception.ServerAlreadyRegisteredException;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private GlobalModelAttributes modelAttributes;

    @ExceptionHandler(value = {SteamCondenserException.class})
    public String handleSteamCondenserException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        addErrorMessageToRequest(request, exception.getMessage() + " Please connect again.");
        addServersToReqest(request);
        exception.printStackTrace();
        return "errors";
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public String handleExceptions2(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleExceptions3(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {NotConnectedException.class})
    public String handleExceptions4(Exception exception, HttpServletRequest request) {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        return "errors";
    }

    @ExceptionHandler(value = {TimeoutException.class})
    public void handleExceptions5(Exception exception, HttpServletResponse response, HttpServletRequest request) throws IOException {
        addErrorMessageToRequest(request, exception.getMessage());
        addServersToReqest(request);
        exception.printStackTrace();
        response.sendRedirect(request.getContextPath() + "/admin/control");
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
