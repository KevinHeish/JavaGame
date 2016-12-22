package ncu.csie.game.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.*;

import ncu.csie.game.CDC.CentralizedDataCenter;
import ncu.csie.game.TCP.TCPServer;
import ncu.csie.game.entities.creatures.Player;
//import ncu.csie.game.item.Item;
import ncu.csie.game.worlds.Handler;


public class UDPClientThread extends Thread{
	private byte[] bdata ;
	private InetAddress[] ipTable;
	private DatagramPacket packet;
    private DatagramSocket socket;
    private ArrayList<Player> players;
	private Handler handler;
    private CentralizedDataCenter CDCTest;
	
	public UDPClientThread(Handler handler)
	{
		bdata = new byte[2048];
		ipTable = TCPServer.getClientIPTable();
		this.handler = handler;
		players = handler.getWorld().getPlayers();
        try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
        CDCTest = new CentralizedDataCenter(handler);
	}
	

	@Override
	public void run() {
		while(true){
			String test = null;
			
			
			test = CDCTest.Encoder();
			assert bdata.length==2048;
			
			bdata = test.getBytes();
			packet = new DatagramPacket(bdata, bdata.length,ipTable[0], 6666);
			try {
				socket.send(packet);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		/*----------------------------------------------
		 * test for broadcast to all socket.
		 * 
		 * 
		VirtualCharacter testChar = new VirtualCharacter(50,50);
		JSONObject charJSONFormat = new JSONObject();
		String test =  charJSONFormat.toString();
		bdata = test.getBytes();
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){
            	for(int i = 0 ; i < 4 ; i++){
    				try {
    					packet = new DatagramPacket(bdata, bdata.length,ipTable[i], 6666);
    					socket.send(packet);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
            }
        },0,5000);
		*/
		
	}
}

