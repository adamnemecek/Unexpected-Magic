package com.mygdx.game.gameEngine.screens;

import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.input.InputAction;
import com.mygdx.game.gameEngine.input.KeyboardControllerAdapter;
import com.mygdx.game.gameEngine.input.KeyboardInputManager;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.gameEngine.systems.ScoreSystem;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.Score;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.utilities.file.Constants;


/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends AbstractScreen{
	
	
	Engine engine;

	private boolean running;
	SpriteBatch batch;
	Texture backgroundTexture;
	private RoundManager roundManager;
	private final NoteLanes noteLanes;
	private Hud hud;
	private PianoRoll pianoRoll;
	private final SoundManager soundmanager;
	private KeyboardInputManager keyboardInputManager;
	
	public GameScreen(final Engine engine, final SpriteBatch batch, ISong song, List<Player> players) {
		super(engine, batch);
		this.engine = engine;
		this.batch = batch;
		noteLanes = new NoteLanes();
		running = false;
		backgroundTexture = new Texture("images/lanes/Purple.png");
		
		Score score = new Score(); //TODO Should be somewhere else, probably RoundManager

        hud = new Hud(batch, score);
		pianoRoll = new PianoRoll(engine, batch, noteLanes);
		initRound(song, players, engine, batch); //TODO catch exceptions?

		soundmanager = new SoundManager();
		soundmanager.setInstrument(40); //
		soundmanager.setSongTimeSignaure(song.getTime()[1],song.getBpm()); //TODO, soundmanger should perhaps only be in one class

		ScoreSystem scoreSystem = new ScoreSystem(score,pianoRoll.getNoteLanes(), soundmanager); //TODO SHOULD BE SOMEWHERE ELSE
        engine.addSystem(scoreSystem);
        
        initInput();

	}
	
	public void initRound(ISong song, List<Player> players, Engine engine, SpriteBatch batch) {
		roundManager = new RoundManager(new Round(song, players), new EntityManager(engine, batch, song), new Ticker(song), noteLanes);
		
		//System.out.println("Number of voices: "+ round.song.getVoices().length);
		//wait for player input here before running?
		running = true;
	}
	
	private void initInput(){
		
		final InputAction ia = new InputAction(roundManager);
		final KeyboardControllerAdapter  kca= new KeyboardControllerAdapter(ia);
		
		this.keyboardInputManager = new KeyboardInputManager(kca);
	    Gdx.input.setInputProcessor(keyboardInputManager);
		
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
			notifyScreenChange(new ScoreScreen(engine, batch));
		}
		//Wipe screen (don't use super because super clears with blue)
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Set the gl viewport bounds for drawing.
		//Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); //TODO
		
		//Draw things on the gamescreen
		batch.setProjectionMatrix(camera.combined);
		viewport.apply(true);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_X, 0, 10, 10, 0);
		batch.end();
		
		//Gdx.gl.glViewport((int)Constants.PIANOROLL_POS_X,(int)Constants.PIANOROLL_POS_Y,(int)Constants.PIANOROLL_DIM_X,(int)Constants.PIANOROLL_DIM_Y);
		//draw pianoroll
		batch.setProjectionMatrix(pianoRoll.camera.combined);
		pianoRoll.viewport.apply(true);
		pianoRoll.draw(delta);
		//update systems
		//batch.setProjectionMatrix(camera.combined);
		//viewport.apply(true);
		update(delta);
		//Draw HUD
		batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
	}
		
	@Override
	public void resize(int width, int height){
		super.resize(width, height);
		pianoRoll.resize(viewport.getScreenWidth(), viewport.getScreenHeight(), viewport.getScreenX(), viewport.getScreenY());
		
	}
}
