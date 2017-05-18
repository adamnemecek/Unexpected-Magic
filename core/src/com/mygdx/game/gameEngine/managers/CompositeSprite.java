package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

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

				if (i == componentSprites.size-1){
					sprite.setPosition(posX,posY);
					sprite.draw(batch);
				}
				else {
					sprite.setPosition(posX,posY+(sprite.getRegionHeight()*(componentSprites.size-1-i)));
					sprite.draw(batch);
			}

		}
	}

	public void addSprite(Sprite sprite){
		this.componentSprites.add(sprite);
	}

}
