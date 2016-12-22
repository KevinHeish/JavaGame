package ncu.csie.game.UDP;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.ClientEnd.JSONDecoder;

public class UDPServerThread extends Thread{
    private DatagramPacket packet;
    private DatagramSocket socket;
    private byte[] buffer;
    private JSONDecoder decoder;
    private GameHandler handler;
    
    
    public UDPServerThread(GameHandler handler)
    {
    	buffer = new byte[2048];
    	packet = new DatagramPacket(buffer, buffer.length);
    	try {
			socket = new DatagramSocket(6666);
			decoder = new JSONDecoder(handler);
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }

    
	@Override
	public void run() {
		assert buffer.length==2048;
		while(true){	
		try {
				socket.receive(packet);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            try{
                String client = new String(packet.getData(),0,packet.getLength());
                
                //System.out.println("Recieve");
                //System.out.println(client);
                
                decoder.parser(client);
                
            } catch(NumberFormatException e){
                System.out.println(e);
            }
		}
	}
	
    
}
