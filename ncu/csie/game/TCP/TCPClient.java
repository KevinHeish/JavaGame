package ncu.csie.game.TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class TCPClient {
	private static Socket clientSocket;

	public static boolean connectServer(String serverIp, int port)
	{
		try{
			clientSocket = new Socket(serverIp, port);
			return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
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
	
}
