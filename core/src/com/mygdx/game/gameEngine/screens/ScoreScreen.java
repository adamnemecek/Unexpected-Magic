package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Screen that shows score after a game.
 * @author soflarb
 */
public class ScoreScreen extends AbstractScreen{

	protected ScoreScreen(Engine engine, SpriteBatch batch) {
		super(engine, batch);
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true, true);
		stage.addActor(table);
		
		//text
		Label testLabel = new Label("This is\nscoreScreen.\nResults will be\nshown here!", skin);
		
		// table layout
		table.add(testLabel).fillX();
		table.row();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();
	}
}
