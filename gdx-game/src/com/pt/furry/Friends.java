////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;
import java.security.*;

public abstract class Friends {
	private long currentTime,lastTime,updateFrequency; 
	private AnimationState anime;
	private Display disply;
//make set get
	
	private float weight,strength,intellegence;
	private float speed,energy,hunger,hardworking;
	private float worksWellWOA;
	
	
	private char drawType;
	public int x,y;// make set get
	private enum sex{
		male,
		female
	}
	


//		animestate = "thinking";
//		speed = 0;
//		doing("the "+ kind+" is "+animestate);
//		update();
//	
	
	public Friends(){
		//keep
		//updateFrequency = 600;
		//updateTime();
		
		
	
	}
	
	//change in unity
	public void updateTime(){
		
		while(GlobalObject.gamestate == GameStates.playing){
			currentTime = System.currentTimeMillis();
			if(currentTime > lastTime + updateFrequency){
				lastTime = currentTime;
				disply = new Display();
				update();
				//render();
				disply.clear();
				disply = null;
			}
		}
	}

	public void render(int x,int y, char c) {
		this.x = x;
		this.y = y;
		disply = new Display();
		disply.map[y][x] = c;
		disply.drawScreen();
	}

	private void update() {
		
		if(x >= disply.rowlength){
			x = disply.rowlength-1;
		}else{
			x+=1;
			//System.out.println("is playing ");
		}
		
		
		if(y >= disply.collength){
			y = disply.collength-1;
		}else{
			//y+=1;
		}
		
		//might move
	      switch(0){//enum
				  case 0:
				  anime = AnimationState.idie;
				  break;
			}
			
			//System.out.println("is playing abimation " + anime);
	}
	
	public void setPosition(int x,int y){
		this.x = x;
		this.y = y;
		render(x,y,' ');
	}
}
