////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.

package com.pt.furry;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.Color;

public class Ship {
	
	private int speed;
	
	public Animation animeNorth;
	public Animation animeSouth;
	public Animation animeEast;
	public Animation animeWest;

	public Animation animeNorthIdel;
	public Animation animeSouthIdel;
	public Animation animeEastIdel;
	public Animation animeWestIdel;
	
	private TextureRegion[] northFrames;
	private TextureRegion[] southFrames;
	private TextureRegion[] eastFrames;
	private TextureRegion[] westFrames;

	private TextureRegion[] northIDELFrames;
	private TextureRegion[] southIDELFrames;
	private TextureRegion[] eastIDELFrames;
	private TextureRegion[] westIDELFrames;


		///hum maybe a better way but just for now can  make sprites or something
		//i did this because the old way i could not do what i wonted I have to make this into a class of its own with the
		//animated obj as not a separate obj but as one object for the stats like life and keeping it as one hole object  
		public Ship(int shipNumber){
				
				Texture[] imgs = new Texture[8];
				northFrames = new TextureRegion[2];
				southFrames = new TextureRegion[2];
				eastFrames = new TextureRegion[2];
				westFrames = new TextureRegion[2];

				northIDELFrames = new TextureRegion[2];
				southIDELFrames = new TextureRegion[2];
				eastIDELFrames = new TextureRegion[2];
				westIDELFrames = new TextureRegion[2];
				
				switch(shipNumber){
						case 0:
								//NorthSouth group
								imgs[0] = new Texture(Gdx.files.internal("ship1fwd.gif"));
								imgs[1] = new Texture(Gdx.files.internal("ship1fwd2.gif"));
								//EastWest group
								imgs[2] = new Texture(Gdx.files.internal("ship1fwdflip.gif"));
								imgs[3] = new Texture(Gdx.files.internal("ship1fwd2flip.gif"));
								//NorthSouth group Idel
								imgs[4] = new Texture(Gdx.files.internal("ship1idel.gif"));
								imgs[5] = new Texture(Gdx.files.internal("ship1idel2.gif"));
								//EastWest group Idel
								imgs[6] = new Texture(Gdx.files.internal("ship1idelflip.gif"));
								imgs[7] = new Texture(Gdx.files.internal("ship1idel2flip.gif"));
								setSpeed(13);//Knots
						break;
								
				}
				
				
				TextureRegion[][] t = new TextureRegion[0][0];

				t = TextureRegion.split(imgs[0] , 16, 16);
				northFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[1] , 16, 16);
				northFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[0] , 16, 16);
				southFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[1] , 16, 16);
				southFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[2] , 16, 16);
				eastFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[3] , 16, 16);
				eastFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[2] , 16, 16);
				westFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[3] , 16, 16);
				westFrames[1] = t[0][0];
				
				
				t = TextureRegion.split(imgs[4] , 16, 16);
				northIDELFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[5] , 16, 16);
				northIDELFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[4] , 16, 16);
				southIDELFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[5] , 16, 16);
				southIDELFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[6] , 16, 16);
				eastIDELFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[7] , 16, 16);
				eastIDELFrames[1] = t[0][0];
				
				t = TextureRegion.split(imgs[6] , 16, 16);
				westIDELFrames[0] = t[0][0];
				t = TextureRegion.split(imgs[7] , 16, 16);
				westIDELFrames[1] = t[0][0];
		
				//System.out.print("imageFrames " +imageFrames.length);
				northFrames[0].flip(false, false);
				northFrames[1].flip(false, false);
				animeNorth = new Animation(0.012f,northFrames);
				
				southFrames[0].flip(false, true);
				southFrames[1].flip(false, true);
				animeSouth = new Animation(0.012f,southFrames);
				
				eastFrames[0].flip(true, false);
				eastFrames[1].flip(true, false);
				animeEast = new Animation(0.012f,eastFrames);
				
				westFrames[0].flip(false, false);
				westFrames[1].flip(false, false);
				animeWest = new Animation(0.012f,westFrames);
				
				northIDELFrames[0].flip(false, false);
				northIDELFrames[1].flip(false, false);
				animeNorthIdel = new Animation(0.012f,northIDELFrames);
				
				southIDELFrames[0].flip(false, true);
				southIDELFrames[1].flip(false, true);
				animeSouthIdel = new Animation(0.012f,southIDELFrames);
				
				eastIDELFrames[0].flip(true, false);
				eastIDELFrames[1].flip(true, false);
				animeEastIdel = new Animation(0.012f,eastIDELFrames);
				
				westIDELFrames[0].flip(false, false);
				westIDELFrames[1].flip(false, false);
				animeWestIdel = new Animation(0.012f,westIDELFrames);
				
		}
		
		

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}

}
