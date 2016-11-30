package ncu.csie.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.EntityManager;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.entities.statics.Tree;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.ui.ClickListener;
import ncu.csie.game.ui.UIImage;
import ncu.csie.game.ui.UIImageButton;
import ncu.csie.game.ui.UIManager;
import ncu.csie.game.ui.UIObject;
import ncu.csie.game.worlds.World;


public class GameState extends State{
	private UIObject[] itemImg;
	//private World world;
	private UIManager Playerinterface;
	private int maxblood;
	
	public GameState(Handler handler){
		super(handler);		
		handler.getMouseManager().setUIManager(null);
		//world = new World(handler, "res/worlds/world2.txt");
		//handler.setWorld(world);
		maxblood = handler.getWorld().getEntityManager().getPlayer().GetBlood();
		Playerinterface = new UIManager(handler);
		Playerinterface.addObject(new UIImage(30, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(150, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(handler.getWidth()-300, handler.getHeight()-100, 300, 100,Assets.mapall));
		itemImg = new UIObject[2];
	}
	
	@Override
	public void tick() {
		handler.getWorld().tick();
		getItem();
		if(handler.getWorld().getEntityManager().getPlayer().GetBlood()<1)
		{
			handler.getGame().buildEnd();
			State.setState(handler.getGame().endState);
			
		}
		Playerinterface.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getWorld().render(g);
		Playerinterface.render(g);
		g.setColor(Color.WHITE);
		g.fillOval((int)(handler.getWidth()-300 + handler.getWorld().getEntityManager().getPlayer().getX()/30)
				,(int)(handler.getHeight()-100 + handler.getWorld().getEntityManager().getPlayer().getY()/30)
				, 5, 5);
		g.setColor(Color.RED);
		g.fillRect(10, 10, (int)(handler.getWorld().getEntityManager().getPlayer().GetBlood()*300/maxblood),15);//maxblood*300
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 300, 15);
		
	}
	
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
			else
			{
				for(int k = 0; k < Playerinterface.getObjects().size();k++){
					if(Playerinterface.getObjects().get(k) == itemImg[i]){
						Playerinterface.getObjects().remove(k);
						itemImg[i] = null;
						System.out.println("Delete.");
					}
				}
			}
		}
		
	}
	
	
}
