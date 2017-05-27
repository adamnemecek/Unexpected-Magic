package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.gdxUtils.CompositeSprite;

/**
 * Component that holds the sprite.
 * @author rastom
 * Revised by soflarb
 * 
 * Uses: CompositeSprite
 * Used By: EntityFactory, PianoRoll
 */

public class CompositeSpriteComponent implements Component{

	private CompositeSprite sprite;
	
	public CompositeSpriteComponent(CompositeSprite sprite){
		this.sprite = sprite;
	}

	public CompositeSprite getCompositeSprite(){
		return this.sprite;
	}

}
