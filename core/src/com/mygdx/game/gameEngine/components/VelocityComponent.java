package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

/**
* Component that holds the velocity.
*/
public class VelocityComponent implements Component{
	private int x;
	private int y;
	public VelocityComponent(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
}