/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2013, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.exceptions;

/**
 * Indicates that a connection has been reset by the peer
 *
 * @author Sebastian Staudt
 */
public class ConnectionResetException extends SteamCondenserException {

    public ConnectionResetException() {
        super("Connection reset by peer");
    }

}