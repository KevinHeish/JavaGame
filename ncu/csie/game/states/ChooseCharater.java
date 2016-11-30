package ncu.csie.game.states;

import java.awt.Graphics;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.ui.ClickListener;
import ncu.csie.game.ui.UIImage;
import ncu.csie.game.ui.UIImageButton;
import ncu.csie.game.ui.UIManager;
import ncu.csie.game.worlds.World;

public class ChooseCharater extends State{

	private UIManager uiManager;
	
	public ChooseCharater(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImage(0,0,handler.getWidth(),handler.getHeight(),Assets.back_img));
		uiManager.addObject(new UIImage(250,00,600,150,Assets.game_start));
		uiManager.addObject(new UIImageButton(100,140, 120, 250, Assets.choose_Asuna, new ClickListener(){
			@Override
			public void onClick() {		
				start_game(0);
			}}));
		uiManager.addObject(new UIImageButton(240,160, 120, 250, Assets.choose_Hao, new ClickListener(){
			@Override
			public void onClick() {		
				start_game(1);
			}}));
		uiManager.addObject(new UIImageButton(380,180, 120, 250, Assets.choose_Hasaki, new ClickListener(){
			@Override
			public void onClick() {		
				start_game(2);
			}}));
		uiManager.addObject(new UIImageButton(520,180, 120, 250, Assets.choose_Jade, new ClickListener(){
			@Override
			public void onClick() {		
				start_game(3);
			}}));
		uiManager.addObject(new UIImageButton(660,160, 120, 250, Assets.choose_Sai, new ClickListener(){
			@Override
			public void onClick() {		
				start_game(4);
			}}));
		uiManager.addObject(new UIImageButton(800,140, 120, 250, Assets.choose_Yuki, new ClickListener(){
			@Override
			public void onClick() {	
				start_game(5);
			}}));
	}
	
	public UIManager getUImanager(){return uiManager;}
	
	@Override
	public void tick() {
		uiManager.tick();		
	}

	@Override
	public void render(Graphics g) {		
		uiManager.render(g);
	}
	public void start_game(int game_type)
	{
		handler.getMouseManager().setUIManager(null);
		for(Entity player: handler.getWorld().getEntityManager().getEntities()){
			if(player instanceof Player){
				((Player)player).setPlayerid(game_type);
			}
		}
		handler.getGame().gameState = new GameState(handler);
		State.setState(handler.getGame().gameState);
		
	}

}
