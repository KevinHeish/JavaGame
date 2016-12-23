package ncu.csie.game.UDP;

import java.util.ArrayList;

import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.worlds.Handler;

public class UDPClient {
	static UDPClientThread ClientThread;
	
	public static void initBroadcast(Handler handler)
	{
		if(ClientThread == null){
			System.out.println("udp broadcasting");
			ClientThread = new UDPClientThread(handler);
			//ClientThread.choosenBroadcast();
			ClientThread.start();
		}
	}
	
}
