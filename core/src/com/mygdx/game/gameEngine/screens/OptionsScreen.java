package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Constants;

public class OptionsScreen extends ScreenAdapter{
	final UnexpectedMagic game;
	OrthographicCamera guiCam;
	private Viewport viewport;
	private TextureAtlas atlas;
	private Stage stage;
	private Skin skin;
	
	public OptionsScreen(final UnexpectedMagic game){
		this.game = game;
		guiCam = new OrthographicCamera(Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		guiCam.setToOrtho(false, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		viewport = new ScalingViewport(Scaling.fit,Constants.VIEWPORT_DIM_X,Constants.VIEWPORT_DIM_Y, guiCam);
	    viewport.apply();
		stage = new Stage(viewport, game.batch);
	    atlas = new TextureAtlas("skins/Mother_Skin/terramotherui/terra-mother-ui.atlas");
	    skin = new Skin(Gdx.files.internal("skins/Mother_Skin/terramotherui/terra-mother-ui.json"),atlas);
		}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batch.setProjectionMatrix(guiCam.combined);
		game.batch.begin();
		game.font.draw(game.batch, "OPTIONS SCREEN", Constants.VIEWPORT_DIM_X/4, Constants.VIEWPORT_DIM_Y/2);
		game.batch.end();
		
		 stage.act();
	     stage.draw();

	}
	
	@Override
    public void dispose() {
        skin.dispose();
		atlas.dispose();
       
    }
}
