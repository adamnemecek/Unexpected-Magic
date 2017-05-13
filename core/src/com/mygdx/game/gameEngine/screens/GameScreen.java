package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.gameEngine.input.KeyboardInputManager;
import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.gameEngine.systems.ScoreSystem;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.Score;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.ISong;


/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends AbstractScreen{
	
	
	Engine engine;

	private boolean running;
	SpriteBatch batch;
	Texture backgroundTexture;
	//EntityManager entityManager;
	private RoundManager roundManager;
	private Hud hud;
	private PianoRoll pianoRoll;
	private final SoundManager soundmanager;
	private final KeyboardInputManager keyboardInputManager;
	
	public GameScreen(final UnexpectedMagic game, ISong song, ArrayList<Player> players) throws IOException{
		super(game);
		engine = game.engine;
		batch = game.batch;
		/*camera = new OrthographicCamera();
		camera.setToOrtho(false);*/
		running = false; //TODO get the right arguments song, players
		backgroundTexture = new Texture("images/lanes/Purple.png");
		
		Score score = new Score(); //TODO Should be somewhere else, probably RoundManager

        hud = new Hud(batch, score);
		pianoRoll = new PianoRoll(engine, batch);
		initRound(song, players, engine, batch); //TODO catch exceptions?

		soundmanager = new SoundManager();
		soundmanager.setInstrument(40);

		ScoreSystem scoreSystem = new ScoreSystem(score,pianoRoll.getNoteLanes(), soundmanager); //TODO SHOULD BE SOMEWHERE ELSE
        engine.addSystem(scoreSystem);

        this.keyboardInputManager = new KeyboardInputManager();
	    Gdx.input.setInputProcessor(keyboardInputManager);

	}
	
	public void initRound(ISong song, ArrayList<Player> players, Engine engine, SpriteBatch batch) throws IOException{
		roundManager = new RoundManager(new Round(song, players), new EntityManager(engine, batch, song), new Ticker(song));
		
		//System.out.println("Number of voices: "+ round.song.getVoices().length);
		//wait for player input here before running?
		running = true;
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
		batch.setProjectionMatrix(pianoRoll.camera.combined);
		pianoRoll.viewport.apply(true);
		pianoRoll.draw(delta);
		
		batch.setProjectionMatrix(camera.combined);
		viewport.apply(true);
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
