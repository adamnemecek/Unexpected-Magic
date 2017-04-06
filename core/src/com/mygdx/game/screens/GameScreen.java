package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.managers.EntityManager;
import com.mygdx.game.systems.MovementSystem;
import com.mygdx.game.systems.RenderSystem;

/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	Engine engine;
	OrthographicCamera inGameCam;
	ScalingViewport viewport;
	EntityManager entityManager;
	SpriteBatch batch;
	
	public GameScreen(final UnexpectedMagic game){
		this.game = game;
		engine = game.engine;
		batch = game.batch;
		inGameCam = new OrthographicCamera();
		inGameCam.setToOrtho(false);
		
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1], inGameCam);
		viewport.apply(true);
		
		engine.addSystem(new MovementSystem()); //TODO
		engine.addSystem(new RenderSystem(batch));
		System.out.println("GameScreen. Engine's systems: " + engine.getSystems().toString());
		
		entityManager = new EntityManager(engine, batch);
		
	}
	
	public void update (float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		//Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(inGameCam.combined);
		engine.update(delta);
		/*
		//Draw stuff
		game.batch.begin();
		game.font.draw(game.batch, "YOU'RE IN GAME YAY.", Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2);
		game.font.draw(game.batch, "look at the cutes texture in corner", Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2 - 100);
		//Print the delta time on screen
		game.font.draw(game.batch, "Delta: "+ delta, Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2 - 200);
		game.batch.draw(testTexture, 0, 0);
		game.batch.end();
		*/
		//System.out.println("GameScreen. engine.update() called. delta = " + delta);

		}

	@Override
	public void render(float delta){
		update(delta);
		
	}
	@Override
	public void resize(int width, int height){
		System.out.println("GameScreen. viewport.update()");
		viewport.update(width, height);
	}
	
}
