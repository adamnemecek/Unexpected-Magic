package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Constants;
import com.mygdx.game.UnexpectedMagic;

/**
* Screen that contains the main menu.
*/
public class MainMenuScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	OrthographicCamera guiCam;
	

	public MainMenuScreen(final UnexpectedMagic game){
		this.game = game;
		guiCam = new OrthographicCamera(Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1]);
		guiCam.setToOrtho(false, Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1]);
	}
	
	public void update(){
		
	}
	
	public void draw(){
		
	}
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batch.setProjectionMatrix(guiCam.combined);
		game.batch.begin();
		game.font.draw(game.batch, "MAIN MENU TEST", Constants.VIEWPORT_DIM[0]/2, Constants.VIEWPORT_DIM[1]/2);
		game.batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			game.setScreen(new GameScreen(game)); //TODO
			dispose();
		}
	}
	
	@Override
	public void pause(){
		
	}
	
}
