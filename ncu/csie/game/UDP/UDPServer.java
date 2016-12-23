package ncu.csie.game.UDP;

import ncu.csie.game.ClientEnd.GameHandler;

public class UDPServer {
	private static UDPServerThread UDPrecieveThread;
	
	public static void initUDPServer(GameHandler handler)
	{
		if(UDPrecieveThread==null){ 
			UDPrecieveThread = new UDPServerThread(handler);
		}
	}
	
	public static void startUDPServer()
	{
		UDPrecieveThread.start();
	}
	/*
	public static void setCharacter()
	{
		UDPrecieveThread.setCharacter();
	}*/
	
}
