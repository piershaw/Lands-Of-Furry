////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Camera implements InputProcessor
{
		public static OrthographicCamera camera;
		public static float zoom,speed,screenScale;
		public static int x,y,foc;
		//private static float ApectRatio;
		public static Vector3 oldPos,newPos;
		private static boolean camSet;
		public static void CameraSetUp(){
				 //0.4f;//	zoom = 0.5f;
				foc = 17;
				//screenScale = setScale(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				//System.out.println("AspectRatio " +screenScale);
				//oldPos = new Vector3(1280,720, 0.4f);
				//newPos = new Vector3(0,0,0);
				
				 	float w = Gdx.graphics.getWidth();
			        float h = Gdx.graphics.getHeight();
			        //camera = new OrthographicCamera(30, 30 * (h / w));
			        camera = new OrthographicCamera();
			        //camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
			        
			       //camera.zoom = 1f;//test only
					camera.setToOrtho(false);
					camera.translate(0,0);
			   // never chage this class works good    
				camera.zoom = MathUtils.clamp(camera.zoom, foc, 100/camera.viewportWidth);
			  camera.update();
				
		}
		
		
	
		public static void updateCameraPosition(){
			camera.position.x = 840-x*16; //fix val some day
			camera.position.y = 700-y*16;
			
			//if(oldPos.x > Gdx.graphics.getWidth()){	
				//float x = (oldPos.x - Gdx.graphics.getWidth());
				//float y = (oldPos.y - Gdx.graphics.getHeight());
			
				//float sum = y/x;
				//float foc = sum / Gdx.graphics.getDensity() - 0.47f;
				//System.out.println("foc "+ foc );
				//camera.zoom = foc;
				//System.out.println("foc "+ camera.zoom);
			
			//hard coded :( way still thinking
			/*if(Gdx.graphics.getWidth() == 640){	
				camera.zoom = 0.64f;
			}else{
				camera.zoom = zoom;
			}*/
			
			//float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
	        //float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
	       // zoom = 20.0f;
				 
	        //camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f); 
	        //camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
		}
		
//		public static void updateZoom(){
//			
//				if(Gdx.input.isKeyPressed(Keys.Z)){
//
//				if(Camera.camera.zoom <= 0.2f) {
//					Camera.zoom = 0.05f;
//				} else {
//					Camera.zoom -= 4f * Gdx.graphics.getDeltaTime();
//				}
//	
//				}else if (Gdx.input.isKeyPressed(Keys.X)){
//					if(Camera.zoom >= 0.9f) {
//						Camera.zoom = 1.0f;
//					}else{
//						Camera.zoom += 4f * Gdx.graphics.getDeltaTime();
//					}
//				}
//				
//				Camera.camera.zoom = Camera.zoom;
//		}
		
		@Override
		public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
		}

		@Override
		public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
		}

		@Override
		public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				
				return true;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				
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
		
		
/*		public static float aspectRatio(int x1,int y1){
			   float gcd=calc(x1,y1);
			   float r1=x1/gcd;
			   float r2=y1/gcd;
			   
				System.out.println("ScreenAspectRatio " + r1+":"+r2 );
				ApectRatio = r1/r2;
				System.out.println("AspectRatio " + ApectRatio);
				return ApectRatio;
			}
			
		public static int calc(int n1,int n2){
			
			  int num1,num2;
			  if(n1 < n2){ 
			      num1=n1;
			      num2=n2;  
			   }
			   else{
			      num1=n2;
			      num2=n1;
			    }
			  int remain=num2%num1;
			  while(remain>0){
			      num2=num1;
			      num1=remain;
			      remain=num2%num1;
			  }
			  return num1;
			}
		
		public static float setScale(int widthPixels, int heightPixels){
			
			float screenWidth = widthPixels-camera.viewportWidth;
			float screenHeight = heightPixels-camera.viewportHeight;
			double size = Math.sqrt(screenWidth + screenHeight);
			
			return (float)size;
		}*/
		
}
