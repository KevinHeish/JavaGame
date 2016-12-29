package ncu.csie.game.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class PlayerThread implements Runnable{
	private Socket mySocket;
	private TCPServerThread host;
	
	public PlayerThread(Socket s , TCPServerThread host){
		this.mySocket = s;
		this.host = host;
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
	
	public Socket getSocket()
	{
		return mySocket;
	}

	@Override
	public void run() {
		while(true){
			String result = getInstruction();
			
			result += mySocket.getInetAddress().toString();
			System.out.println(result);
			host.getQueue().offer(result); 
		}
		
	}
	
	
	
}
