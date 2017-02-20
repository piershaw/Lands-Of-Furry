////  Created by Pier Shaw on 4/14/14
////  Copyright © 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
public class SplashScreen implements Screen{
		
		private SpriteBatch batch;
		private Texture splash;
		private Game game;
		
		public SplashScreen(Game g){
				game = g;
		}
		
		//Show runs 1st
		@Override
		public void show(){
				batch = new SpriteBatch();
				splash = new Texture(Gdx.files.internal("splash.gif"));
				
		}
		//Render runs next
		@Override
		public void render(float delta){
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				batch.begin();
				batch.draw(splash,Gdx.graphics.getWidth() / 2 - splash.getWidth()/2,Gdx.graphics.getHeight()/ 2 - splash.getHeight() /2 );
				batch.end();
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resize(int arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}
}
