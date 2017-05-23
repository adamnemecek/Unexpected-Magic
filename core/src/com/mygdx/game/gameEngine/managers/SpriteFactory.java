package com.mygdx.game.gameEngine.managers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gameEngine.gdxUtils.CompositeSprite;

/**
 * Class for adding sprites ot a note entity, knows which color to pick.
 * @author rastom
 */

public class SpriteFactory {
	private static int noteSectionHeight = new TextureRegion(new Texture("sprites/note-64th12x4.png")).getRegionHeight();
	public static int yCoordinate(int tick){
		return tick*noteSectionHeight;
	}
	private String color = "";
	
	public CompositeSprite createSprites(int duration, int playerNumber){

		switch (playerNumber){
			case(0) : this.color = "-green";
				break;

			case(1) : this.color = "-magenta";
				break;

			case(2) : this.color = "-turquoise";
				break;

			case(3) : this.color = "-yellow";
				break;

		}

		CompositeSprite sprite = new CompositeSprite();

			if(duration == 1) {
				sprite.addSprite(new Sprite(new Texture("sprites/note-64th12x4"+ this.color + ".png")));
			}

			else{
				sprite.addSprite(new Sprite(new Texture("sprites/note-top12x4"+ this.color + ".png")));

				for (int i = 0; i < duration-2; i++){
					sprite.addSprite(new Sprite(new Texture("sprites/note-mid12x4"+ this.color + ".png")));

				}
				sprite.addSprite(new Sprite(new Texture("sprites/note-bot12x4"+ this.color + ".png")));

		}
		return sprite;

	}

}
