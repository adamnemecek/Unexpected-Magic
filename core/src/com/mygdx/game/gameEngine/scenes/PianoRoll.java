package com.mygdx.game.gameEngine.scenes;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gameEngine.components.CompositeSpriteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.managers.CompositeSprite;
import com.mygdx.game.utilities.file.Constants;

/**
 * A class that defines the properties of the piano roll (the area where the notes are drawn).
 * @author soflarb
 * 
 */
public class PianoRoll {
	private Engine engine;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	private Viewport viewport;
	ComponentMapper<PositionComponent> positionComponentMapper; //TODO Should this class draw all notes?
	ComponentMapper<CompositeSpriteComponent> spriteComponentMapper;

	
	public PianoRoll(Engine engine, SpriteBatch spriteBatch) {
        this.engine = engine;
        batch = spriteBatch;
        camera = new OrthographicCamera();
        viewport = new ScalingViewport(Scaling.fit, Constants.PIANOROLL_DIM_X, Constants.PIANOROLL_DIM_Y, camera);
        viewport.setScreenBounds((int) Constants.PIANOROLL_POS_X, (int) Constants.PIANOROLL_POS_Y, (int) Constants.PIANOROLL_DIM_X, (int) Constants.PIANOROLL_DIM_Y);
        positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteComponentMapper = ComponentMapper.getFor(CompositeSpriteComponent.class);
    }

	private void drawEntities(float delta){
		ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class, CompositeSpriteComponent.class).get());
		batch.begin(); 
		for(Entity entity : entities){
			PositionComponent pos = positionComponentMapper.get(entity);
	        CompositeSpriteComponent spr = spriteComponentMapper.get(entity);
			spr.sprite.draw(batch, pos.getX(), pos.getY());
		}
		batch.end();
	}
	
	public void draw(float delta){
		viewport.apply(true);
		drawEntities(delta);
	}
	
	public void resize(int width, int height, int screenX, int screenY){
		viewport.update(width, height);
		viewport.setScreenPosition(screenX, (int)(screenY + Constants.PIANOROLL_BOT_PADDING * (height/Constants.VIEWPORT_DIM_Y)));
//		viewport.setScreenPosition(screenX, 0);
		
	}
}
