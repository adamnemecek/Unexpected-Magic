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
import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.Song;


/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	Engine engine;
	OrthographicCamera inGameCam;
	private boolean running;
	ScalingViewport viewport;
	private final Stage stage;
	SpriteBatch batch;
	Texture backgroundTexture;
	Texture pianoRollTexture;
	//EntityManager entityManager;
	private RoundManager roundManager;
	private Hud hud;
	private PianoRoll pianoRoll;
	private final SoundManager soundmanager;
	
	public GameScreen(final UnexpectedMagic game, Song song, ArrayList<Player> players) throws IOException{
		this.game = game;
		engine = game.engine;
		batch = game.batch;
		inGameCam = new OrthographicCamera();
		inGameCam.setToOrtho(false);
		running = false; //TODO get the right arguments song, players
		backgroundTexture = new Texture("images/textureCheckedBlue16x16.png");
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		pianoRollTexture = new Texture("images/textureCheckedPurple16x16.png");
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, inGameCam);
		viewport.apply(true);
		hud = new Hud(batch);
		pianoRoll = new PianoRoll(engine, batch);
		initRound(song, players, engine, batch); //TODO catch exceptions?
	    stage = new Stage(viewport, game.batch);
	    Gdx.input.setInputProcessor(stage);
		soundmanager = new SoundManager();	
	    soundmanager.setInstrument(40);
	    
	    addStageListener();
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
		batch.setProjectionMatrix(inGameCam.combined);
		viewport.apply(true);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_X, 0, 10, 10, 0);
		
		batch.end();
		
		//Gdx.gl.glViewport((int)Constants.PIANOROLL_POS_X,(int)Constants.PIANOROLL_POS_Y,(int)Constants.PIANOROLL_DIM_X,(int)Constants.PIANOROLL_DIM_Y);
		batch.setProjectionMatrix(pianoRoll.camera.combined);
		pianoRoll.viewport.apply(true);
		pianoRoll.draw(delta);
		
		batch.setProjectionMatrix(inGameCam.combined);
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
	
	private void addStageListener(){
		stage.addListener(new InputListener(){
        	@Override
        	public boolean keyDown(InputEvent event, int keycode){
        		
        		switch(keycode){
        		
        		case (Input.Keys.TAB) : soundmanager.setInstrument(new Random().nextInt(100));
        		break;
        		
        		case (Input.Keys.A): 
        			soundmanager.noteOn(57);
        			pianoRoll.activateLane(0);
        		break;
        		
        		case (Input.Keys.S):
        			soundmanager.noteOn(58);
        			pianoRoll.activateLane(1);

        		break;
        		
        		case (Input.Keys.D): 
        			soundmanager.noteOn(59);
        			pianoRoll.activateLane(2);

        		break;
        		
        		case (Input.Keys.F):
        			soundmanager.noteOn(60);
        			pianoRoll.activateLane(3);

        		break;
        		
        		case (Input.Keys.G): 
        			soundmanager.noteOn(61);
    				pianoRoll.activateLane(4);
    			
        		break;
        		
        		case (Input.Keys.H): 
        			soundmanager.noteOn(62);
    				pianoRoll.activateLane(5);
        		
        		break;
        		
        		case (Input.Keys.J): 
        			soundmanager.noteOn(63);
        			pianoRoll.activateLane(6);

        		break;
        		
        		case (Input.Keys.K): 
        			soundmanager.noteOn(64);
        			pianoRoll.activateLane(7);
        		
        		break;
        		
        		case (Input.Keys.L): 
        			soundmanager.noteOn(65);
        			pianoRoll.activateLane(8);
        		
        		break;
        		        		
        		default:
        		break;
        		}
        		
        		return true;
        	
        	
        	}
        	@Override
        	public boolean keyUp(InputEvent event, int keycode){
        		switch(keycode){
        		
        		case (Input.Keys.A): 
        			soundmanager.noteOff(57);
        			pianoRoll.deactivateLane(0);
        		break;
        		
        		case (Input.Keys.S): 
        			soundmanager.noteOff(58);
        			pianoRoll.deactivateLane(1);
        		break;
        		
        		case (Input.Keys.D): 
        			soundmanager.noteOff(59);
        			pianoRoll.deactivateLane(2);
        		break;
        		
        		case (Input.Keys.F): 
        			soundmanager.noteOff(60);
        			pianoRoll.deactivateLane(3);
        		break;
        		
        		case (Input.Keys.G):
        			soundmanager.noteOff(61);
        			pianoRoll.deactivateLane(4);
        		break;
        		
        		case (Input.Keys.H): 
        			soundmanager.noteOff(62);
        			pianoRoll.deactivateLane(5);
        		break;
        		
        		case (Input.Keys.J): 
        			soundmanager.noteOff(63);
        			pianoRoll.deactivateLane(6);
        		break;
        		
        		case (Input.Keys.K): 
        			soundmanager.noteOff(64);
        			pianoRoll.deactivateLane(7);
        		break;
        		
        		case (Input.Keys.L): 
        			soundmanager.noteOff(65);
        			pianoRoll.deactivateLane(8);
        		
        		break;
        		
        		default:
        		break;
        		}
        		
        		 		return true;
        	}
        	
	    	});
	}
	
}
