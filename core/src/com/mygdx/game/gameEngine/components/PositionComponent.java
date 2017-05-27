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
	public int x;
	public int y;
	
	public PositionComponent(int x, int y){
		this.x = x;
		this.y = y;
	}

}
