////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//move
//import com.badlogic.gdx.contorllers;

public class MyGdxGame implements ApplicationListener{
	
	//move to globel
		
	//player class
	private SpriteBatch batch;
	
	//gameMap
	private float time;
	private static int FPS;
	private boolean uiOn,mainGameVisible,startVisible;
	
	private GameMap gameMap;
	private int boxHeight,boxLength,boxHeight2,camLeft,camRight,camTop,camBottom;
	//move
	private Texture lui,rui,tui,bui,oui,uui,xui,lsu,rsu,ui,lbu,rbu,weather,gold;
	private ActorButton startGameButton;
	//private GameObject test;
	private BitmapFont msg1;
	public static iBlueTooth blueTooth;
	 

	@Override
	public void create(){
		startVisible = true;
		mainGameVisible = false;
		gameMap = new GameMap();
		gameMap.setPosX(Gdx.graphics.getWidth()/2 - 1024);
		gameMap.setPosY(Gdx.graphics.getHeight()/2 - 1024);
		TimeOfDay.setUpTimeOfDay();
	
	
		GlobalObject.gamestate = GameStates.start;
		GlobalObject.setGameScreenWidth(Gdx.graphics.getWidth());
		GlobalObject.setGameScreenHeight(Gdx.graphics.getHeight());
		Camera.CameraSetUp();
		
		batch = new SpriteBatch();
		uiOn = true;
		msg1 = new BitmapFont();
		msg1.setColor(Color.WHITE);
		//msg1.scale(0.01f);
		
		boxHeight = 0;
		boxHeight2 = 160;
		boxLength = 14;
		
		

		//layout map
	/*	test = new Pireate(ImagesNames.catanime);
		test.setPosX(Gdx.graphics.getWidth());
		test.setPosY(Gdx.graphics.getHeight());*/
		
		
		//were in the map he gos fix
			//for(int i = 0; i< 128; i++){
					//for(int g = 0; g < 128; g++)
							//map[g][i]
		
	
			
				//Gdx.input.setInputProcessor(this);
				//Gdx.input.setInputProcessor(player);
		mainGameUI();
	
	}
	
	
	//might move to screen render class
	@Override
	public void render(){     
		FPS = Gdx.graphics.getFramesPerSecond(); // debug take out
		
		Camera.camera.update();
		batch.setProjectionMatrix(Camera.camera.combined);
		//
		//Fix for device build
		//for my phone build
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//for my desktop eclipse
		// or GL20
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
	
		//i did this so instead for messing with how this libgdx drawa 3d on the screen
		//i jut made it so i loop not the hole tile map but only what you see this made the FPS go from
		// 1fps to 30-60 on mobile and desktop with just a few math changes
	if(GlobalObject.gamestate == GameStates.playing){
			
		Gdx.input.setInputProcessor(gameMap); // might move keep GameStates.playing
		camRight = (64-Camera.x)+16;
		camLeft = (64-Camera.x)-16;
		camBottom = (64-Camera.y)+8;
		camTop = (64-Camera.y)-8;
			
		//render game this is to 
		//limit the map render
		if(camLeft <= 0) 
			camLeft = 0;
				
		if(camRight >= 124) 
			camRight = 124;
		
		if(camTop <= 0) 
			camTop = 0;
		
		if(camBottom >= 124) 
			camBottom = 124;

			for(int x = camLeft; x < camRight; x++){
				for(int y = camTop; y < camBottom; y++){
					//layer 1 map
					if(gameMap.map[x][y] != gameMap.oceanA)
						if(!gameMap.map[x][y].equals(gameMap.player))
									batch.draw(gameMap.map[x][y],x*16+gameMap.getPosX()/2,y*16+gameMap.getPosY()/2,16,16);
								
					//layer 2 ocean
					if(gameMap.map[x][y].equals(gameMap.oceanA) || gameMap.map[x][y].equals(gameMap.player))
							batch.draw(gameMap.ocean[x][y].getKeyFrame(time,true),x*16+gameMap.getPosX()/2,y*16+gameMap.getPosY()/2,16,16);
					//layer 3 objects
				//	if(gameMap.objects[x][y] != null)
							//batch.draw(gameMap.objects[x][y].getKeyFrame(time,true),x*16+gameMap.getPosX()/2,y*16+gameMap.getPosY()/2,16,16);
					//layer 4 player
					if(gameMap.player[x][y] != null)
							batch.draw(gameMap.player[x][y].getKeyFrame(time,true),x*16+gameMap.getPosX()/2,y*16+gameMap.getPosY()/2,16,16);
					//layer 5 to hide stuff
					if(gameMap.visable[x][y] != null)
							batch.draw(gameMap.visable[x][y],x*16+gameMap.getPosX()/2,y*16+gameMap.getPosY()/2,16,16);
					//System.out.println("where I am "+ x*16+gameMap.getPosX()/2+ "Y " + y*16+gameMap.getPosY()/2);
				}
					
			}
			//render Ui
			if(gameMap.isUiOn())
				renderGameUI();	
			
		
			//msg1.draw(batch,"Pier Ship",Camera.camera.position.x-tui.getWidth(),Camera.camera.position.y+8);
			
			Camera.updateCameraPosition();
			
			gameMap.update();
			
			time += Gdx.graphics.getDeltaTime() * 0.05f;

			//debug
			GameMap.timePass = System.currentTimeMillis();
		}else if(GlobalObject.gamestate == GameStates.start){
			startScreen();
		}
	
	batch.end();
	
		
		
		//if(blueTooth.isConnected())
			//blueTooth.sendMessage("D");
	}
	

	
	
	
	
	
	 private void startScreen() {
		 batch.draw(startGameButton.getImage(),startGameButton.getX(),startGameButton.getY());
		
		
	}


	public static iBlueTooth getBlueTooth() {
        return blueTooth;
	 }

	 public static void setBlueTooth(iBlueTooth btm) {
		 blueTooth = btm;
	 }
	
	
	@Override
	public void dispose(){
		//	background.dispose();
			
	}

	@Override
	 public void resize(int width, int height) {
		Camera.camera.viewportWidth = 30f;
		Camera.camera.viewportHeight = 30f * height/width;
		Camera.camera.update();
	 }
	
	@Override
	public void pause(){ }

	@Override
	public void resume(){ }

	public void mainGameUI(){
		lui = new Texture(Gdx.files.internal("leftuiblock.gif"));
		rui = new Texture(Gdx.files.internal("rightuiblock.gif"));
		tui = new Texture(Gdx.files.internal("topuiblock.gif"));
		bui = new Texture(Gdx.files.internal("bottomuiblock.gif"));
		oui = new Texture(Gdx.files.internal("openuiblock.gif"));
		xui = new Texture(Gdx.files.internal("exituiblock.gif"));
		uui = new Texture(Gdx.files.internal("tabuiblock.gif"));
		rsu = new Texture(Gdx.files.internal("rightsideuiblock.gif"));
		lsu = new Texture(Gdx.files.internal("leftsideuiblock.gif"));
		ui = new Texture(Gdx.files.internal("blankuiblock.gif"));
		rbu = new Texture(Gdx.files.internal("rightbottomuiblock.gif"));
		lbu = new Texture(Gdx.files.internal("leftbottomuiblock.gif"));
		
		weather = new Texture(Gdx.files.internal("time05.gif"));//move
		gold = new Texture(Gdx.files.internal("gold.gif"));
		startGameButton = new ActorButton(Camera.camera.position.x,Camera.camera.position.y);
		Gdx.input.setInputProcessor(startGameButton);
		 
		
	}
	


	private void renderGameUI() {
		//title bar
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*14,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*13,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*12,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*11,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*10,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*9,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*8,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(gold,Camera.camera.position.x+xui.getWidth()*6,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*5,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*4,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*3,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth()*2,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x+xui.getWidth(),Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(TimeOfDay.getCurent(),Camera.camera.position.x-xui.getWidth(),Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*2,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*3,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*4,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*5,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*6,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*8,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*9,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*10,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*11,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*12,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*13,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*14,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*8);
		batch.draw(bui,Camera.camera.position.x-xui.getWidth()*16,Camera.camera.position.y+lui.getHeight()*8);



		msg1.draw(batch,gameMap.getScreenTittle(),Camera.camera.position.x-tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*9);
		msg1.draw(batch,"Weather:",Camera.camera.position.x-tui.getWidth()*5,Camera.camera.position.y+lui.getHeight()*9);
		//msg1.draw(batch,"Time: 00:00",Camera.camera.position.x,Camera.camera.position.y+lui.getHeight()*9);
		msg1.draw(batch,"Gold 000000000000",Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*9);
		

	//selected object
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y-boxHeight);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y-lui.getHeight()-boxHeight);
	batch.draw(lbu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
	
	batch.draw(rui,Camera.camera.position.x+lui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*8,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*9,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*10,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*11,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*12,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*13,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*14,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	batch.draw(lui,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*7-boxHeight);
	
	
	for(int r = 8; r <= boxLength; r++){
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight);

			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-boxHeight);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()-boxHeight);
			
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y-lui.getHeight()*2-boxHeight);
	}
	
	
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*6-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*5-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*4-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*3-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*2-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y-boxHeight);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y-lui.getHeight()-boxHeight);
	batch.draw(rbu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y-lui.getHeight()*2-boxHeight);

	msg1.draw(batch,"EXP  1   LEV  1",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*7+lui.getHeight()/2);
	msg1.draw(batch,"SPEED "+ "KNOTS "+gameMap.getCurrentPlayer().getSpeed(),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*6+lui.getHeight()/2);
	msg1.draw(batch,"AAAAAAAAAAAAA",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*5+lui.getHeight()/2);
	msg1.draw(batch,"AAAAAAAAAAAAA",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*4+lui.getHeight()/2);
	msg1.draw(batch,"AAAAAAAAAAAAA",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*3+lui.getHeight()/2);
	msg1.draw(batch,"AAAAAAAAAAAAA",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*2+lui.getHeight()/2);
	msg1.draw(batch,"AAAAAAAAAAAAA",Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()+lui.getHeight()/2);
	msg1.draw(batch,"FPS " + FPS,Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()/2);
	msg1.draw(batch,"debug time " + GameMap.time,Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y-lui.getHeight()+lui.getHeight()/2);
	
	
	System.out.println(GameMap.time);
	
	
	//message window 
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
	batch.draw(lsu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
	batch.draw(lbu,Camera.camera.position.x+tui.getWidth()*7,Camera.camera.position.y+lui.getHeight()-boxHeight2);

	batch.draw(rui,Camera.camera.position.x+lui.getWidth()*7,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*8,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*9,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*10,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+lui.getWidth()*11,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*12,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*13,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(tui,Camera.camera.position.x+tui.getWidth()*14,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	batch.draw(lui,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*7-boxHeight2);
	
	
	for(int r = 8; r <= boxLength; r++){
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
			
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
			batch.draw(ui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
		
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
			batch.draw(bui,Camera.camera.position.x+lui.getWidth()*r,Camera.camera.position.y+lui.getHeight()-boxHeight2);
	}
	
	
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*6-boxHeight2);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*5-boxHeight2);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*4-boxHeight2);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*3-boxHeight2);
	batch.draw(rsu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()*2-boxHeight2);
	batch.draw(rbu,Camera.camera.position.x+tui.getWidth()*15,Camera.camera.position.y+lui.getHeight()-boxHeight2);
	//message window
	msg1.draw(batch,gameMap.direction.firstElement(),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*7-boxHeight2+lui.getHeight()/2);
	msg1.draw(batch,gameMap.direction.get(1),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*6-boxHeight2+lui.getHeight()/2);
	msg1.draw(batch,gameMap.direction.get(2),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*5-boxHeight2+lui.getHeight()/2);
	msg1.draw(batch,gameMap.direction.get(3),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*4-boxHeight2+lui.getHeight()/2);
	msg1.draw(batch,gameMap.direction.get(4),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*3-boxHeight2+lui.getHeight()/2);
	msg1.draw(batch,gameMap.direction.get(5),Camera.camera.position.x+(lui.getWidth()*8-lui.getWidth()/4),Camera.camera.position.y+lui.getHeight()*2-boxHeight2+lui.getHeight()/2);
	
	
}


}
