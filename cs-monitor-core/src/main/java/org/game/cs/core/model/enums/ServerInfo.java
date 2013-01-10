package org.game.cs.core.model.enums;

public enum ServerInfo {

    SERVER_NAME("serverName"),

    CURRENT_MAP("currentMap"),

    PLAYERS("players"),

    BOT_COUNT("bot_count"),

    DEDICATED("dedicated"),

    MAX_PLAYERS("max_players"),

    OS("os"),

    PASSWORD_REQUIRED("password_required"),

    VAC_SECURE("vac_secure"),

    NUMBER_OF_PLAYERS("number_of_players");

    private String value;

    private ServerInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
