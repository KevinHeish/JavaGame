package ncu.csie.game.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class TCPClient {
	private static Socket clientSocket;
	
	public static int connectServer(String serverIp, int port)
	{
		BufferedReader messageReader;
		int connectionId = -1;
		String recieve;
		
		try{
			clientSocket = new Socket(serverIp, port);
			messageReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			recieve = messageReader.readLine();
			connectionId = Integer.parseInt(recieve);
			
			return connectionId;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return connectionId;
        }
	}
	
	public static void send(String instruction)
	{
		PrintStream messageSend;   	
    	
		try {
			messageSend = new PrintStream(clientSocket.getOutputStream());	
			messageSend.println(instruction);
			messageSend.flush();
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean waitMessage()
	{
		BufferedReader messageReader;
    	
		try{
			messageReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String recieve = messageReader.readLine();
			
			
			if(recieve.equals("chooseState")){
				return true;
			}		
			else if(recieve.equals("start"))
			{
				return true;
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
}
