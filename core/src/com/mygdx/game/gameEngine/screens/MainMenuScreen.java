package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.services.file.SongList;
import com.mygdx.game.UnexpectedMagic;

/**
* Screen that contains the main menu.
*/
public class MainMenuScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	OrthographicCamera guiCam;
	Engine engine;
	private SongList songList;
	private ArrayList<Player> players;
	

	public MainMenuScreen(final UnexpectedMagic game){
		this.game = game;
		engine = game.engine;
		guiCam = new OrthographicCamera(Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		guiCam.setToOrtho(false, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		
		songList = new SongList();
		players = new ArrayList<>();
		//TEST PLAYERS
		players.add(new Player("Testplayer1", null, null));
		players.add(new Player("Testplayer2", null, null));
	}
	
	public void update(){
		
	}
	/*
	public void draw(){
		
	}
	*/
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batch.setProjectionMatrix(guiCam.combined);
		game.batch.begin();
		game.font.draw(game.batch, songList.songs().toString(), Constants.VIEWPORT_DIM_X/15, Constants.VIEWPORT_DIM_Y - 10);
		game.font.draw(game.batch, "MAIN MENU TEST", Constants.VIEWPORT_DIM_X/2, Constants.VIEWPORT_DIM_Y/2);
		game.font.draw(game.batch, "PRESS THE ANY KEY", Constants.VIEWPORT_DIM_X/2, Constants.VIEWPORT_DIM_Y/2 - 100);
		game.batch.end();

		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			try {
				game.setScreen(new GameScreen(game, songList.getSong(songList.songs().iterator().next()), players)); //TODO The Song argument is null!
				//song takes the text in the text doc as a String
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dispose();
		}
	}
	
	@Override
	public void pause(){
		
	}
	
}
