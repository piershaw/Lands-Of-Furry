
////  Created by Pier Shaw on 4/14/14
////  Copyright � 2014 PixelTactics. All rights reserved.
////  8 bit with no exp card can use just words hydra can get from ext storage card
////  note add more weather 
package com.pt.furry;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
public class TimeOfDay{
		
		private static Texture[] sunsetImages;
		private static Texture curent;
		
		private static int select;
		
		public static void setUpTimeOfDay(){
				sunsetImages = new Texture[50];
				sunsetImages[0] = new Texture(Gdx.files.internal("time00.gif"));
				sunsetImages[1] = new Texture(Gdx.files.internal("time1.gif"));
				sunsetImages[2] = new Texture(Gdx.files.internal("time2.gif"));
				sunsetImages[3] = new Texture(Gdx.files.internal("time3.gif"));
				sunsetImages[4] = new Texture(Gdx.files.internal("time4.gif"));
				sunsetImages[5] = new Texture(Gdx.files.internal("time5.gif"));
				sunsetImages[6] = new Texture(Gdx.files.internal("time6.gif"));
				sunsetImages[7] = new Texture(Gdx.files.internal("time7.gif"));
				sunsetImages[8] = new Texture(Gdx.files.internal("time8.gif"));
				sunsetImages[9] = new Texture(Gdx.files.internal("time9.gif"));
				sunsetImages[10] = new Texture(Gdx.files.internal("time10.gif"));
				sunsetImages[11] = new Texture(Gdx.files.internal("time11.gif"));
				sunsetImages[12] = new Texture(Gdx.files.internal("time12.gif"));
				sunsetImages[13] = new Texture(Gdx.files.internal("time13.gif"));
				sunsetImages[14] = new Texture(Gdx.files.internal("time14.gif"));
				sunsetImages[15] = new Texture(Gdx.files.internal("time15.gif"));
				sunsetImages[16] = new Texture(Gdx.files.internal("time16.gif"));
				sunsetImages[17] = new Texture(Gdx.files.internal("time17.gif"));
				sunsetImages[18] = new Texture(Gdx.files.internal("time18.gif"));
				sunsetImages[19] = new Texture(Gdx.files.internal("time19.gif"));
				sunsetImages[20] = new Texture(Gdx.files.internal("time20.gif"));
				sunsetImages[21] = new Texture(Gdx.files.internal("time21.gif"));
				sunsetImages[22] = new Texture(Gdx.files.internal("time22.gif"));
				sunsetImages[23] = new Texture(Gdx.files.internal("time23.gif"));
				sunsetImages[24] = new Texture(Gdx.files.internal("time24.gif"));
//moon
				sunsetImages[25] = new Texture(Gdx.files.internal("time00.gif"));
				sunsetImages[26] = new Texture(Gdx.files.internal("time01.gif"));
				sunsetImages[27] = new Texture(Gdx.files.internal("time02.gif"));
				sunsetImages[28] = new Texture(Gdx.files.internal("time03.gif"));
				sunsetImages[29] = new Texture(Gdx.files.internal("time04.gif"));
				sunsetImages[30] = new Texture(Gdx.files.internal("time05.gif"));
				sunsetImages[31] = new Texture(Gdx.files.internal("time06.gif"));
				sunsetImages[32] = new Texture(Gdx.files.internal("time07.gif"));
				sunsetImages[33] = new Texture(Gdx.files.internal("time08.gif"));
				sunsetImages[34] = new Texture(Gdx.files.internal("time09.gif"));
				sunsetImages[35] = new Texture(Gdx.files.internal("time010.gif"));
				sunsetImages[36] = new Texture(Gdx.files.internal("time011.gif"));
				sunsetImages[37] = new Texture(Gdx.files.internal("time012.gif"));
				sunsetImages[38] = new Texture(Gdx.files.internal("time013.gif"));
				sunsetImages[39] = new Texture(Gdx.files.internal("time014.gif"));
				sunsetImages[40] = new Texture(Gdx.files.internal("time015.gif"));
				sunsetImages[41] = new Texture(Gdx.files.internal("time016.gif"));
				sunsetImages[42] = new Texture(Gdx.files.internal("time017.gif"));
				sunsetImages[43] = new Texture(Gdx.files.internal("time018.gif"));
				sunsetImages[44] = new Texture(Gdx.files.internal("time019.gif"));
				sunsetImages[45] = new Texture(Gdx.files.internal("time020.gif"));
				sunsetImages[46] = new Texture(Gdx.files.internal("time021.gif"));
				sunsetImages[47] = new Texture(Gdx.files.internal("time022.gif"));
				sunsetImages[48] = new Texture(Gdx.files.internal("time023.gif"));
				sunsetImages[49] = new Texture(Gdx.files.internal("time024.gif"));
				setCurent(sunsetImages[select]);
		}

		public static void setSelect(int sel)
		{
					select = sel;
		}

		public static int getSelect()
		{
				return select;
		}

		public static void setCurent(Texture c)
		{
				curent = c;
		}

		public static Texture getCurent()
		{
				return curent;
		}
		
		public static void update(int t){
			//time += Gdx.graphics.getDeltaTime() /60;
			setSelect(t);
			//if(time > 30){
					//time = 0;
						setCurent(sunsetImages[select]);
					if(getSelect() >= 49){
							select=0;
					//}else{
							//select++;
					}
					
			//}
		}
		
}
