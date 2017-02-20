////  Created by Pier Shaw on 4/14/14
////  Copyright © 2014 PixelTactics. All rights reserved.
////
package com.pt.furry;

// i made this to test on console 
public class Display
{
	
	public char[][] map;
	public final int rowlength = 64;
	public final int collength = 48;
	
	private int y;
	
	public Display(){
		map = new char[collength][rowlength];
		y=0;
		
		//clear
		for(int i = 0; i< rowlength; i++){
			for(int g = 0; g < collength; g++){
				map[g][i] = ' ';
			}
		}	
	}
	
	
	public void drawScreen(){
		for(int co=0; co < collength; co++){
			y = co;
			drawRow();
		}
	}
	
	
	private void drawRow(){
		
		for(int x = 0; x < rowlength; x++){	
		
			if(map[y][x] == ' '){
				System.out.print("-");
			}else if(map[y][x] == '!'){
				System.out.print("");
			}else{	
				System.out.print(map[y][x]);
			}
			
		}
		System.out.println();
		return;
	}


	public void clear() {
		
		for(int i = 0; i< rowlength; i++){
			for(int g = 0; g < collength; g++){
				map[g][i] = '!';
			}
		}
	
		//System.out.flush();
		
	}
}
