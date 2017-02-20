////  Created by Pier Shaw on 4/14/14
////  Copyright © 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.controllers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Player extends GameObject {

	private Ship currentShip;
	private PlayerBody currentBody;
	private int speed;
	private int mode;
	private Animation anime;
	
	    public Player(){    
	    	
	    	setCurrentShip(new Ship(0));//somthing the get changed in globles 
	    	setCurrentBody(new PlayerBody(0));
	    	setMode(1);
	    }
	    
	 
	  		public Animation setAnime(int animationGroup){
	  			
	  			switch(animationGroup){
	  			
	  			case 0:
	  				//north
	  				this.anime = getCurrentShip().animeNorth;
	  				break;
	  				
	  			case 1:
	  				//south
	  				this.anime = getCurrentShip().animeSouth;
	  				break;
	  				
	  			case 2:
	  				//east
	  				this.anime = getCurrentShip().animeEast;
	  				break;
	  				
	  			case 3:
	  				//west
	  				this.anime = getCurrentShip().animeWest;
	  				break;
	  				
	  			case 4:
	  				//north
	  				this.anime = getCurrentShip().animeNorthIdel;
	  				break;
	  				
	  			case 5:
	  				//south
	  		
	  				this.anime = getCurrentShip().animeSouthIdel;
	  				break;
	  				
	  			case 6:
	  				//east
	  				this.anime = getCurrentShip().animeEastIdel;
	  				break;
	  				
	  			case 7:
	  				//west
	  				this.anime = getCurrentShip().animeWestIdel;
	  				break;
	  				
	  				default:
	  				this.anime = getCurrentShip().animeNorthIdel;
	  				break;
	  		}
	  					
	  			return this.anime;
	  				
	  		}


			public Ship getCurrentShip() {
				return currentShip;
			}


			public void setCurrentShip(Ship currentShip) {
				this.currentShip = currentShip;
			}


			public PlayerBody getCurrentBody() {
				return currentBody;
			}


			public void setCurrentBody(PlayerBody currentBody) {
				this.currentBody = currentBody;
			}


			public int getSpeed() {
				if(mode == 1){
					setSpeed(getCurrentShip().getSpeed());
				}else if(mode == 2){
					setSpeed(getCurrentBody().getSpeed());
				}
				return speed;
			}


			public void setSpeed(int speed) {
				this.speed = speed;
			}


			public int getMode() {
				return mode;
			}


			public void setMode(int mode) {
				this.mode = mode;
			}

}
