package com.mygdx.game.gameEngine.screens;

import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.gameEngine.input.InputAction;
import com.mygdx.game.gameEngine.input.KeyboardControllerAdapter;
import com.mygdx.game.gameEngine.input.KeyboardInputManager;
import com.mygdx.game.gameEngine.managers.Round;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.RoundData;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utils.Constants;


/**
* Screen that contains the game (in-game).
* @author soflarb
* Revised by car0b1nius, rastom and rarvid
* 
* Uses: AbstractScreen, ScoreScreen, InputAction, KeyboardControllerAdapter, KeyboardInputManager, 
* IPlayer, Round, Ticker, ISong, IVoice, ObserverHandler, RoundManager, Hud, PianoRoll
* 
* Used By: None
*/
public class GameScreen extends AbstractScreen {
	private boolean running;
	//Texture backgroundTexture;
	private Round roundManager;
	private Hud hud;
	private PianoRoll pianoRoll;
	private KeyboardInputManager keyboardInputManager;
	private Engine engine;
	private Ticker ticker;
	private final List<IPlayer> players;
	private final ISong song;

	private Texture background = new Texture("images/UnexpectedMagicBackground5.png");
	
	public GameScreen(final SpriteBatch batch, ISong song, List<IPlayer> players, List<IVoice> nonPlayerVoices) {
		super(batch);
		this.engine = new PooledEngine();
		this.ticker = new Ticker(song);
		this.players = players;
		this.song = song;
		running = false;
		hud = new Hud(batch, song.getTitle(), Integer.toString(song.getBpm()), players);
		pianoRoll = new PianoRoll(engine, batch, players, song, ticker);
		initRound(song, players, nonPlayerVoices);
		initInput();
	}
	

	public void initRound(ISong song, List<IPlayer> players, List<IVoice> nonPlayerVoices) {
		roundManager = new Round(new RoundData(song, players), nonPlayerVoices, ticker);
		//wait for player input here before running?
		running = true;
	}
	
	private void initInput(){
		final InputAction ia = new InputAction(hud, roundManager);
		final KeyboardControllerAdapter kca= new KeyboardControllerAdapter(ia);
		
		this.keyboardInputManager = new KeyboardInputManager(kca);
		//Gdx.input.setInputProcessor(keyboardInputManager);
	}

	
	public void update (float delta) {
		if(running){
			ticker.updateTick(delta);
			engine.update(delta);
		}
	}

	@Override
	public void render(float delta){
		if(!ticker.isTicking()){
			this.dispose();
			ObserverHandler.clearTickListeners();
			changeToScreen(new ScoreScreen(batch, players, song));
		}
		//Wipe screen (don't use super because super clears with blue)
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Draw things on the gamescreen
		batch.setProjectionMatrix(camera.combined);
		viewport.apply(true);
		batch.begin();
		//batch.draw(backgroundTexture, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_X, 0, 10, 10, 0);
		batch.draw(background, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		batch.end();

		//draw pianoroll
		batch.begin();
		batch.setProjectionMatrix(pianoRoll.getCamera().combined);
		pianoRoll.draw();
		pianoRoll.placeCamera();
		//update systems
		update(delta);
		batch.end();

		//Draw HUD
		batch.setProjectionMatrix(hud.getStage().getCamera().combined);
		hud.draw();
	}
	
	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(keyboardInputManager);
	}
		
	@Override
	public void resize(int width, int height){
		super.resize(width, height);
		pianoRoll.resize(viewport.getScreenWidth(), viewport.getScreenHeight(), viewport.getScreenX(), viewport.getScreenY());
		hud.resize(width, height);
	}
}
