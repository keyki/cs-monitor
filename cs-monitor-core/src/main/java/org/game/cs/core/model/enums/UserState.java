package org.game.cs.core.model.enums;

public enum UserState {

    IDLE("idle"),

    CONNECTED("connected");

    private String value;

    private UserState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
