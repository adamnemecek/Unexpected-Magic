package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.gameEngine.components.SpriteComponent;

/**
 * Created by rasmus on 2017-05-17.
 */
public class SpriteFactory {

	public CompositeSprite createSprites(int duration, CompositeSprite sprite){

		switch (duration){

			case(64) :
				sprite.addSprite(new Sprite(new Texture("sprites/note-64th12x4.png")));
				break;

			case(32) :
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4.png")));
				break;

			case(16) :
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4.png")));
				break;

			case(12) : //TODO how to make a 12th?
				break;

			case(8) :
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4.png")));
				break;

			default:
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4.png")));
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4.png")));
				break;
		}


		return sprite;
	}

}
