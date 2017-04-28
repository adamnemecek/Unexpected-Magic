package com.mygdx.game.gameEngine.scenes;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.components.SpriteComponent;
import com.mygdx.game.model.Constants;

/**
 * A class that defines the properties of the piano roll (the area where the notes are drawn).
 */
public class PianoRoll {
	private Engine engine;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	Texture backgroundTexture;
	ComponentMapper<PositionComponent> positionComponentMapper;
	ComponentMapper<SpriteComponent> spriteComponentMapper;
	
	public PianoRoll(Engine engine, SpriteBatch spriteBatch){
		this.engine = engine;
		batch = spriteBatch;
		//batch = new SpriteBatch();//TODO is this necessary?
		camera = new OrthographicCamera();
//		viewport = new ScreenViewport(camera);
		viewport = new ScalingViewport(Scaling.fit, Constants.PIANOROLL_DIM_X, Constants.PIANOROLL_DIM_Y, camera);
		
		
		viewport.setScreenBounds((int)Constants.PIANOROLL_POS_X, (int)Constants.PIANOROLL_POS_Y, (int)Constants.PIANOROLL_DIM_X, (int)Constants.PIANOROLL_DIM_Y);
		//viewport.apply(true);
		//camera.zoom = 0.5f;
		backgroundTexture = new Texture("images/textureCheckedPurple16x16.png");
		//backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
		spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
	}

	private void drawEntities(float delta){
		ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
		for(Entity entity : entities){
			PositionComponent pos = positionComponentMapper.get(entity);
	        SpriteComponent spr = spriteComponentMapper.get(entity);
	        batch.begin();
	        batch.draw(spr.sprite.getTexture(), (float)Math.floor(pos.x), (float)Math.floor(pos.y));
	        //batch.draw(spr.sprite.getTexture(), pos.x, pos.y);
	        batch.end();
		}
	}
	
	public void draw(float delta){
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, 2000, 2000, 0, 10, 10, 0);
		batch.end(); 
		drawEntities(delta);
	}
	
	public void resize(int width, int height, int screenX, int screenY){
		viewport.update(width, height);
		viewport.setScreenPosition(screenX, (int)(screenY + Constants.PIANOROLL_BOT_PADDING * (height/Constants.VIEWPORT_DIM_Y)));
//		viewport.setScreenPosition(screenX, 0);
		
	}
	
}
