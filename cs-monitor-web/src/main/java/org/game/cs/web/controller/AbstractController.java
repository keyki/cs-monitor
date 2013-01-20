package org.game.cs.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

public abstract class AbstractController {

	@ExceptionHandler(value = { NoSuchRequestHandlingMethodException.class })
	public String noSuchRequestHandlingMethodException(Exception exception,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		exception.printStackTrace();
		return "404page";
	}

}
