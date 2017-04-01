package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Constants;
import com.mygdx.game.UnexpectedMagic;

/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	final UnexpectedMagic game;
	OrthographicCamera inGameCam;
	
	public GameScreen(final UnexpectedMagic game){
		this.game = game;
		inGameCam = new OrthographicCamera(Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1]);
	}

}
