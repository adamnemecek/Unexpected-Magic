package com.mygdx.game.gameEngine.managers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.gameEngine.gdxUtils.CompositeSprite;
import com.mygdx.game.model.song.IVoice;


/**
 * Created by rasmus on 2017-05-17.
 */
public class SpriteFactory {

	private String color = "";
	private Array<IVoice> voices = new Array<>();
	private boolean added;


	public CompositeSprite createSprites(int duration, IVoice voice){

		added = false;

		for(int i = 0; i < voice.length(); i ++){
			if(voices.size > i && voices.get(i).equals(voice)){
				added = true;
			}
		}
		if(!added){
			voices.add(voice);
		}

		switch (voices.indexOf(voice,true)){
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
