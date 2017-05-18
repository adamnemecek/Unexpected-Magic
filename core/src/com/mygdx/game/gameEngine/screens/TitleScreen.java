package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utilities.file.Constants;

public class TitleScreen extends AbstractScreen{

	public TitleScreen(SpriteBatch batch) {
		super(batch);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(float delta){
		update(delta);
		viewport.apply(true);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Texture bg = new Texture("images/UnexpectedMagicBackground1.png");
		batch.draw(bg, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		batch.end();
	}
	public void update(float delta) {
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			notifyScreenChange(new MainMenuScreen(batch));
			dispose();
		}
	}
}
