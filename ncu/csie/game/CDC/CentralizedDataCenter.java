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
		String test = new String();
		ArrayList<Player> playerList = handler.getWorld().getPlayers();
		ArrayList<Monster> monsterList = handler.getWorld().getMonsters();
		ArrayList<Item> itemList  = handler.getWorld().getItems();
		assert monsterList.size()==25;
		
		JSONArray allInfo = new JSONArray();
		
		
		for(int i = 0 ; i < playerList.size() ;i++)
		{
			Map map = new HashMap();
			
			map.put("id", playerList.get(i).getConnectionId());
			map.put("x", playerList.get(i).getX());
			map.put("y", playerList.get(i).getY());
			map.put("skill", playerList.get(i).GetSkillUse());
			map.put("hp", playerList.get(i).GetBlood());
			map.put("direction" , playerList.get(i).getDirection());
			
			
			
			JSONObject JSONPlayer = new JSONObject(map);
			allInfo.put(JSONPlayer);
		}
		
		for(int i = 0; i < monsterList.size(); i++ ){
			Map map = new HashMap();
			
			map.put("id",monsterList.get(i).GetID());
			map.put("x",monsterList.get(i).getX());
			map.put("y",monsterList.get(i).getY());
			
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
		test = allInfo.toString();
		
		return test;
	}
}
