package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

/**
* Component that holds the position.
*/
public class PositionComponent implements Component{
	public int x;
	public int y;
	
	public PositionComponent(int x, int y){
		this.x = x;
		this.y = y;
	}

}
