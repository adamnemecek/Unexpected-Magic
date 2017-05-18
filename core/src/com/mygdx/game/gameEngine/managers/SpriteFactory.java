package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.gameEngine.components.CompositeSpriteComponent;

/**
 * Created by rasmus on 2017-05-17.
 */
public class SpriteFactory {

	public CompositeSprite createSprites(int duration){

		CompositeSprite sprite = new CompositeSprite();

			if(duration == 1) {
				sprite.addSprite(new Sprite(new Texture("sprites/note-64th12x4.png")));
				System.out.println("gave it a 64th");
			}

			else{
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4.png")));
				System.out.println("added a top");
				for (int i = 0; i < duration-2; i++){
					sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
					System.out.println("added a mid");
				}
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4.png")));
				System.out.println("added a bot");
		}
		return sprite;

	}

}
