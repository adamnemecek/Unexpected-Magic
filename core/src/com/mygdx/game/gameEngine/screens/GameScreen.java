package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.gameEngine.systems.MovementSystem;
import com.mygdx.game.gameEngine.systems.RenderSystem;

/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	Engine engine;
	OrthographicCamera inGameCam;
	private boolean running;
	ScalingViewport viewport;
	//EntityManager entityManager;
	SpriteBatch batch;
	Texture backgroundTexture;
	private Round round;

	
	public GameScreen(final UnexpectedMagic game, Song song, ArrayList<Player> players) throws IOException{
		this.game = game;
		engine = game.engine;
		batch = game.batch;
		inGameCam = new OrthographicCamera();
		inGameCam.setToOrtho(false);
		running = false; //TODO get the right arguments song, players
		
		backgroundTexture = new Texture("images/textureCheckedBlue16x16.png");
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, inGameCam);
		viewport.apply(true);
		
		initRound(song, players, engine, batch); //TODO catch exceptions?
	}
	
	public void initRound(Song song, ArrayList<Player> players, Engine engine, SpriteBatch batch) throws IOException{
		round = new Round(song, players, engine, batch);
		System.out.println("Number of voices: "+ round.song.getVoices().length);
		//wait for player input here before running?
		running = true;
	}
	
	public void update (float delta) {
		if(running){
			round.update(delta);
			//System.out.println("TICK: " + round.getTick());
			engine.update(delta);
		}
	}

	@Override
	public void render(float delta){
		game.batch.setProjectionMatrix(inGameCam.combined);
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(backgroundTexture, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_X, 0, 10, 10, 0);
		game.font.draw(game.batch, "YOU'RE IN GAME YAY.", Constants.VIEWPORT_DIM_X/4, Constants.VIEWPORT_DIM_Y/2);
		game.font.draw(game.batch, "look at the cutes\ntexture in everywhere", Constants.VIEWPORT_DIM_X/4, Constants.VIEWPORT_DIM_Y/2 - 20);
		//Print the delta time on screen
		game.font.draw(game.batch, "\nDYNAMIC STUFF Delta: "+ delta, Constants.VIEWPORT_DIM_X/4, Constants.VIEWPORT_DIM_Y/2 - 40);
		//Print the tick on the screen
		game.font.draw(game.batch, "\nDYNAMIC STUFF Tick: "+ round.getTick(), Constants.VIEWPORT_DIM_X/4, Constants.VIEWPORT_DIM_Y/2 - 60);
		
		game.batch.end();
		update(delta);
	}
		
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	
}