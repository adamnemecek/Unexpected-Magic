package com.mygdx.game.gameEngine.managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Class for playing gif animations in mainmenu screen.
 * @author rastom
 */

public class AnimationManager {

	private int FRAME_ROWS;
	private int FRAME_COLS;
	private int pos_x;
	private int pos_y;
	private int width;
	private int height;
	private float speed;

	private Animation<TextureRegion> animation;
	private Texture animationSheet;
	private SpriteBatch spriteBatch;

	private float stateTime;

	public AnimationManager(Texture t, SpriteBatch spriteBatch, int rows, int columns, int x, int y, int width, int height, float speed) {
		this.animationSheet = t;
		this.spriteBatch = spriteBatch;
		this.FRAME_ROWS = rows;
		this. FRAME_COLS =columns;
		this.pos_x = x;
		this.pos_y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		create();
	}

	public void create() {

		TextureRegion[][] tmp = TextureRegion.split(animationSheet,
				animationSheet.getWidth() / FRAME_COLS,
				animationSheet.getHeight() / FRAME_ROWS);

		TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		animation = new Animation<TextureRegion>(speed, frames);

		stateTime = 0f;
	}



	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		spriteBatch.draw(currentFrame, this.pos_x, this.pos_y,this.width,this.height);
	}
}

