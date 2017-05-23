package com.mygdx.game.gameEngine.screens;

import java.util.List;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.input.InputAction;
import com.mygdx.game.gameEngine.input.KeyboardControllerAdapter;
import com.mygdx.game.gameEngine.input.KeyboardInputManager;
import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.gameEngine.sound.SongPlayback;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.Score;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;


/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends AbstractScreen{
	private boolean running;
	//Texture backgroundTexture;
	private RoundManager roundManager;
	private final NoteLanes noteLanes;
	private Hud hud;
	private PianoRoll pianoRoll;
	private Ticker ticker;
	private KeyboardInputManager keyboardInputManager;
	private Engine engine;

	Texture background = new Texture("images/UnexpectedMagicBackground5.png");
	
	public GameScreen(final SpriteBatch batch, ISong song, List<Player> players, List<IVoice> nonPlayerVoices) {
		super(batch);
		this.engine = new PooledEngine();
		this.noteLanes = new NoteLanes();
		this.ticker = new Ticker(song);
		running = false;
		Score score = new Score(); //TODO Should be somewhere else, probably RoundManager
        hud = new Hud(batch, score, noteLanes, song.getTitle(), Integer.toString(song.getBpm()), players);
		pianoRoll = new PianoRoll(engine, batch, players, song); //TODO should pianoroll create the "pianoroll"?
		initRound(song, players, engine, batch, nonPlayerVoices); //TODO catch exceptions?
        initInput();
	}
	
	public void initRound(ISong song, List<Player> players, Engine engine, SpriteBatch batch, List<IVoice> nonPlayerVoices) {
		roundManager = new RoundManager(new Round(song, players), ticker, nonPlayerVoices);
		//wait for player input here before running?
		running = true;
	}
	
	private void initInput(){
		
		final InputAction ia = new InputAction(hud, roundManager);
		final KeyboardControllerAdapter  kca= new KeyboardControllerAdapter(ia);
		
		this.keyboardInputManager = new KeyboardInputManager(kca);
//	    Gdx.input.setInputProcessor(keyboardInputManager);
		
	}
	
	public void update (float delta) {
		if(running){
			roundManager.update(delta);
			engine.update(delta);
            hud.setScoreLabel();
		}
	}

	@Override
	public void render(float delta){

		if(roundManager.gameOver()){
			roundManager.endRound();
			this.dispose();
			changeToScreen(new ScoreScreen(batch));
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
		batch.setProjectionMatrix(pianoRoll.camera.combined);
		pianoRoll.draw();//TODO how to move pianoroll camera, should move according to tick
		pianoRoll.placeCamera((float)ticker.tickWithDecimals()*Constants.NOTESPRITE_HEIGHT);
		//update systems
		update(delta);
		batch.end();

		//Draw HUD
		batch.setProjectionMatrix(hud.stage.getCamera().combined);
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
