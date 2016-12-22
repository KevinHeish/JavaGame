package ncu.csie.game.worlds;

import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.TCP.TCPServer;
import ncu.csie.game.UDP.UDPClient;
import ncu.csie.game.entities.creatures.Monster;

public class Server {
	private static Handler handler;
	private static World world;
	
	public static void main(String[] args) {
		handler = new Handler();
		world = new World(handler, "res/worlds/world2.txt");
		handler.setWorld(world);
		TCPServer.initTCPServer(handler);
		UDPClient.initBroadcast(handler);
		
	}
}
