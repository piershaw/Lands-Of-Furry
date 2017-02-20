////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.controllers.Controller;
//import com.badlogic.gdx.controllers.ControllerListener;
//import com.badlogic.gdx.controllers.Controllers;
//import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.Texture;

public class GameMap extends GameObject implements InputProcessor{//,//ControllerListener{
		private static final int scaleBB = 1;
	
		public Texture[][] map = new Texture[128][128];
		public Texture[][] visable = new Texture[128][128];
		public Animation[][] ocean = new Animation[128][128];
		public Animation[][] objects = new Animation[128][128];
		public Animation[][] player = new Animation[128][128];
		
		public Stack<String> direction = new Stack<String>();
		private static Player selectedPlayer;
	
		//private Pireate shipIdleNorth,shipNorth,shipIdleSouth,shipSouth,shipIdleEast,shipEast,shipIdleWest,shipWest;
		
		private String message;
		private boolean ok,uiOn;
		private int boardSize,updateTime;
		private String screenTittle;
		//private Controller controller;
		private int mapPositionX,mapPositionY,lastPosX,lastPosY;
		
	
		private static String mapDirction,lastMapDirction;
		private static int joyButton,nextX,nextY,pervX,pervY,timeof;
		private static float distance;
		private static double idelTimmer;
		private static int incoderY;
		private static int incoderX;
	
		
		public static long timeNow,time,timePass;// debug take out
		
		private static Texture grass1A,grass2A,grass3A,grass4A,grass5A,grass6A,grass7A,grass8A,beachhills2A,beachlightA,treesdark4A,visableBlock;
		private static Texture treesA,mosse2A,mosse3A,mosse4A,reff4A,reff3A,sandA,treeslightA,boldersA,reffA,treesl2A,trees4colorA;
		private static Animation anime;
		
		
		public Texture oceanA;
		public Animation oceanTexture;
		
		
		
		public GameMap(){
				
				mapPositionX = getPosX();
				mapPositionY = getPosY();
				
				grass1A = new Texture(Gdx.files.internal("grass1.gif"));
				grass2A = new Texture(Gdx.files.internal("grass2.gif"));
				grass3A = new Texture(Gdx.files.internal("grass3.gif"));
				grass4A = new Texture(Gdx.files.internal("grass4.gif"));
				grass5A = new Texture(Gdx.files.internal("grass5.gif"));
				grass6A = new Texture(Gdx.files.internal("grass6.gif"));
				grass7A = new Texture(Gdx.files.internal("grass7.gif"));
				grass8A = new Texture(Gdx.files.internal("grass8.gif"));
				//beachhillsA = new Texture(Gdx.files.internal("beachhills.gif"));
				beachhills2A = new Texture(Gdx.files.internal("beachhills2.gif"));
				beachlightA = new Texture(Gdx.files.internal("beachlight.gif"));
				//hilllightA = new Texture(Gdx.files.internal("hilllight.gif"));
				treesA = new Texture(Gdx.files.internal("trees.gif"));
				treesl2A = new Texture(Gdx.files.internal("treesl2.gif"));
				trees4colorA = new Texture(Gdx.files.internal("trees4color.gif"));
				mosse2A = new Texture(Gdx.files.internal("mosse2.gif"));
				mosse3A = new Texture(Gdx.files.internal("mosse3.gif"));
				mosse4A = new Texture(Gdx.files.internal("mosse4.gif"));
				reffA = new Texture(Gdx.files.internal("reff.gif"));
				reff3A = new Texture(Gdx.files.internal("reff3.gif"));
				reff4A = new Texture(Gdx.files.internal("reff4.gif"));
				//reff5A = new Texture(Gdx.files.internal("reff5.gif"));
				sandA = new Texture(Gdx.files.internal("sand.gif"));
				treesdark4A = new Texture(Gdx.files.internal("treesdark4.gif"));
				treeslightA = new Texture(Gdx.files.internal("treeslight.gif"));
				boldersA = new Texture(Gdx.files.internal("bolders.gif"));
				oceanA = new Texture(Gdx.files.internal("ocean.gif"));
				
				visableBlock = new Texture(Gdx.files.internal("blankuiblock.gif"));
				//anime
				oceanTexture = makeAnimation("ocean.gif",2,2);
			
				//all objects fix to ship objects array
			  	selectedPlayer = new Player();
			    //mapDirction = "north";
			    //lastMapDirction = "north";
					setScreenTittle("World Map");
					
					player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(4);
					
				
				for(int i = 0; i < 6; i++)
					direction.add("");
					
				
			 //System.out.println("objects " + shipFoward.animes.size());
			 //player = makeAnimation("catanime.png",4,4);
			 //test.setPosX(Gdx.graphics.getWidth());
			 //test.setPosY(Gdx.graphics.getHeight());
				
				boardSize = 16;//pixles
				//Gdx.input.setInputProcessor(this);
		    	//for(Controller c : Controllers.getControllers())
		    		//controller = c;
		    	//controller.addListener(this);
		    	//Controllers.addListener(this);
		    	joyButton = -1;
		    	//incoder
		    	incoderX = -1;
		    	
		    	for(int b = 0; b < 128; b++){
		    		for(int bb = 0; bb < 128; bb++){
		    				visable[b][bb] = visableBlock;
						}
				}
				
				//clear
		    	visable[64-Camera.x-1][64-Camera.y] = null;
				visable[64-Camera.x+1][64-Camera.y] = null;	
				visable[64-Camera.x][64-Camera.y+1] = null;	
				visable[64-Camera.x][64-Camera.y-1] = null;	
				visable[64-Camera.x][64-Camera.y] = null;
				
		    	for(int nextX = 0; nextX < 128; nextX++){//row
		    		this.nextX = nextX;
		    		for(int nextY = 0; nextY < 128; nextY++){	//next col
		    			this.nextY = nextY;
		    			incoderX++;
		    			//map[nextY][nextX] = treesl2A; //test
		    			ocean[nextY][nextX] = oceanTexture;
							findImage();
							
		    		}
		    	}
		    	
		 
		    	
		}

		public void setBoardSize(int boardSize)
		{
				this.boardSize = boardSize;
		}

		public int getBoardSize()
		{
				return boardSize;
		}

		public void setUpdateTime(int updateTime)
		{
				this.updateTime = updateTime;
		}

		public int getUpdateTime()
		{
				return updateTime;
		}

		public void setScreenTittle(String screenTittle)
		{
				this.screenTittle = screenTittle;
		}

		public String getScreenTittle()
		{
				return screenTittle;
		}

		


		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public void update(){
			
			/*if(uiOn){
				if(idelTimmer > 180){
					idelTimmer = 0;
					uiOn = false;
				}else{
					idelTimmer += 60 * Gdx.graphics.getDeltaTime();
				}
			}
		*/
		///	System.out.println(idelTimmer);
			if(ok!= true) return;
			
			if(Gdx.input.isTouched(0)){ // 0 for phone 1 for drsktop
				
				if(Gdx.input.getDeltaX() > GlobalPrfs.joyforce){
						mapDirction = "east";
				}else if(Gdx.input.getDeltaX() < -GlobalPrfs.joyforce){
						mapDirction = "west";
				}else if(Gdx.input.getDeltaY() < -GlobalPrfs.joyforce){
						mapDirction = "north";
				}else if(Gdx.input.getDeltaY() > GlobalPrfs.joyforce){
						mapDirction = "south";
				}
			}
			
			//joystick keep works
//			if(Controllers.getControllers().size > 0){
//				mapPositionX = getPosX();
//		    	mapPositionY = getPosY();
//				if(joyButton == 5){
//						mapDirction = "right";
//				}else if(joyButton == 7){.
//						mapDirction = "left";
//				}else if(joyButton == 4){
//						mapDirction = "up";
//				}else if(joyButton == 6){
//						mapDirction = "down";
//				}
//			}
			
		
		
			
				
		  	//clear
			  player[64-Camera.x][64-Camera.y] = null; 
				//with out lamp
				visable[64-Camera.x-1][64-Camera.y] = null;
				visable[64-Camera.x+1][64-Camera.y] = null;	
				visable[64-Camera.x][64-Camera.y+scaleBB] = null;	
				visable[64-Camera.x][64-Camera.y-scaleBB] = null;	
				visable[64-Camera.x][64-Camera.y] = null;
				// all input movement
			if(mapDirction == "west"){
					
					
					//colligen detection 
					if(map[64-Camera.x][64-Camera.y] != oceanA){
							Camera.x++;
					}else{
							Camera.x++;
							player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(3);
							
					
					ok = false;
					}
			}else if(mapDirction == "east"){
					if(map[64-Camera.x][64-Camera.y] != oceanA){
							Camera.x--;
					}else{
							Camera.x--;
							player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(2);
					}
				
					ok = false;
			}
			
			if(mapDirction == "south"){
					Camera.y++;
					player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(1);
					ok = false;
			}else if(mapDirction == "north"){
					Camera.y--;
					player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(0);
					ok = false;
			}
			
				
			//debug
			//time = timeToDo();
			//end of game loop
		}
			
		    @Override
		    public boolean keyDown(int keycode) {
		    	ok = true;
		    	setUiOn(true);
		        return false;
		    }

		    @Override
		    public boolean keyUp(int keycode) {
		    	ok = false;
		    
		    	
				/*	if(timeof==49){
							timeof = 0;
						}else{
						  timeof++;
						}
					setUpdateTime(timeof);*/
					
					
		    	lastMapDirction = mapDirction;
		    	if(lastMapDirction == "north"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(4);
		    	}else if(lastMapDirction == "south"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(5);
		    	}else if(lastMapDirction == "west"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(7);
		    	}else if(lastMapDirction == "east"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(6);
		    	}
		    	
						if(direction.size() > 8){
							direction.removeElementAt(8);
						}else{
								direction.add(0,">" + lastMapDirction);
						}
					
		        return false;
		    }

		    @Override
		    public boolean keyTyped(char character) {
		    	mapPositionX = getPosX();
		    	mapPositionY = getPosY();
		    
		    	if(character == 'd'){
		    		ok = true;
					mapDirction = "east";
				}else if(character == 'a'){
					ok = true;
					mapDirction = "west";
				}else if(character == 'w'){
					ok = true;
					mapDirction = "north";
				}else if(character == 's'){
					ok = true;
					mapDirction = "south";
				}else{
					ok = false;
				}
		    	
		    	
		        return true;
		    }

		    @Override
		    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		    	mapPositionX = getPosX();
		    	mapPositionY = getPosY();
		      ok = true;
		        return true;
		    }

		    @Override
		    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		        //ok = false;
						setUiOn(true);
						
		    	lastMapDirction = mapDirction;
				if(lastMapDirction == "north"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(4); // make enum
		    	}else if(lastMapDirction == "south"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(5);
		    	}else if(lastMapDirction == "west"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(7);
		    	}else if(lastMapDirction == "east"){
		    		player[64-Camera.x][64-Camera.y] = selectedPlayer.setAnime(6);
		    	}
				
						/*lastMapDirction="";
						
						if(timeof==49){
								timeof = 0;
						}else{
								timeof++;
						}
						setUpdateTime(timeof);*/
						
				if(direction.size() > 8){
						direction.removeElementAt(8);
				}else{
						direction.add(0,">" + mapDirction);
				}
						TimeOfDay.update(getUpdateTime());
				
					
						
		        return true;
		    }

		    @Override
		    public boolean touchDragged(int screenX, int screenY, int pointer) {
		        // TODO Auto-generated method stub
		        return false;
		    }

		    @Override
		    public boolean mouseMoved(int screenX, int screenY) {
		        // TODO Auto-generated method stub
		        return false;
		    }

		    @Override
		    public boolean scrolled(int amount) {
		        // TODO Auto-generated method stub
		        return false;
		    }


			public float getDistance() {
				return distance;
			}


			public void setDistance(float distance) {
				this.distance = distance;
			}


//			@Override
//			public boolean accelerometerMoved(Controller arg0, int arg1,Vector3 arg2) {
//				//ok = true;
//				return false;
//			}


//			@Override
//			public boolean axisMoved(Controller arg0, int arg1, float arg2) {
//				//System.out.println("Controller axisMoved " +arg2);
//				//System.out.println("axis" + axis);
//				ok = true;
//				return false;
//			}
//
//
//			@Override
//			public boolean buttonDown(Controller arg0, int arg1) {
//				joyButton = arg1;
//				ok = true;
//				return false;
//			}
//
//
//			@Override
//			public boolean buttonUp(Controller arg0, int arg1) {
//				ok = false;
//				joyButton = -1;
//				return false;
//			}
//
//
//			@Override
//			public void connected(Controller arg0) {
//		
//			}
//
//
//			@Override
//			public void disconnected(Controller arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//
//			@Override
//			public boolean povMoved(Controller arg0, int arg1, PovDirection arg2) {
//				//ok = true;
//				
//				return false;
//			}
//
//
//			@Override
//			public boolean xSliderMoved(Controller arg0, int arg1, boolean arg2) {
//				//ok = true;
//				return false;
//			}
//
//
//			@Override
//			public boolean ySliderMoved(Controller arg0, int arg1, boolean arg2) {
//				//ok = true;
//				return false;
//			}
//

		

private void findImage(){
	
		if (MapData.mapData[incoderX] == 1 || MapData.mapData[incoderY] == 1) {
			map[nextX][nextY] = reff4A;
		} else if (MapData.mapData[incoderX] == 2
				|| MapData.mapData[incoderY] == 2) {
			map[nextX][nextY] = reff3A;
		} else if (MapData.mapData[incoderX] == 3
				|| MapData.mapData[incoderY] == 3) {
			map[nextX][nextY] = grass5A;
		} else if (MapData.mapData[incoderX] == 4
				|| MapData.mapData[incoderY] == 4) {
			map[nextX][nextY] = treesA;
		} else if (MapData.mapData[incoderX] == 5
				|| MapData.mapData[incoderY] == 5) {
			map[nextX][nextY] = sandA;
		} else if (MapData.mapData[incoderX] == 6
				|| MapData.mapData[incoderY] == 6) {
			map[nextX][nextY] = beachlightA;
		} else if (MapData.mapData[incoderX] == 7
				|| MapData.mapData[incoderY] == 7) {
			map[nextX][nextY] = reffA;
		} else if (MapData.mapData[incoderX] == 8
				|| MapData.mapData[incoderY] == 8) {
			map[nextX][nextY] = treeslightA;
		} else if (MapData.mapData[incoderX] == 9
				|| MapData.mapData[incoderY] == 9) {
			map[nextX][nextY] = grass8A;
		} else if (MapData.mapData[incoderX] == 10
				|| MapData.mapData[incoderY] == 10) {
			map[nextX][nextY] = grass4A;
		} else if (MapData.mapData[incoderX] == 11
				|| MapData.mapData[incoderY] == 11) {
			map[nextX][nextY] = trees4colorA;
		} else if (MapData.mapData[incoderX] == 12
				|| MapData.mapData[incoderY] == 12) {
			map[nextX][nextY] = grass7A;
		} else if (MapData.mapData[incoderX] == 13
				|| MapData.mapData[incoderY] == 13) {
			map[nextX][nextY] = boldersA;
		} else if (MapData.mapData[incoderX] == 14
				|| MapData.mapData[incoderY] == 14) {
			map[nextX][nextY] = treesl2A;
		} else if (MapData.mapData[incoderX] == 15
				|| MapData.mapData[incoderY] == 15) {
			map[nextX][nextY] = mosse4A;
		} else if (MapData.mapData[incoderX] == 16
				|| MapData.mapData[incoderY] == 16) {
			map[nextX][nextY] = beachhills2A;
		} else if (MapData.mapData[incoderX] == 17
				|| MapData.mapData[incoderY] == 17) {
			map[nextX][nextY] = grass1A;
		} else if (MapData.mapData[incoderX] == 18
				|| MapData.mapData[incoderY] == 18) {
			map[nextX][nextY] = grass3A;
		} else if (MapData.mapData[incoderX] == 19
				|| MapData.mapData[incoderY] == 19) {
			map[nextX][nextY] = mosse2A;
		} else if (MapData.mapData[incoderX] == 20
				|| MapData.mapData[incoderY] == 20) {
			map[nextX][nextY] = grass6A;
		} else if (MapData.mapData[incoderX] == 21
				|| MapData.mapData[incoderY] == 21) {
			map[nextX][nextY] = mosse4A;
		} else if (MapData.mapData[incoderX] == 22
				|| MapData.mapData[incoderY] == 22) {
			map[nextX][nextY] = treesdark4A;
		} else if (MapData.mapData[incoderX] == 23
				|| MapData.mapData[incoderY] == 23) {
			map[nextX][nextY] = grass4A;
		} else {
			map[nextX][nextY] = oceanA;
		}
	
}
//only oceanTexture
public Animation makeAnimation(String imageName,int rows,int cols){

		Texture image = new Texture(Gdx.files.internal(imageName));
		
		Sprite s = new Sprite(image);
		s.setCenter(0,0);
		s.rotate(0);
		
 		TextureRegion[] imageFrames = new TextureRegion[rows * cols];
 		int oindex = 0;
 		
	 	
	 		TextureRegion[][] tm = TextureRegion.split(s.getTexture(), image.getWidth() / rows, image.getHeight() / cols);
			for (int ox = 0; ox < rows; ox++)
					for (int oy = 0; oy < cols; oy++)
						imageFrames[oindex++] = tm[ox][oy];
			
			anime = new Animation(0.012f,imageFrames);
			anime.getKeyFrame(Gdx.graphics.getDeltaTime() * 0.5f/GlobalPrfs.oceanSpeed,true);
	 	
	 	
		return anime;
}


public Long timeToDo(){
	
		return System.currentTimeMillis() - timePass;
}
///testing
public Player getCurrentPlayer(){
	return selectedPlayer;
	
}

public boolean isUiOn() {
	return uiOn;
}

public void setUiOn(boolean uiOn) {
	this.uiOn = uiOn;
}

}

		






