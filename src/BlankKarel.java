/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class BlankKarel extends SuperKarel {
	public void run() {
		int [] dimensions = getDimensions();
		if(dimensions[0] == 1 || dimensions[1] == 1)
			levelOne(Math.max(dimensions[0] , dimensions[1]),Math.min(dimensions[0] , dimensions[1]));
	}
	public int [] getDimensions(){
		int x = 1,y = 1;
		int count = 0;
		while (true){
			if (frontIsBlocked()){
				turnLeft();
				count++;
			}
			else{
				if (count==0)
					x++;
				if (count==1)
					y++;
				if (count >= 2)
					break;
				move();
			}
		}
		return new int[] {x,y};
	}
	public void levelOne (int max,int min){
		int lev = 2;
		while (max > 2){
			if(max%2 != 0){
				if(max == 5){
					move();
					max--;
					continue;
				}
				if(max == 3){
					move();
					putBeeper();
					max--;
					continue;
				}
				if((max/2)%2 == 0){
				putBeeper();
				move();
					max--;
				continue;
				}
				else {
					int numOfMoves = (max - 3) / 4;
					for (int i = 0; i < 3; i++) {
						if(i == 1)
							numOfMoves++;
						for (int j = 0; j < numOfMoves && frontIsClear(); j++) {
							move();
						}
						putBeeper();
					}
					return;
				}
			}
			if(max == 6){
				putBeeper();
				move();
				max--;
				continue;
			}
			if(max == 4){
				putBeeper();
				move();
				move();
				putBeeper();
				return;
			}
			if(max%4 != 0){
				putBeeper();
				move();
				putBeeper();
				move();
				max-=2;
			}
			int numOfMoves = (max - 4) / 4;
			for(int i =0; i<4; i++){
				putBeeper();
				if(i == 3)
					return;
				for(int j=0; j<numOfMoves+1 && frontIsClear();j++){
					move();
				}
			}
		}
	}
}

