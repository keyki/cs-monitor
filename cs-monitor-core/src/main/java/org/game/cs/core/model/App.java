package org.game.cs.core.model;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import org.game.cs.core.condenser.steam.SteamPlayer;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.condenser.steam.servers.SourceServer;

public class App {
    public static void main(String[] args) throws TimeoutException, InterruptedException {
        String IP = "fsanyee.no-ip.org";
        Integer PORT = 27015;
        InetSocketAddress addr = new InetSocketAddress(IP, PORT);
        try {
            SourceServer server = new SourceServer(addr.getAddress(), PORT);
            System.out.println(server.getServerInfo());
            server.rconAuth("asddsa");
//            System.out.println(server.rconExec("status"));
            System.out.println(server.rconExec("changelevel de_dust2"));
//            System.out.println(server.rconExec("status"));
        } catch (SteamCondenserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        HashMap<String,SteamPlayer> players = server.getPlayers();
//        System.out.println(server.getPlayers());
//        for(SteamPlayer player : players.values()){
//            System.out.println(player.getSteamId());
//        }
//        server.disconnect();
    }

}
