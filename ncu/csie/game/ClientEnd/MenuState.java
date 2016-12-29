package ncu.csie.game.ClientEnd;

import java.awt.Graphics;

import ncu.csie.game.gfx.Assets;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(GameHandler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImage(0,0,handler.getWidth(),handler.getHeight(),Assets.back_img));
		uiManager.addObject(new UIImage(handler.getWidth()/2-250,handler.getHeight()/2-300,300,120,Assets.title_img[0]));
		uiManager.addObject(new UIImage(handler.getWidth()/2,handler.getHeight()/2-200,220,120,Assets.title_img[1]));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2-230, handler.getHeight()/2-60, 450, 50, Assets.btn_start, new ClickListener(){
			@Override
			public void onClick() {
				//handler.getMouseManager().setUIManager(null);
				//State.setState(handler.getGame().gameState);
				handler.getGame().getDisplay().getDialogPassLinkData().setVisible(true);
			}}));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2-230, handler.getHeight()/2, 450, 50, Assets.btn_instruct, new ClickListener(){
			@Override
			public void onClick() {
				new Manual(500,300);
			}}));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2-230, handler.getHeight()/2+60, 450, 50, Assets.btn_actor, new ClickListener(){
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(handler.getGame().illustState.getUImanager());
				State.setState(handler.getGame().illustState);	
			}}));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2-230, handler.getHeight()/2+120, 210, 50, Assets.btn_option, new ClickListener(){
			@Override
			public void onClick() {				
			}}));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2+10, handler.getHeight()/2+120, 210, 50, Assets.btn_exit, new ClickListener(){
			@Override
			public void onClick() {		
				System.exit(0);
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

}
