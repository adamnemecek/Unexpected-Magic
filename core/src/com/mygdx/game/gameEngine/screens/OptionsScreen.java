package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.utilities.file.Constants;

public class OptionsScreen extends AbstractScreen {

	public OptionsScreen(final Engine engine, SpriteBatch batch) {
		super(engine, batch);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//game.font.draw(batch, "OPTIONS SCREEN", Constants.VIEWPORT_DIM_X / 4, Constants.VIEWPORT_DIM_Y / 2);
		batch.end();

		stage.act();
		stage.draw();

	}
}
