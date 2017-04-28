package com.mygdx.game.gameEngine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Constants;

/**
 * A class that defines the properties of the piano roll (the area where the notes are drawn).
 */
public class PianoRoll {
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	Texture backgroundTexture;
	
	public PianoRoll(SpriteBatch spriteBatch){
		batch = spriteBatch;
		//batch = new SpriteBatch();//TODO is this necessary?
		camera = new OrthographicCamera();
//		viewport = new ScreenViewport(camera);
		viewport = new ScalingViewport(Scaling.fit, Constants.PIANOROLL_DIM_X, Constants.PIANOROLL_DIM_Y, camera);
		
		viewport.setScreenBounds((int)Constants.PIANOROLL_POS_X, (int)Constants.PIANOROLL_POS_Y, (int)Constants.PIANOROLL_DIM_X, (int)Constants.PIANOROLL_DIM_Y);
		//viewport.apply(true);
		//camera.zoom = 0.5f;
		backgroundTexture = new Texture("images/textureCheckedPurple16x16.png");
		//backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
	}
	public void draw(){
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, 2000, 2000, 0, 10, 10, 0);
		batch.end();
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
}
