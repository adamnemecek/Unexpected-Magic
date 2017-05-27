package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.sound.MusicPlayer;
import com.mygdx.game.utils.Constants;

/**
 * The logo screen, shown when the application launches.
 * @author soflarb
 * 
 * Uses: AbstractScreen, MainMenuScreen, MusicPlayer
 * 
 * Used by: UnexpectedMagic
 */

public class TitleScreen extends AbstractScreen{

	private final Texture bg = new Texture("images/UnexpectedMagicBackground5.png");
	private final Texture logo = new Texture("images/uxm-logo148x32.png");
	private final Texture cred = new Texture("images/uxm-lcred-125x43-shad-2.png");
	public TitleScreen(SpriteBatch batch) {
		super(batch);
		MusicPlayer.getInstance().play("main-theme");
	}
	@Override
	public void render(float delta){
		update(delta);
		viewport.apply(true);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(bg, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		float scale = 1.7f;
		float scaledLogoX = (Constants.VIEWPORT_DIM_X - logo.getWidth()*scale)/2;
		float scaledLogoY = (Constants.VIEWPORT_DIM_Y - logo.getHeight()/2*scale)/2;
		float scaledLogoWidth = logo.getWidth()*scale;
		float scaledLogoHeight = logo.getHeight()*scale;
		batch.draw(logo, scaledLogoX, scaledLogoY, scaledLogoWidth, scaledLogoHeight);
		batch.draw(cred, (Constants.VIEWPORT_DIM_X - cred.getWidth())/2, scaledLogoY - cred.getHeight() - 15, cred.getWidth(), cred.getHeight());
		
		batch.end();
	}
	public void update(float delta) {
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			changeToScreen(new MainMenuScreen(batch));
			dispose();
		}
	}
	@Override
	public void dispose() {
		super.dispose();
		bg.dispose();
		logo.dispose();
		cred.dispose();
	}
}
