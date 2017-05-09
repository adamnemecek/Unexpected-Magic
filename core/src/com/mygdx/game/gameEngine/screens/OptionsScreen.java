package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Constants;

public class OptionsScreen extends AbstractScreen {

	private TextureAtlas atlas;
	private Skin skin;

	public OptionsScreen(final UnexpectedMagic game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "OPTIONS SCREEN", Constants.VIEWPORT_DIM_X / 4, Constants.VIEWPORT_DIM_Y / 2);
		game.batch.end();

		stage.act();
		stage.draw();

	}
}
