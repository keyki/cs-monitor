/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2008-2011, Sebastian Staudt
 */

package org.game.cs.core.condenser.steam.packets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.CRC32;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.game.cs.core.condenser.steam.Helper;
import org.game.cs.core.condenser.steam.exceptions.PacketFormatException;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;

/**
 * This module provides functionality to handle raw packet data, including data
 * split into several UDP / TCP packets and BZIP2 compressed data. It's the
 * main utility to transform data bytes into packet objects.
 *
 * @author Sebastian Staudt
 * @see SteamPacket
 */
public abstract class SteamPacketFactory {

    /**
     * Creates a new packet object based on the header byte of the given raw
     * data
     *
     * @param rawData The raw data of the packet
     * @throws PacketFormatException if the packet header is not recognized
     * @return The packet object generated from the packet data
     */
    public static SteamPacket getPacketFromData(byte[] rawData) throws PacketFormatException {
        byte header = rawData[0];
        byte[] data = new byte[rawData.length - 1];
        System.arraycopy(rawData, 1, data, 0, rawData.length - 1);

        switch (header) {
        case SteamPacket.A2S_INFO_HEADER:
            return new A2S_INFO_Packet();

        case SteamPacket.S2A_INFO2_HEADER:
            return new S2A_INFO2_Packet(data);

        case SteamPacket.A2S_PLAYER_HEADER:
            return new A2S_PLAYER_Packet(Helper.integerFromByteArray(data));

        case SteamPacket.S2A_PLAYER_HEADER:
            return new S2A_PLAYER_Packet(data);

        case SteamPacket.A2S_RULES_HEADER:
            return new A2S_RULES_Packet(Helper.integerFromByteArray(data));

        case SteamPacket.S2A_RULES_HEADER:
            return new S2A_RULES_Packet(data);

        case SteamPacket.A2S_SERVERQUERY_GETCHALLENGE_HEADER:
            return new A2S_SERVERQUERY_GETCHALLENGE_Packet();

        case SteamPacket.S2C_CHALLENGE_HEADER:
            return new S2C_CHALLENGE_Packet(data);

        case SteamPacket.M2A_SERVER_BATCH_HEADER:
            return new M2A_SERVER_BATCH_Paket(data);

        case SteamPacket.M2C_ISVALIDMD5_HEADER:
            return new M2C_ISVALIDMD5_Packet(data);

        case SteamPacket.M2S_REQUESTRESTART_HEADER:
            return new M2S_REQUESTRESTART_Packet(data);

        case SteamPacket.S2A_LOGSTRING_HEADER:
            return new S2A_LOGSTRING_Packet(data);

        default:
            throw new PacketFormatException("Unknown packet with header 0x" + Integer.toHexString(header) + " received.");
        }
    }

    /**
     * Reassembles the data of a split packet into a single packet object
     *
     * @param splitPackets An array of packet data
     * @throws SteamCondenserException if decompressing the packet data fails
     * @throws PacketFormatException if the calculated CRC32 checksum does not
     *         match the expected value
     * @return SteamPacket The reassembled packet
     * @see SteamPacketFactory#getPacketFromData
     */
    public static SteamPacket reassemblePacket(ArrayList<byte[]> splitPackets) throws SteamCondenserException {
        return SteamPacketFactory.reassemblePacket(splitPackets, false, 0, 0);
    }

    /**
     * Reassembles the data of a split and/or compressed packet into a single
     * packet object
     *
     * @param splitPackets An array of packet data
     * @param isCompressed whether the data of this packet is compressed
     * @param uncompressedSize The size of the decompressed packet data
     * @param packetChecksum The CRC32 checksum of the decompressed
     *        packet data
     * @throws SteamCondenserException if decompressing the packet data fails
     * @throws PacketFormatException if the calculated CRC32 checksum does not
     *         match the expected value
     * @return SteamPacket The reassembled packet
     * @see SteamPacketFactory#getPacketFromData
     */
    public static SteamPacket reassemblePacket(ArrayList<byte[]> splitPackets, boolean isCompressed, int uncompressedSize, int packetChecksum)
        throws SteamCondenserException {
        byte[] packetData, tmpData;
        packetData = new byte[0];

        for (byte[] splitPacket : splitPackets) {
            tmpData = packetData;
            packetData = new byte[tmpData.length + splitPacket.length];
            System.arraycopy(tmpData, 0, packetData, 0, tmpData.length);
            System.arraycopy(splitPacket, 0, packetData, tmpData.length, splitPacket.length);
        }

        if (isCompressed) {
            try {
                ByteArrayInputStream stream = new ByteArrayInputStream(packetData);
                stream.read();
                stream.read();
                BZip2CompressorInputStream bzip2 = new BZip2CompressorInputStream(stream);
                byte[] uncompressedPacketData = new byte[uncompressedSize];
                bzip2.read(uncompressedPacketData, 0, uncompressedSize);

                CRC32 crc32 = new CRC32();
                crc32.update(uncompressedPacketData);
                int crc32checksum = (int) crc32.getValue();

                if (crc32checksum != packetChecksum) {
                    throw new PacketFormatException("CRC32 checksum mismatch of uncompressed packet data.");
                }
                packetData = uncompressedPacketData;
            } catch (IOException e) {
                throw new SteamCondenserException(e.getMessage(), e);
            }
        }

        packetData = new String(packetData).substring(4).getBytes();

        return SteamPacketFactory.getPacketFromData(packetData);
    }
}
