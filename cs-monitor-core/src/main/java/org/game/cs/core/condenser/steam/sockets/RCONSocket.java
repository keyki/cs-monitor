/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2008-2013, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.game.cs.core.condenser.steam.exceptions.ConnectionResetException;
import org.game.cs.core.condenser.steam.exceptions.RCONBanException;
import org.game.cs.core.condenser.steam.exceptions.RCONNoAuthException;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.condenser.steam.packets.rcon.RCONPacket;
import org.game.cs.core.condenser.steam.packets.rcon.RCONPacketFactory;

/**
 * This class represents a socket used for RCON communication with game servers
 * based on the Source engine (e.g. Team Fortress 2, Counter-Strike: Source)
 * <p>
 * The Source engine uses a stateful TCP connection for RCON communication and
 * uses an additional socket of this type to handle RCON requests.
 *
 * @author Sebastian Staudt
 */
public class RCONSocket extends SteamSocket {

    protected final static Logger LOG = Logger.getLogger(RCONSocket.class.getName());

    /**
     * Creates a new TCP socket to communicate with the server on the given IP
     * address and port
     *
     * @param ipAddress Either the IP address or the DNS name of the server
     * @param portNumber The port the server is listening on
     * @throws SteamCondenserException if the socket cannot be opened
     */
    public RCONSocket(InetAddress ipAddress, int portNumber)
            throws SteamCondenserException {
        super(ipAddress, portNumber);
    }

    /**
     * Closes the underlying TCP socket if it is connected
     *
     * @see SteamSocket#close
     */
    @Override
    public void close() {
        if(((SocketChannel)this.channel).isConnected()) {
            super.close();
        }
    }

    /**
     * Sends the given RCON packet to the server
     *
     * @param dataPacket The RCON packet to send to the server
     * @throws SteamCondenserException if an error occurs while writing to the
     *         socket
     */
    public void send(RCONPacket dataPacket)
            throws SteamCondenserException {
        try {
            if (this.channel == null ||
               !((SocketChannel)this.channel).isConnected()) {
                this.channel = SocketChannel.open();
                ((SocketChannel) this.channel).socket().connect(this.remoteSocket, SteamSocket.timeout);
                this.channel.configureBlocking(false);
            }

            this.buffer = ByteBuffer.wrap(dataPacket.getBytes());
            ((SocketChannel)this.channel).write(this.buffer);
        } catch(IOException e) {
            throw new SteamCondenserException(e.getMessage(), e);
        }
    }

    /**
     * Reads a packet from the socket
     * <p>
     * The Source RCON protocol allows packets of an arbitrary sice transmitted
     * using multiple TCP packets. The data is received in chunks and
     * concatenated into a single response packet.
     *
     * @return The packet replied from the server
     * @throws RCONNoAuthException if an authenticated connection has been
     *         dropped by the server
     * @throws RCONBanException if the IP of the local machine has been banned
     *         on the game server
     * @throws SteamCondenserException if an error occurs while communicating
     *         with the server
     * @throws TimeoutException if the request times out
     */
    public RCONPacket getReply()
            throws SteamCondenserException, TimeoutException {
        try {
            if (this.receivePacket(4) == 0) {
                try {
                    this.channel.close();
                } catch (IOException e) {}
                throw new RCONNoAuthException();
            }
        } catch (ConnectionResetException e) {
            throw new RCONBanException();
        }

        int packetSize = Integer.reverseBytes(this.buffer.getInt());
        int remainingBytes = packetSize;

        byte[] packetData;
        List<Byte> packetDataList = new ArrayList<Byte>();
        int receivedBytes;
        do {
            receivedBytes = this.receivePacket(remainingBytes);

            packetData = this.buffer.array();
            for(int i = 0; i < this.buffer.limit(); i ++){
                packetDataList.add(packetData[i]);
            }
            remainingBytes -= receivedBytes;
        } while(remainingBytes > 0);

        packetData = new byte[packetDataList.size()];
        for(int i = 0; i < packetData.length; i ++) {
            packetData[i] = packetDataList.get(i);
        }

        RCONPacket packet = RCONPacketFactory.getPacketFromData(packetData);

        LOG.info("Received packet of type \"" + packet.getClass() + "\".");

        return packet;
    }
}
