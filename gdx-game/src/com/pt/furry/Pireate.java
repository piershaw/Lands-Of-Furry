package com.pt.furry;

////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
////  convert to a TextureRegion i just did this so i would not have blank space in the Texture Region for animations
////  saves a few bytes of space



import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.Color;


public class Pireate extends GameObject {
	
	public Pireate(String[] fileNames,String dir){
		Animation anime;
		Texture[] imgs = new Texture[fileNames.length];
	
		
		//for mem op i do it this way
		for(int n = 0; n < fileNames.length; n++){
				imgs[n] = new Texture(Gdx.files.internal(fileNames[n]));
				//Sprite s = new Sprite(new Texture(Gdx.files.internal(fileNames[n])));
				//s.setTexture(imgs[n]);
				//s.setCenter(8,8);
				//s.rotate(30);
				//imgs[n] = s.getTexture();
			}
		
 		TextureRegion[] imageFrames = new TextureRegion[fileNames.length];
 		
 		
 		TextureRegion[][] tm = new TextureRegion[0][0];
 		
 		for (int r = 0; r < fileNames.length; r++){
	 		tm = TextureRegion.split(imgs[r] , 16, 16);
	 		imageFrames[r] = tm[0][0];
	 		
	 		if(dir == "south"){
	 			imageFrames[r].flip(false, true);
	 		}else if(dir == "north"){
	 			imageFrames[r].flip(false, false);
	 		}else if(dir == "east"){
	 			imageFrames[r].flip(true, false);
	 		}else{
	 			imageFrames[r].flip(false, false);
	 		}
 		}
 		
	 	//System.out.print("imageFrames " +imageFrames.length);
		anime = new Animation(0.012f,imageFrames);
		setCurrentAnimation(anime);
	}

}
