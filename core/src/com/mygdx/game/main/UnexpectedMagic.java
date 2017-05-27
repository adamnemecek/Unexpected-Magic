package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.screens.AbstractScreen;
import com.mygdx.game.gameEngine.screens.ScreenListener;
import com.mygdx.game.gameEngine.screens.TitleScreen;

/**
 * The game class.
 * @author soflarb
 * Revised by rarvid
 * 
 * Uses: AbstractScreen, ScreenListener, TitleScreen
 * Used by: none
 */

public class UnexpectedMagic extends Game implements ScreenListener{
	private SpriteBatch batch;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		AbstractScreen.addListener(this);
		this.setScreen(new TitleScreen(this.batch));
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		this.getScreen().resize(width, height);
	}

	@Override
	public void screenChanged(AbstractScreen screen) {
		this.setScreen(screen);
		
	}
	
}
