package ncu.csie.game.TCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.UDP.UDPClient;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.worlds.Handler;

public class TCPServerThread extends Thread {
		private ServerSocket serverSocket;
		private Socket clientSockets;
		private PlayerThread players;
		private final int maxConnection = 1;
		private ArrayList<PlayerThread> ThreadList;
		private InetAddress[] ipTable;
		private Handler handler;
		private Timer timer;
		
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
			
			chooseCharacterState();
		}
		
		public InetAddress[] getIPTable()
		{
			return ipTable;
		}
		
		public void chooseCharacterState()
		{
			boolean[] characterList = {true, true, true, true, true ,true};
			boolean[] connectionList = {false, false, false , false};
			String index = null;
			
			
			while(connectionList[0]==false){
				for(int i = 0 ; i < maxConnection ; i++){
					while(index==null){
						index = ThreadList.get(i).getInstruction();
					}
					assert index!=null;
					
					int number = Integer.parseInt(index); 
					assert number>=0 && number<=5;
					
					System.out.println(number);
					if(characterList[number]==true){
						handler.getWorld().getPlayers().get(i).setPlayerid(number);
						connectionList[i] = true;
						characterList[number] = false;
					}
				}
			}
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
					handler.getWorld().getPlayers().get(i).tick();
				}
			}
		}
}
