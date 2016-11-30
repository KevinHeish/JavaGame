package ncu.csie.game.states;

import java.awt.Graphics;
import ncu.csie.game.Handler;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.ui.ClickListener;
import ncu.csie.game.ui.UIImage;
import ncu.csie.game.ui.UIImageButton;
import ncu.csie.game.ui.UIManager;

public class EndState extends State{
	
	private UIManager uiManager;

	public EndState(Handler handler) {
		super(handler);
			
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImage(0,0,handler.getWidth(),handler.getHeight(),Assets.end_back_img));
		uiManager.addObject(new UIImageButton(handler.getWidth()/2-120, handler.getHeight()/2+120, 210, 50, Assets.btn_exit, new ClickListener(){
			@Override
			public void onClick() {		
				System.exit(0);
			}}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();	
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
	}

}
