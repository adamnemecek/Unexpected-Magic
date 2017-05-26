package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.gameEngine.gdxUtils.CompositeSprite;

/**
 * Component that holds the sprite.
 * @author rastom
 * Revised by soflarb
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
