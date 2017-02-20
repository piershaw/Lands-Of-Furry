////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public abstract class GameObject implements InputProcessor {
		private int posX,posY;
		private int fullSpriteSize;
		
		private Animation currentAnimation;
		protected ImagesNames name;
		//protected Stack<Animation> animes;
	    class TouchInfo {
	        public float touchX = 0;
	        public float touchY = 0;
	        public boolean touched = false;
	    }
	    
	    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
		public GameObject(){
			//animes = new Stack<Animation>();
		    Gdx.input.setInputProcessor(this);
		}


		public void setPosX(int posX)
		{
				this.posX = posX;
		}

		public int getPosX()
		{
				return posX;
		}
		
		
		public void setPosY(int posY)
		{
				this.posY = posY;
		}

		public int getPosY()
		{
				return posY;
		}
	
		public Animation getCurrentAnimation() {
			return currentAnimation;
		}
	
		public void setCurrentAnimation(Animation currentAnimation) {
			this.currentAnimation = currentAnimation;
		}
		
		
		public void message(String s){
				BitmapFont msg = new BitmapFont();
				msg.setColor(Color.BLUE);
				//msg.setScale(16);
		}
		
		public int getFullSpriteSize() {
			return fullSpriteSize;
		}


		public void setFullSpriteSize(int fullSpriteSize) {
			this.fullSpriteSize = fullSpriteSize;
		}
		
		

		@Override
		public boolean keyDown(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean keyTyped(char arg0) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean keyUp(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean mouseMoved(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean scrolled(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean touchDragged(int arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			return false;
		}
			
		/*
			if(Gdx.input.isTouched()){
				
			
				
			}
			
		        
		        message = "";
		       
		        for(int i = 0; i < 5; i++){
		            if(touches.get(i).touched){
		                //message += "Finger:" + Integer.toString(i) + "touch at:" + Float.toString(touches.get(i).touchX) + "," + Float.toString(touches.get(i).touchY) +"\n";
		            	 message += "Finger:" + Float.toString(distance);
		            	   // distance between 2 points        	 
		            	distance = touches.get(i).touchX - touches.get(i).touchY;
		            }    
		        }
		        
		        //float x = w/2 - tb.width/2;
		        //float y = h/2 + tb.height/2;
		     
		    }*/

		   
		

		    
		
		

}

