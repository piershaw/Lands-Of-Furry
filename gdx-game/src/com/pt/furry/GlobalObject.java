////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector3;
//this is a static class it has all the properties of the game 
public final class GlobalObject {

	public static GameStates gamestate;
	//properties
	private static int Coins;
	private static int Employment;
	private static int Achievement;
	private static Weather weather;
	
	private static int gameScreenWidth;
	private static int gameScreenHeight;
	private static Vector3 centerofMap;

		public static void setGameScreenWidth(int gameScreenWidth)
		{
				GlobalObject.gameScreenWidth = gameScreenWidth;
		}

		public static int getGameScreenWidth()
		{
				return gameScreenWidth;
		}

		public static void setGameScreenHeight(int gameScreenHeight)
		{
				GlobalObject.gameScreenHeight = gameScreenHeight;
		}

		public static int getGameScreenHeight()
		{
				return gameScreenHeight;
		}
	

	public static int getCoins() {
		return Coins;
	}

	public static void setCoins(int coins) {
		Coins = coins;
	}

	public static int getEmployment() {
		return Employment;
	}

	public static void setEmployment(int employment) {
		Employment = employment;
	}

	public static int getAchievement() {
		return Achievement;
	}

	public static void setAchievement(int achievement) {
		Achievement = achievement;
	}


	
	public static Weather getWeather() {
		Random r = new Random();
		
		switch(r.nextInt(Weather.values().length)){
			case 0:
				GlobalObject.setWeather(Weather.Cold);
				break;
				//finish
		}
		
		return weather;
	}

	private static void setWeather(Weather w) {
		weather = w;
	}

	public static Vector3 getCenterofMap() {
		return centerofMap;
	}

	public static void setCenterofMap(Vector3 centerofMap) {
		GlobalObject.centerofMap = centerofMap;
	}
}
