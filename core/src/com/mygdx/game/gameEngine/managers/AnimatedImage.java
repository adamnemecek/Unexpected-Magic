package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Class for playing gif animations.
 * @author rastom
 * 
 * Uses: None
 * Used By: MainMenuScreen
 */

public class AnimatedImage {

	private final int frameRows;
	private final int frameCols;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private float speed;

	private Animation<TextureRegion> animation;
	private final Texture animationSheet;
	private final SpriteBatch spriteBatch;

	private float stateTime;

	public AnimatedImage(Texture t, SpriteBatch spriteBatch, int rows, int columns, int x, int y, int width, int height, float speed) {
		this.animationSheet = t;
		this.spriteBatch = spriteBatch;
		this.frameRows = rows;
		this.frameCols = columns;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		create();
	}

	public void create() {

		TextureRegion[][] tmp = TextureRegion.split(animationSheet,
				animationSheet.getWidth() / frameCols,
				animationSheet.getHeight() / frameRows);

		TextureRegion[] frames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		animation = new Animation<>(speed, frames);

		stateTime = 0f;
	}



	public void draw() {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, this.posX, this.posY,this.width,this.height);
		spriteBatch.end();
	}
}

