package org.game.cs.core.model.enums;

public enum RconCommand {

    CHANGE_MAP("changelevel "),

    MAP_LIST("maps *"),

    KICK("kickid "),
    
    BAN("banid "),
    
    BOT_ADD("bot_add_"),
    
    BOT_DIFFICULTY("bot_difficulty "),
    
    BOT_KICK_ALL("bot_kick all");

    private String value;

    private RconCommand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
