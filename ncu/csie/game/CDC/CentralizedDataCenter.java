package ncu.csie.game.CDC;

import java.util.ArrayList;
/*
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
*/
import java.util.HashMap;
import java.util.Map;

import org.json.*;


import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.item.Item;
import ncu.csie.game.worlds.Handler;

public class CentralizedDataCenter {
	private Handler handler;
	
	public CentralizedDataCenter(Handler handler){
		this.handler = handler;
	}
	
	public String Encoder(){
		String result = new String();
		ArrayList<Player> playerList = handler.getWorld().getPlayers();
		ArrayList<Monster> monsterList = handler.getWorld().getMonsters();
		ArrayList<Item> itemList  = handler.getWorld().getItems();
		JSONArray allInfo = new JSONArray();
		
		Map count = new HashMap();
		
		count.put("players", playerList.size());
		count.put("monsters", monsterList.size());
		count.put("items", itemList.size());
		JSONObject sizeCounts = new JSONObject(count);
		allInfo.put(sizeCounts);
		
		
		for(int i = 0 ; i < playerList.size() ;i++)
		{
			Map map = new HashMap();
			
			map.put("id", playerList.get(i).getID());
			map.put("x", playerList.get(i).getX());
			map.put("y", playerList.get(i).getY());
			map.put("skill", playerList.get(i).GetSkillUse());
			map.put("hp", playerList.get(i).GetBlood());
			map.put("direction" , playerList.get(i).getDirection());
			
			if(playerList.get(i).GetBag(0)!=null)
				map.put("item1", playerList.get(i).GetBag(0).getId());
			else
				map.put("item1", -1);
			
			if(playerList.get(i).GetBag(1)!=null)
				map.put("item2", playerList.get(i).GetBag(1).getId());
			else
				map.put("item2", -1);
			
			
			JSONObject JSONPlayer = new JSONObject(map);
			allInfo.put(JSONPlayer);
		}
		
		for(int i = 0; i < monsterList.size(); i++ ){
			Map map = new HashMap();
			
			map.put("id",monsterList.get(i).GetID());
			map.put("x", monsterList.get(i).getX());
			map.put("y", monsterList.get(i).getY());
			map.put("direction", monsterList.get(i).getDirection());
			
			JSONObject JSONMonster = new JSONObject(map);
			allInfo.put(JSONMonster);
		}
		
		for(int i = 0 ; i< itemList.size(); i++)
		{
			Map map = new HashMap();
			
			map.put("id", itemList.get(i).getId());
			map.put("x",itemList.get(i).getX());
			map.put("y",itemList.get(i).getY());
			
			JSONObject JSONItem = new JSONObject(map);
			allInfo.put(JSONItem);
		}
		result = allInfo.toString();
		
		return result;
	}
}
