package ncu.csie.game.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class PlayerThread extends Thread{
	private Socket mySocket;

	
	public PlayerThread(Socket s){
		this.mySocket = s;
	}

	public String getInstruction()
	{
		BufferedReader messageReader;
		
		try {
			messageReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			return messageReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public InetAddress getIpAddress() {
		return mySocket.getInetAddress();
	}
}
