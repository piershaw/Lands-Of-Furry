////  Created by Pier Shaw on 4/14/14
////  Copyright © 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

///game object thats a Animal
public class Animal extends Friends implements Moveable{
	//private AnimalTypes a; // keep 
	
	
	
	public Animal(){
		//
	}
	
	@Override
	public void setPosition(int x,int y){
		this.x = x;
		this.y = y;
		//keep but dont use here
		/*if(t == AnimalTypes.bear){
			render(x,y,'B');
		}else if(t == AnimalTypes.bunny){
			render(x,y,'U');
		}else if(t == AnimalTypes.cat){
			render(x,y,'C');
		}else if(t == AnimalTypes.dog){
			render(x,y,'B');
		}else if(t == AnimalTypes.pig){
			render(x,y,'B');
		}else if(t == AnimalTypes.eleafent){
			render(x,y,'E');
		}else if(t == AnimalTypes.mouse){
			render(x,y,'M');
		}*/
		
	}
}
