package ncu.csie.game.TCP;

import java.net.InetAddress;

import ncu.csie.game.worlds.Handler;



public class TCPServer {
	private static TCPServerThread server;
	/*-----------------------------------------------------------------------------
	 * initTCPServer(), No return value , Server has been initialized after been called..
	 * default port : 8080
	 -----------------------------------------------------------------------------*/
	public static void initTCPServer(Handler handler)
	{
		if(server == null){
			server = new TCPServerThread(8080 , handler);
			server.waitConnection();
			server.start();
		}
	}
	
	//getClientIPTable() , Return InetAddress array include all clients' IPAddress. 
	public static InetAddress[] getClientIPTable()
	{
		return server.getIPTable();
	}
	
}
