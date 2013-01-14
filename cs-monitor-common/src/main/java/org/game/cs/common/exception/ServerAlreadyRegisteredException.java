package org.game.cs.common.exception;

public class ServerAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = -4228190933375720892L;

	public ServerAlreadyRegisteredException(String message) {
		super(message);
	}

}
