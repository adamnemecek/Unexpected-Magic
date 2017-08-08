package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

/**
* Component that holds the position.
* @author soflarb
* Revised by rastom, car0b1nius
* 
* Uses: none
* Used by: EntityFactoy, PianoRoll
*/
public class PositionComponent implements Component{
	private int x;
	private int y;
	
	public int getX(){
		return this.x;
	}
	public void setX(int newX){
		this.x = newX;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int newY){
		this.y = newY;
	}
	
	public PositionComponent(int x, int y){
		this.x = x;
		this.y = y;
	}

}
