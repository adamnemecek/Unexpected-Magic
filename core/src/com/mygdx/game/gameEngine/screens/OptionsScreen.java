package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.utils.Constants;

public class OptionsScreen extends AbstractScreen {

	public OptionsScreen(/*final Engine engine,*/ SpriteBatch batch) {
		super(/*engine,*/ batch);
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		//able.setDebug(true, true);
		table.top();
		table.left();
		stage.addActor(table);
				
		//back button
				TextButton backButton = new TextButton("Back", skin);
				backButton.addListener(
						(Event event) -> {
							if(!(event instanceof InputEvent)) return false;
							InputEvent evt = (InputEvent) event;
							if(evt.getType() != InputEvent.Type.touchDown) return false;
							changeToPreviousScreen();
							return true;
						}
						);
		table.add(backButton);
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
