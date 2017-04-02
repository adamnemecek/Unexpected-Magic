package com.mygdx.game.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Constants;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.systems.MovementSystem;

/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	PooledEngine engine;
	OrthographicCamera inGameCam;
	Texture testTexture;
	
	public GameScreen(final UnexpectedMagic game){
		this.game = game;
		engine = new PooledEngine(); //TODO
		
		testTexture = new Texture("textures/textureCheckedBlue16x16.png");
		inGameCam = new OrthographicCamera(Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1]);
		inGameCam.setToOrtho(false, Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1]);
		engine.addSystem(new MovementSystem()); //TODO
	}
	
	public void update (float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		inGameCam.update();
		game.batch.setProjectionMatrix(inGameCam.combined);
		//Draw stuff
		game.batch.begin();
		game.font.draw(game.batch, "YOU'RE IN GAME YAY.", Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2);
		game.font.draw(game.batch, "look at the cutes texture in corner", Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2 - 100);
		//Print the delta time on screen
		game.font.draw(game.batch, "Delta: "+ delta, Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2 - 200);
		game.batch.draw(testTexture, 0, 0);
		game.batch.end();
		
		engine.update(delta);

		}

	@Override
	public void render(float delta){
		update(delta);
		
	}
	
}
