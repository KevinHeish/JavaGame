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
		int players , monsters,id ;
		double x , y ;
		players = 1; 
		monsters = 25;
		
		
		
		/*=for(int i = 0 ; i < dataSet.length(); i++){
			parseData = dataSet.getJSONObject(i);*/
		
			parseData = dataSet.getJSONObject(0);
			x = parseData.getDouble("x");
			y = parseData.getDouble("y");
			id =parseData.getInt("id");
			
			//if(i < players){
				int newHp;
				String direction;
				boolean skillState;
				
				
				newHp = parseData.getInt("hp");
				skillState = parseData.getBoolean("skill");
				direction = parseData.getString("direction");

				handler.getGame().getPlayerRender().update((int)x,(int) y, newHp, skillState, direction);
			/*}
			else if(i < monsters){
				
			}
			else{
			
			}
			
		}*/
		
		
		
	}
}
