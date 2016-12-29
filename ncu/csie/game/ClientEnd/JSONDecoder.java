package ncu.csie.game.ClientEnd;

import org.json.*;

public class JSONDecoder {
	private GameHandler handler;
	
	public JSONDecoder(GameHandler handler)
	{
		this.handler = handler;
	}
	
	public void parser(String recieveData)
	{
		JSONArray dataSet = new JSONArray(recieveData);
		JSONObject parseData;
		int playerCount , monstersCount, itemCount, id , index = 0 ;
		double x , y ;
		
		parseData = dataSet.getJSONObject(0);
		
		playerCount = parseData.getInt("players");
		monstersCount = parseData.getInt("monsters");
		itemCount = parseData.getInt("items");
		
		
		for(int i = 1 ; i < dataSet.length(); i++){
			parseData = dataSet.getJSONObject(i);
		
			x = parseData.getDouble("x");
			y = parseData.getDouble("y");
			id = parseData.getInt("id");
			
			if(i <= playerCount){
				int newHp;
				int[] itemId = {-1 , -1};
				String direction;
				boolean skillState;
					
				newHp = parseData.getInt("hp");
				skillState = parseData.getBoolean("skill");
				direction = parseData.getString("direction");
				itemId[0] = parseData.getInt("item1");
				itemId[1] = parseData.getInt("item2");
				
				if(i == handler.getGame().getLocalPlayerId())
					handler.getGame().getPlayerRender().update((int)x,(int) y, newHp, 
							skillState, direction,itemId[0] , itemId[1]);
				else{
					handler.getGame().getOtherPlayers()[index].setcharIndex(id);
					handler.getGame().getOtherPlayers()[index++].update((int)x,(int) y, direction);
				}
			}
			else if(i <= playerCount + monstersCount){
				int direction = parseData.getInt("direction");
				handler.getGame().getMonsters().get(i - (playerCount + 1)).update((int)x, (int)y, direction);
			}
			else if(i <= playerCount + monstersCount + itemCount){
				handler.getGame().getItems().get(i-(playerCount + monstersCount + 1)).update((int)x, (int)y, id);
			}
			else{
				break;
			}
		}
		
	}
}
