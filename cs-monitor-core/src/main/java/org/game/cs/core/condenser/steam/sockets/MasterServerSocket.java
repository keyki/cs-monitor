/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2008-2011, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.sockets;

import java.net.InetAddress;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.game.cs.core.condenser.steam.exceptions.PacketFormatException;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.condenser.steam.packets.SteamPacket;

/**
 * This class represents a socket used to communicate with master servers
 *
 * @author     Sebastian Staudt
 */
public class MasterServerSocket extends QuerySocket {

    /**
     * Creates a new socket to communicate with the server on the given IP
     * address and port
     *
     * @param ipAddress Either the IP address or the DNS name of the server
     * @param portNumber The port the server is listening on
     * @throws SteamCondenserException if the socket cannot be opened
     */
    public MasterServerSocket(InetAddress ipAddress, int portNumber)
            throws SteamCondenserException {
        super(ipAddress, portNumber);
    }

    /**
     * Reads a single packet from the socket
     *
     * @return The packet replied from the server
     * @throws SteamCondenserException if an error occurs while communicating
     *         with the server
     * @throws PacketFormatException if the packet has the wrong format
     * @throws TimeoutException if the request times out
     */
    public SteamPacket getReply()
            throws SteamCondenserException, TimeoutException {
        this.receivePacket(1500);

        if(this.buffer.getInt() != -1) {
            throw new PacketFormatException("Master query response has wrong packet header.");
        }

        SteamPacket packet = this.getPacketFromData();

        Logger.getLogger("com.github.koraktor.steamcondenser").info("Received reply of type \"" + packet.getClass().getSimpleName() + "\"");

        return packet;
    }

}
