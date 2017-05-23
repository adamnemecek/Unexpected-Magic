package com.mygdx.game.gameEngine.gdxUtils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Class for playing sound.
 * @author rastom
 */

public class CompositeSprite{

	//sprites needs to be added, top, mid, mid... , bot
	private Array<Sprite> componentSprites;

	public CompositeSprite(){
		componentSprites = new Array<Sprite>();
	}

	public void draw(SpriteBatch batch){

			for (int i = 0; i < componentSprites.size; i ++){
					Sprite sprite = componentSprites.get(i);
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
