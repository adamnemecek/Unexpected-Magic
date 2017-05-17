package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by rasmus on 2017-05-17.
 */
public class CompositeSprite{

	private Array<Sprite> componentSprites;

	public CompositeSprite(){
		componentSprites = new Array<Sprite>();
	}
	//TODO unsure if this is usable for non-moving components
	public void draw(SpriteBatch batch, float posX, float posY){
		for(Sprite sprite : componentSprites){
			sprite.setPosition(posX,posY);
			sprite.draw(batch);
		}
	}

	public void addSprite(Sprite sprite, int x, int y, int height, int width){
		Sprite s = sprite;
		s.setBounds(x,y,width,height);
		this.componentSprites.add(s);
	}
}
