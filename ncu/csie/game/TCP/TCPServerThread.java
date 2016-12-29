package ncu.csie.game.TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
		private Queue<String> test = new LinkedList<String>();
		
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
			PrintStream messageSend;
			
			System.out.println("Wait for connection......");
			while(connectionId < maxConnection )
			{
				try {
					clientSockets = serverSocket.accept();
					players = new PlayerThread(clientSockets,this);
					ipTable[connectionId] = players.getIpAddress();
					
					ThreadList.add(players);
					try {
						messageSend = new PrintStream(ThreadList.get(connectionId).getSocket().getOutputStream());
						messageSend.println(connectionId+1);
						messageSend.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
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
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendToClient("chooseState");
			chooseCharacterState();
			sendToClient("start");
			
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
			
			
			//while(( connectionList[0] & connectionList[1])==false){
			while( connectionList[0]==false){
				for(int i = 0 ; i < maxConnection ; i++){
					while(index==null){
						index = ThreadList.get(i).getInstruction();
					}
					assert index!=null;
					
					int number = Integer.parseInt(index); 
					assert number>=0 && number<=5;
					
					if(characterList[number]==true){
						handler.getWorld().getPlayers().get(i).setPlayerid(number);
						connectionList[i] = true;
						characterList[number] = false;
					}
				}
			}
			
		}
		
		public void sendToClient(String message){
			PrintStream messageSend;

			for(int i = 0; i < maxConnection ;i++)
			{
				try {
					messageSend = new PrintStream(ThreadList.get(i).getSocket().getOutputStream());
					messageSend.println(message);
					messageSend.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		public Queue<String> getQueue()
		{
			return test;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				handler.getWorld().getPlayers().get(0).getInput(
						ThreadList.get(0).getInstruction());
				handler.getWorld().getPlayers().get(0).tick();
			}
		}
}
