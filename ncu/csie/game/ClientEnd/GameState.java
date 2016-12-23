package ncu.csie.game.ClientEnd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.UDP.UDPServer;
import ncu.csie.game.entities.EntityManager;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.entities.statics.Tree;
import ncu.csie.game.gfx.Assets;


public class GameState extends State{
	private UIObject itemImg;
	private UIManager Playerinterface;
	private int maxblood = -1;
	
	public GameState(GameHandler handler){
		super(handler);		
		handler.getMouseManager().setUIManager(null);
		
		Playerinterface = new UIManager(handler);
		Playerinterface.addObject(new UIImage(30, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(150, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(handler.getWidth()-300, handler.getHeight()-100, 300, 100,Assets.mapall));
		maxblood = handler.getGame().getPlayerRender().getHp();
		//itemImg = new UIObject[2];
	}
	
	@Override
	public void tick() {
		handler.getKeyManager().tick();
		handler.getGame().getEntityRenders().tick();
		//getItem();
		if(handler.getGame().getPlayerRender().getHp() < 1)
		{
			handler.getGame().getPlayerRender().setcharIndex(-1);
		}
		
		Playerinterface.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getGame().getMap().render(g);
		handler.getGame().getEntityRenders().render(g);
		
		
		
		Playerinterface.render(g);
		
		g.setColor(Color.WHITE);
		g.fillOval((int)(handler.getWidth()-300 + handler.getGame().getPlayerRender().getX()/30)
				,(int)(handler.getHeight()-100 + handler.getGame().getPlayerRender().getY()/30)
				, 5, 5);
		
		g.setColor(Color.RED);
		
		if(handler.getGame().getPlayerRender().getHp()>=0)
			g.fillRect(10, 10, (int)(handler.getGame().getPlayerRender().getHp()*300/maxblood),15);
		else
			g.fillRect(10, 10, 0,15);
	
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 300, 15);
		
		if(itemImg==null){
			itemImg = new UIImage(45+0*120, handler.getHeight()*4/5+15, 90, 90,
					handler.getGame().getItemRender().getItemImage() );
			Playerinterface.addObject(itemImg);
		}
	}
	
	/*
	public void getItem(){
		int maxItem = 2;
		
		
		for(int i = 0 ; i < maxItem ; i++){
			if(handler.getWorld().getEntityManager().getPlayer().GetBag(i) != null)
			{
				if(itemImg[i]==null){
					itemImg[i] = new UIImage(45+i*120, handler.getHeight()*4/5+15, 90, 90,
							handler.getWorld().getEntityManager().getPlayer().GetBag(i).getImage());
					Playerinterface.addObject(itemImg[i]);
				}
			}
			else{
				for(int k = 0; k < Playerinterface.getObjects().size();k++){
					if(Playerinterface.getObjects().get(k) == itemImg[i]){
						Playerinterface.getObjects().remove(k);
						itemImg[i] = null;
					}
				}
			}
		}
		
	}
	*/
	
}
