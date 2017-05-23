package com.mygdx.game.gameEngine.gdxUtils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for playing sound.
 * @author rastom
 */

public class CompositeSprite{

	//sprites needs to be added, top, mid, mid... , bot

	private List<Sprite> componentSprites;

	public CompositeSprite(){
		componentSprites = new ArrayList<>();
	}

	public void draw(SpriteBatch batch){

			for (int i = 0; i < componentSprites.size(); i ++){
					Sprite sprite = componentSprites.get(i);
					sprite.draw(batch);
			}

		}

	public void addSprite(Sprite sprite){

		this.componentSprites.add(sprite);
	}

	public int getLength(){
		return (componentSprites.size())*(componentSprites.get(0).getRegionHeight());
	}

}
