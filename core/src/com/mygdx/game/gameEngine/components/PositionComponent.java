package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

/**
* Component that holds the position.
*/
public class PositionComponent implements Component{
	public float x;
	public float y;
	
	public PositionComponent(float x, float y){
		this.x = x;
		this.y = y;
	}
}
