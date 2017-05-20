package com.mygdx.game.gameEngine.gdxUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by rasmus on 2017-05-17.
 */
public class CompositeSprite{

	//sprites needs to be added, top, mid, mid... , bot
	private Array<Sprite> componentSprites;

	public CompositeSprite(){
		componentSprites = new Array<Sprite>();
	}

	public void draw(SpriteBatch batch, float posX, float posY){

			for (int i = 0; i < componentSprites.size; i ++){
					Sprite sprite = componentSprites.get(i);
					sprite.setPosition(posX,posY+(sprite.getRegionHeight()*(componentSprites.size-1-i)));
					sprite.draw(batch);
			}

		}

	public void addSprite(Sprite sprite){
		this.componentSprites.add(sprite);
	}

	public int getLength(){
		return (componentSprites.size)*(componentSprites.get(0).getRegionHeight());
	}

}
