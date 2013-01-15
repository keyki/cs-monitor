/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2011, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.packets.rcon;

/**
 * This packet class represents a special SERVERDATA_RESPONSE_VALUE packet
 * which is sent to the server
 *
 * It is used to determine the end of a RCON response from Source servers.
 * Packets of this type are sent after the actual RCON command and the empty
 * response packet from the server will indicate the end of the response.
 *
 * @author Sebastian Staudt
 * @see org.game.cs.core.condenser.steam.servers.SourceServer#rconExec
 */
public class RCONTerminator extends RCONPacket {

    /**
     * Creates a new RCON terminator packet instance for the given request ID
     *
     * @param requestId The request ID for the current RCON communication
     */
    public RCONTerminator(int requestId) {
        super(requestId, RCONPacket.SERVERDATA_RESPONSE_VALUE, "");
    }

}
