/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2008-2011, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.packets.rcon;

import org.game.cs.core.condenser.steam.packets.SteamPacket;

/**
 * This packet class represents a RCON response packet sent by a GoldSrc server
 * <p>
 * It is used to transport the output of a command from the server to the
 * client which requested the command execution.
 *
 * @author Sebastian Staudt
 * @see org.game.cs.core.condenser.steam.servers.GoldSrcServer#rconExec
 */
public class RCONGoldSrcResponsePacket extends SteamPacket {

    /**
     * Creates a RCON command response for the given command output
     *
     * @param commandResponse The output of the command executed on the server
     */
    public RCONGoldSrcResponsePacket(byte[] commandResponse) {
        super(SteamPacket.RCON_GOLDSRC_RESPONSE_HEADER, commandResponse);
    }

    /**
     * Returns the output of the command execution
     *
     * @return The output of the command
     */
    public String getResponse() {
        return this.contentData.getString();
    }
}
