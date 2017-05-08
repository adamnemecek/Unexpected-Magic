package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

public abstract class AbstractScreen extends ScreenAdapter {
	final protected UnexpectedMagic game;
	final protected Engine engine;
	protected OrthographicCamera camera;
	protected Viewport viewport;
	protected Stage stage;

	protected TextureAtlas atlas;
	protected Skin skin;

	protected AbstractScreen(final UnexpectedMagic game) {
		this.game = game;
		this.engine = game.engine;
		camera = new OrthographicCamera(Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		camera.setToOrtho(false, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, camera);
		viewport.apply();
		stage = new Stage(viewport, game.batch);
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("skins/Mother_Skin/terramotherui/terra-mother-ui.atlas");
		skin = new Skin(Gdx.files.internal("skins/Mother_Skin/terramotherui/terra-mother-ui.json"), atlas);
	}
	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
