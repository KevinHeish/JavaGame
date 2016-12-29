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
	private UIObject[] itemImg;
	private UIObject skillImage;
	private UIManager Playerinterface;
	private int maxblood = -1;
	private boolean[] bagIsFull = {false, false};
	
	public GameState(GameHandler handler){
		super(handler);		
		handler.getMouseManager().setUIManager(null);
		
		Playerinterface = new UIManager(handler);
		Playerinterface.addObject(new UIImage(30, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(150, handler.getHeight()*4/5, 120, 120,Assets.bagLoc_img));
		Playerinterface.addObject(new UIImage(handler.getWidth()-300, handler.getHeight()-100, 300, 100,Assets.mapall));
		skillImage = new UIImage(10, 40, 50, 50,handler.getGame().getPlayerRender().getSkillImage());
		maxblood = handler.getGame().getPlayerRender().getHp();
		itemImg = new UIObject[2];
	}
	
	@Override
	public void tick() {
		handler.getKeyManager().tick();
		handler.getGame().getEntityRenders().tick();
		getItem();
		if(handler.getGame().getPlayerRender().getHp() < 1)
		{
			handler.getGame().getPlayerRender().setcharIndex(-1);
		}
		if(isGameEnd())
		{
			handler.getGame().buildEnd();
			State.setState(handler.getGame().endState);
		}
		
		
		Playerinterface.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getGame().getMap().render(g);
		handler.getGame().getEntityRenders().render(g);
		skilldraw();
		
		Playerinterface.render(g);
		
		g.setColor(Color.WHITE);
		g.fillOval((int)(handler.getWidth()-300 + handler.getGame().getPlayerRender().getX()/30)
				,(int)(handler.getHeight()-100 + handler.getGame().getPlayerRender().getY()/30)
				, 5, 5);
		
		for(int i = 0 ; i < 3 ; i++){
			if(i==0)g.setColor(Color.BLUE);
			else if(i==1)g.setColor(Color.GREEN);
			else if(i==2)g.setColor(Color.ORANGE);
			g.fillOval((int)(handler.getWidth()-300 + handler.getGame().getOtherPlayers()[i].getX()/30)
					,(int)(handler.getHeight()-100 + handler.getGame().getOtherPlayers()[i].getY()/30)
					, 5, 5);
		}
		
		g.setColor(Color.RED);
		
		if(handler.getGame().getPlayerRender().getHp()>=0)
			g.fillRect(10, 10, (int)(handler.getGame().getPlayerRender().getHp()*300/maxblood),15);
		else
			g.fillRect(10, 10, 0,15);
	
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 300, 15);
		g.drawRect(10, 40, 50, 50);
		
		
	}
	
	public boolean isGameEnd()
	{
		int count = 0;
		
		if(handler.getGame().getPlayerRender().getIndex()==-1)
			count++;
		
		for(int i = 0 ; i < 3 ; i++)
		{
			if(handler.getGame().getOtherPlayers()[i].getIndex() == -1)
				count++;
			if(count==1)return true;
			
		}
		
		return false;
	}
	
	
	public void skilldraw(){
		if(handler.getGame().getPlayerRender().getSkillUse())
		{
			for(int k = 0; k < Playerinterface.getObjects().size();k++){
				if(Playerinterface.getObjects().get(k) == skillImage)
				{
					return;
				}
			}
				Playerinterface.addObject(skillImage);
		}
		
		else
		{
			for(int k = 0; k < Playerinterface.getObjects().size();k++){
				if(Playerinterface.getObjects().get(k) == skillImage){
					Playerinterface.getObjects().remove(k);
					System.out.println("Delete.");
					break;
				}
			}
		}
		
	}
	
	public void getItem(){
		int[] bag = handler.getGame().getPlayerRender().getBag();
		
		
		for(int i= 0 ; i < 2 ; i++){
			if(bag[i]!=-1){
				if(bagIsFull[i] == false){
					itemImg[i] = new UIImage(45+i*120, handler.getHeight()*4/5+15, 90, 90,
						Assets.skill[bag[i]]);
					Playerinterface.addObject(itemImg[i]);
					bagIsFull[i] = true;
				}
			}
			else
			{
				for(int k = 0; k < Playerinterface.getObjects().size();k++){
					if(Playerinterface.getObjects().get(k) == itemImg[i]){
						Playerinterface.getObjects().remove(k);
						itemImg[i] = null;
						bagIsFull[i] = false;
						break;
					}
				}
			}
		}
		
	}
	
	
}
