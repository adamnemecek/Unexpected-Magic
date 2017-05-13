package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

/**
 * 
 * @author Arvid
 * Component that tracks of whether the entity has been hit
 *
 */

public class HitComponent implements Component{
	private boolean isHit;
	public HitComponent(){
		isHit = false;
	}
	
	public void hit(){
		isHit = true;
	}
	
	public boolean isHit(){
		return isHit;
	}

}
