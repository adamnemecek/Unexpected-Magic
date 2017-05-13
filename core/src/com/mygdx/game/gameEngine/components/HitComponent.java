package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;

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
