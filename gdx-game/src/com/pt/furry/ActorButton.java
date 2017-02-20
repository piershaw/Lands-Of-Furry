package com.pt.furry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Rectangle;


public class ActorButton extends Actor implements InputProcessor {
	    private TextureRegion region;
	    private float X,Y;
	    private float mouseY,mouseX;
	    private Rectangle rect;
	 
	    
	    public ActorButton(float x, float y){
	    	this.X = x;
	    	this.Y = y;
	    	Texture temp = new Texture(Gdx.files.internal("bottomuiblock.gif"));
	        setImage(new TextureRegion(temp));
	        rect = new Rectangle(x-60,y-60,x+80,y+80);
	   
	        
	    }

		public TextureRegion getImage() {
			return region;
		}

		
		public float getX() {
			return X;
		}
		
		public float getY() {
			return Y;
		}


		public void setImage(TextureRegion region) {
			this.region = region;
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
		public boolean mouseMoved(int x, int y) {
			//Hover
			Rectangle point = new Rectangle(x-20,y-20,x + 40,y +40);
			Rectangle rect = new Rectangle(X-60,Y-60,X+120,Y+120);
			mouseX = x;
			mouseY = y;
			//System.out.println(point.x);
			//System.out.println(y);
			System.out.println(point.x);
			System.out.println(rect.x);
			if(rect.contains(point)){
				System.out.println("Button hover");
			}
			
			
			return false;
		}

		@Override
		public boolean scrolled(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int x, int y, int arg2, int arg3) {

			return false;
		}

		@Override
		public boolean touchDragged(int x, int y, int arg2) {
				mouseX = x;
				mouseY = y;
			return false;
		}

		@Override
		public boolean touchUp(int x, int y, int arg2, int arg3) {
			Rectangle point = new Rectangle(mouseX-20,mouseY-20,mouseX + 40,mouseY +40);
			Rectangle rect = new Rectangle(X-60,Y-60,X+120,Y+120);
		
			if(rect.contains(point)){
				System.out.println("click");
				GlobalObject.gamestate = GameStates.playing;
			}
			return false;
		}

	
		
	  
	}

