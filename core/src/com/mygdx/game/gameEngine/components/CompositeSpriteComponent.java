package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.gameEngine.gdxUtils.CompositeSprite;

/**
* Component that represents the sprite.
*/
public class CompositeSpriteComponent implements Component{

	public CompositeSprite sprite;
	
	public CompositeSpriteComponent(CompositeSprite sprite){
		this.sprite = sprite;
	}

}
