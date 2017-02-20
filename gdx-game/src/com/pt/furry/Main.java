////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

import java.util.Scanner;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
// by pier shaw copyright 2014
// use eclipse to build 
public class Main
{
	public static void main(String[] args){
		
		//Scanner input = new Scanner(System.in);
		//Upgrades.addToPlayer(input.toString());
		//System.out.println("v.v"+Upgrades.upgradeobjects.size());

		//System.out.println(GlobalObject.getGamestate());
		
		//run MyGdxGame
	
		System.out.println("by pier shaw ");
		
		//
		// change these lines of code to build a platform add images in folder or assets
		//
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
		//System.out.println("what is your players name?");
		
		//Animal cat = new Animal();
		//cat.setPosition(4,5);
		//new MyGdxGame();
		
		//Scanner input = new Scanner(System.in);
	
		
		
	}
}
