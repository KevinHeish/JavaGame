package ncu.csie.game.TCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ncu.csie.game.UDP.UDPClient;
import ncu.csie.game.worlds.Handler;

public class TCPServerThread extends Thread {
		private ServerSocket serverSocket;
		private Socket clientSockets;
		private PlayerThread players;
		private final int maxConnection = 1;
		private ArrayList<PlayerThread> ThreadList;
		private InetAddress[] ipTable;
		private Handler handler;
		
		public TCPServerThread(int port, Handler handler)
		{
			try {
				serverSocket = new ServerSocket(port);
				this.handler = handler;
			} catch (IOException e) {
				e.printStackTrace();
			}
			clientSockets = new Socket();
			ThreadList = new ArrayList<PlayerThread>();
			ipTable = new InetAddress[maxConnection];
		}
		
		public void waitConnection()
		{
			int connectionId = 0;
			
			System.out.println("Wait for connection......");
			while(connectionId < maxConnection )
			{
				try {
					clientSockets = serverSocket.accept();
					players = new PlayerThread(clientSockets);
					ipTable[connectionId] = players.getIpAddress();
					
					ThreadList.add(players);
					connectionId++;
				} catch (IOException e){
					e.printStackTrace();
				}
			}
			
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public InetAddress[] getIPTable()
		{
			return ipTable;
		}
		
		
		@Override
		public void run()
		{
			while(true)
			{
				for(int i = 0 ; i< maxConnection ;i++)
				{
					handler.getWorld().getPlayers().get(i).getInput(
							ThreadList.get(i).getInstruction());
					handler.getWorld().getEntityManager().tick();
				}
			}
		}
}
