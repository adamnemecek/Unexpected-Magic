package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.gameEngine.managers.KeyboardInputManager;
import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.gameEngine.systems.ScoreSystem;
import com.mygdx.game.model.*;
import com.mygdx.game.model.song.Song;


/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends AbstractScreen{
	
	
	Engine engine;

	private boolean running;
	SpriteBatch batch;
	Texture backgroundTexture;
	Texture pianoRollTexture;
	//EntityManager entityManager;
	private RoundManager roundManager;
	private Hud hud;
	private PianoRoll pianoRoll;
	private final SoundManager soundmanager;
	private final KeyboardInputManager keyboardInputManager;
	
	public GameScreen(final UnexpectedMagic game, Song song, ArrayList<Player> players) throws IOException{
		super(game);
		engine = game.engine;
		batch = game.batch;
		/*camera = new OrthographicCamera();
		camera.setToOrtho(false);*/
		running = false; //TODO get the right arguments song, players
		backgroundTexture = new Texture("images/textureCheckedBlue16x16.png");
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		pianoRollTexture = new Texture("images/textureCheckedPurple16x16.png");



        hud = new Hud(batch);
		pianoRoll = new PianoRoll(engine, batch);
		initRound(song, players, engine, batch); //TODO catch exceptions?

        ScoreSystem scoreSystem = new ScoreSystem(new Score(),pianoRoll); //TODO SHOULD BE SOMEWHERE ELSE
        engine.addSystem(scoreSystem);

        soundmanager = new SoundManager();
	    soundmanager.setInstrument(40);

        this.keyboardInputManager = new KeyboardInputManager(soundmanager, pianoRoll);
	    Gdx.input.setInputProcessor(keyboardInputManager);

	}
	
	public void initRound(Song song, ArrayList<Player> players, Engine engine, SpriteBatch batch) throws IOException{
		roundManager = new RoundManager(new Round(song, players), new EntityManager(engine, batch, song), new Ticker(song));
		
		//System.out.println("Number of voices: "+ round.song.getVoices().length);
		//wait for player input here before running?
		running = true;
	}
	
	public void update (float delta) {
		if(running){
			roundManager.update(delta);
			//System.out.println("TICK: " + round.getTick());
			engine.update(delta);
			
		}
	}

	@Override
	public void render(float delta){
		//Wipe screen
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
		viewport.update(width, height);
		pianoRoll.resize(viewport.getScreenWidth(), viewport.getScreenHeight(), viewport.getScreenX(), viewport.getScreenY());
		
	}
}
