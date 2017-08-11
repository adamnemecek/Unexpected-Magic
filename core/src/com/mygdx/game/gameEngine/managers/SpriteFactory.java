package com.mygdx.game.gameEngine.managers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gdxUtils.CompositeSprite;

/**
 * Class creating sprites.
 * @author rastom
 * Revised by soflarb
 * 
 * Uses: CompositeSprite
 * Used By: EntityFactory, PianoRoll
 */

public class SpriteFactory {
	public static final int noteSectionHeight = new TextureRegion(new Texture("sprites/note-64th12x4.png")).getRegionHeight();
	public static final int noteSectionWidth = new TextureRegion(new Texture("sprites/note-64th12x4.png")).getRegionWidth();

	public CompositeSprite createSprites(int duration, int playerNumber, int posX, int posY){
		String color;
		switch (playerNumber){
			case 0: color = "-green";
				break;
			case 1: color = "-magenta";
				break;
			case 2: color = "-turquoise";
				break;
			case 3: color = "-yellow";
				break;
			default: throw new IllegalArgumentException("Invalid player index: " + playerNumber);
		}

		CompositeSprite sprites = new CompositeSprite();
			if(duration == 1) {
				Sprite sprite = new Sprite(new Texture("sprites/note-64th12x4"+ color + ".png"));
				sprite.setPosition(posX,posY);
				sprites.addSprite(sprite);
			} else {
				Sprite sprite = new Sprite(new Texture("sprites/note-top12x4"+ color + ".png"));
				sprite.setPosition(posX,posY + sprite.getRegionHeight()*(duration-1));
				sprites.addSprite(sprite);

				for (int i = 0; i < duration-2; i++){
					sprite = new Sprite(new Texture("sprites/note-mid12x4"+ color + ".png"));
					sprite.setPosition(posX,posY + sprite.getRegionHeight()*(i+1));
					sprites.addSprite(sprite);
				}
				sprite = new Sprite(new Texture("sprites/note-bot12x4"+ color + ".png"));
				sprite.setPosition(posX,posY);
				sprites.addSprite(sprite);
		}
		return sprites;
	}
}
