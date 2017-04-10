package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.screens.MainMenuScreen;

/**
 * The game class.
 */

public class UnexpectedMagic extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Engine engine;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		engine = new PooledEngine(); 
		this.setScreen(new MainMenuScreen(this));
		//TODO
		//init assets?
		
	}

	@Override
	public void render () {
		super.render();
		/*
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		*/
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
	
}
