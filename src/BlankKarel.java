/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class BlankKarel extends SuperKarel {
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
	public void levelTow (){
		turnLeft();
		move();
		putBeeper();
		turnRight();
		turnRight();
		move();
		turnLeft();
	}
	public void levelOne (int max,int min){
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
					if(min == 2)
						levelTow();
					max--;
					continue;
				}
				if((max/2)%2 == 0){
				putBeeper();
					if(min == 2)
						levelTow();
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
						if(min == 2)
							levelTow();
					}
					return;
				}
			}
			if(max == 6){
				putBeeper();
				if(min == 2)
					levelTow();
				move();
				max--;
				continue;
			}
			if(max == 4){
				putBeeper();
				if(min == 2)
					levelTow();
				move();
				move();
				putBeeper();
				if(min == 2)
					levelTow();
				return;
			}
			if(max%4 != 0){
				putBeeper();
				if(min == 2)
					levelTow();
				move();
				putBeeper();
				if(min == 2)
					levelTow();
				move();
				max-=2;
			}
			int numOfMoves = (max - 4) / 4;
			for(int i =0; i<4; i++){
				putBeeper();
				if(min == 2)
					levelTow();
				if(i == 3)
					return;
				for(int j=0; j<numOfMoves+1 && frontIsClear();j++){
					move();
				}
			}
		}
	}
	public void putLineOfBeeper(int n){
		for (int i =0; i<n ; i++){
			if(noBeepersPresent())
				putBeeper();
			if(frontIsClear())
				move();
		}
	}
	public void highLevel(int x,int y){

		for(int i=0; i<x/2; i++) {
			if(i==0 && x%2==0)
				i++;
			move();
		}
		turnLeft();
		putLineOfBeeper(y);
		turnRight();
		if(x%2==0){
			move();
			turnRight();
			putLineOfBeeper(y);
			turnLeft();
		}
		while (frontIsClear())
			move();
		if(x%2 == 0)
			turnLeft();
		else
			turnRight();
		for (int i=0; i<y/2;i++) {
			if(i==0 && y%2==0)
				i++;
			move();
		}
		if(x%2 == 0)
			turnLeft();
		else
			turnRight();
		putLineOfBeeper(x);
		if(y%2==0){
			if(x%2 == 0)
				turnRight();
			else
				turnLeft();
			move();
			if(x%2 == 0)
				turnRight();
			else
				turnLeft();
			putLineOfBeeper(x);

		}
	}
	public void run() {
		int [] dimensions = getDimensions();
		if(dimensions[0] <= 2 || dimensions[1] <= 2 )
			levelOne(Math.max(dimensions[0] , dimensions[1]),Math.min(dimensions[0] , dimensions[1]));
		else
			highLevel(dimensions[0],dimensions[1]);
	}
}

