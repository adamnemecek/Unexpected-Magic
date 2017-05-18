package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.gameEngine.managers.CompositeSprite;
import com.mygdx.game.gameEngine.managers.SpriteFactory;

/**
* Component that represents the sprite.
*/
public class CompositeSpriteComponent implements Component{

	public CompositeSprite sprite;
	
	public CompositeSpriteComponent(CompositeSprite sprite){
		this.sprite = sprite;
	}

}
