package ncu.csie.game.ClientEnd;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.Timer;

import ncu.csie.game.ClientAnimation.EntityRenderManager;
import ncu.csie.game.ClientAnimation.ItemRender;
import ncu.csie.game.ClientAnimation.MonsterRender;
import ncu.csie.game.ClientAnimation.PlayerRender;
import ncu.csie.game.ClientAnimation.VirtualCharRender;
import ncu.csie.game.TCP.TCPClient;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.gfx.GameCamera;


public class Game implements Runnable{
	
	private Display display;
	public String title;
	private int width, height;	
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	public State gameState , menuState, endState, illustState, choosecharater;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private GameCamera gameCamera;
	private GameHandler handler;
	private Map map;
	private PlayerRender playerRender;
	private EntityRenderManager entitiesManager;
	private ArrayList<MonsterRender> monsterList;
	private int localPlayerId;
	private ArrayList<ItemRender> itemList;
	private ActionListener actionListener;
	private VirtualCharRender[] otherPlayers;
	//---------------------------------------------
	
	
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		Assets.init();
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		handler = new GameHandler(this);
		map = new Map(handler, "res/worlds/world2.txt");
		gameCamera = new GameCamera(handler, 0, 0);	
		illustState = new Illustration(handler);
		choosecharater = new ChooseCharater(handler);
		menuState = new MenuState(handler);
		display = new Display(title, width, height);
		monsterList = new ArrayList<MonsterRender>();
		//------------------setup end----------------------
		
		//------------------player monster-------------
		entitiesManager = new EntityRenderManager(handler);
		
		
		playerRender = new PlayerRender(handler , 0 ,0 );
		entitiesManager.getEntities().add(playerRender);
		
		itemList = new ArrayList<ItemRender>();
		
		otherPlayers = new VirtualCharRender[3];
		
		for(int i = 0 ; i < 3 ; i++)
		{
			otherPlayers[i] = new VirtualCharRender(handler , 0 , 0);
			entitiesManager.getEntities().add(otherPlayers[i]);
		}
		
		
		for(int i = 0 ; i < 25 ; i++)
		{
			MonsterRender generator = new MonsterRender(handler, 0 , 0, i);
			monsterList.add(generator);
			entitiesManager.getEntities().add(generator);
		}
		
		for(int i = 0 ; i < 15 ; i++)
		{
			ItemRender generator = new ItemRender(handler, 0,0 , 50 ,50 , 0);
			itemList.add(generator);
			entitiesManager.getEntities().add(generator);
		}

	}
	
	private void init(){
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);	
		
		State.setState(menuState);
		
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				
				if(e.getSource().equals(display.getDialogPassLinkData().getOkButton())){
					
					String ip = display.getDialogPassLinkData().getInputIP();
					int port = Integer.parseInt(display.getDialogPassLinkData().getInputPort());				
					
					localPlayerId = TCPClient.connectServer(ip, port);
					assert localPlayerId >=0;
					
					display.getDialogPassLinkData().dispose();
					display.getDialogWaitForLink().setVisible(true);
					
				}
				
				while(flag == false){
					flag = TCPClient.waitMessage();
					display.getDialogWaitForLink().getLabelMsg().setText("waiting");
				}
				
				display.getDialogWaitForLink().getOkButton().setEnabled(true);
				
				display.getDialogWaitForLink().setVisible(false);
				handler.getMouseManager().setUIManager(handler.getGame().choosecharater.getUImanager());
				State.setState(handler.getGame().choosecharater);
				
			}
		};
		
		display.getDialogPassLinkData().getOkButton().addActionListener(actionListener);
		display.getDialogPassLinkData().getCancelButton().addActionListener(actionListener);
		display.getDialogWaitForLink().getOkButton().addActionListener(actionListener);
		
	}
	
	private void tick(){
		keyManager.tick();
		if(State.getState() != null)
			gameState.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		
		//Draw Here
		if(State.getState() != null)
			gameState.getState().render(g);
		
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run(){
		
		init();	
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		double now;
		double lastTime = System.nanoTime();
		long timer = 0;
		long ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: "+ticks);
				timer = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void buildEnd()
	{
		endState = new EndState(handler);
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public Map getMap()
	{
		return map;
	}
	
	public PlayerRender getPlayerRender()
	{
		return playerRender;
	}
	
	public ArrayList<MonsterRender> getMonsters()
	{
		return monsterList;
	}
	
	public EntityRenderManager getEntityRenders()
	{
		return entitiesManager;
	}
	
	public ArrayList<ItemRender> getItems()
	{
		return itemList;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public int getLocalPlayerId(){
		return localPlayerId;
	}
	
	public VirtualCharRender[] getOtherPlayers()
	{
		return otherPlayers;
	}
}
