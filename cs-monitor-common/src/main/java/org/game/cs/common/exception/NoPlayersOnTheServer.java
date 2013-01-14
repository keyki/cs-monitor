package org.game.cs.common.exception;

public class NoPlayersOnTheServer extends RuntimeException {

    private static final long serialVersionUID = 6881051119101239470L;

    public NoPlayersOnTheServer(String message) {
        super(message);
    }

}
