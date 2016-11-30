package ncu.csie.game.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.Handler;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.ui.ClickListener;
import ncu.csie.game.ui.UIImage;
import ncu.csie.game.ui.UIImageButton;
import ncu.csie.game.ui.UIManager;

public class Illustration extends State{
	
	private UIManager uiManager;
    int illust_id;
    private BufferedImage[] illusts;
    private UIImage current_illust;
	public Illustration(Handler handler) {
		super(handler);
		illusts=Assets.illustrations;
		illust_id=0;
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImage(0,0,handler.getWidth(),handler.getHeight(),Assets.back_img));
		current_illust=new UIImage(180,70,handler.getWidth()*2/3,handler.getHeight()*2/3,illusts[illust_id]);
		uiManager.addObject(current_illust);
		uiManager.addObject(new UIImageButton(handler.getWidth()-140,handler.getHeight()/2-50, 100, 80, Assets.btn_right, new ClickListener(){
			@Override
			public void onClick() {	
				uiManager.removeObject(current_illust);
				illust_id-=1;
				if(illust_id<0)
					illust_id+=6;
				current_illust=new UIImage(180,70,handler.getWidth()*2/3,handler.getHeight()*2/3,illusts[illust_id]);
				uiManager.addObject(current_illust);
			}}));
		uiManager.addObject(new UIImageButton(60,handler.getHeight()/2-50, 100, 80, Assets.btn_left, new ClickListener(){
			@Override
			public void onClick() {	
				uiManager.removeObject(current_illust);
				illust_id+=1;
				if(illust_id>5)
					illust_id-=6;
				current_illust=new UIImage(180,70,handler.getWidth()*2/3,handler.getHeight()*2/3,illusts[illust_id]);
				uiManager.addObject(current_illust);
			}}));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2, handler.getHeight()/2+180, 100, 80, Assets.btn_back, new ClickListener(){
			@Override
			public void onClick() {		
				handler.getMouseManager().setUIManager(handler.getGame().menuState.getUImanager());
				State.setState(handler.getGame().menuState);	
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
