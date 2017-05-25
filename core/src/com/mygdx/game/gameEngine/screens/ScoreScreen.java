package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.gameEngine.managers.AnimationManager;

/**
 * Screen that shows score after a game.
 * @author soflarb
 */
public class ScoreScreen extends AbstractScreen{

	private AnimationManager djblookAnim;
	
	protected ScoreScreen(SpriteBatch batch) {
		super(batch);
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true, true);
		stage.addActor(table);
		
		//text
		Label testLabel = new Label("This is\nscoreScreen.\nResults will be\nshown here!", skin);
		
		//main menu button
		TextButton mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(
			(Event event) -> {
				if(!(event instanceof InputEvent)) return false;
				InputEvent evt = (InputEvent) event;
				if(evt.getType() != InputEvent.Type.touchDown) return false;
				changeToScreen(new MainMenuScreen(batch));
				return true;
				
			}
		);
		// table layout
		table.add(testLabel).fillX();
		table.row();
		table.add(mainMenuButton);
		
		// animations
		djblookAnim = new AnimationManager(new Texture(Gdx.files.internal("animationSheets/napstablook.png")), batch, 1, 4, 0, 0, 55, 52, 0.1f);

	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();
		djblookAnim.draw();
		
	}
}
