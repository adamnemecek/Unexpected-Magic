package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Constants;

/**
 * 
 * @author Arvid
 *
 */

public abstract class AbstractScreen extends ScreenAdapter{
	final UnexpectedMagic game;
	OrthographicCamera camera;
	Viewport viewport;
	protected Stage stage;
	
	
	protected AbstractScreen(final UnexpectedMagic game){
		this.game = game;
		camera = new OrthographicCamera(Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		camera.setToOrtho(false, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, camera);
		viewport.apply();
		stage = new Stage(viewport, game.batch);
	}

}
