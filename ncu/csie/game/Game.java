package ncu.csie.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import ncu.csie.game.display.Display;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.gfx.GameCamera;
import ncu.csie.game.input.KeyManager;
import ncu.csie.game.input.MouseManager;
import ncu.csie.game.states.ChooseCharater;
import ncu.csie.game.states.EndState;
import ncu.csie.game.states.GameState;
import ncu.csie.game.states.Illustration;
import ncu.csie.game.states.MenuState;
import ncu.csie.game.states.State;
import ncu.csie.game.worlds.World;

public class Game implements Runnable{
	
	private Display display;
	public String title;
	public int width, height;	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//State	
	public State gameState;
	public State menuState;
	public State endState;
	public State illustState;
	public State choosecharater;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
		
	//Handler
	private Handler handler;
	
	private World world;
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;	
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);	
		world = new World(handler, "res/worlds/world2.txt");
		handler.setWorld(world);
		illustState = new Illustration(handler);
		choosecharater = new ChooseCharater(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
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
			gameState.getState().render(g);;
		
		//End Drawing
		bs.show();
		g.dispose();
	}
		
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
}
