package org.game.cs.core.model.enums;

public enum ServerInfo {

    SERVER_NAME("serverName"),

    CURRENT_MAP("currentMap"),

    PLAYERS("players");

    private String value;

    private ServerInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
