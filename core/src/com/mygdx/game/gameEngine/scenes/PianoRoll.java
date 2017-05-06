package com.mygdx.game.gameEngine.scenes;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.components.SpriteComponent;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.song.Note;

/**
 * A class that defines the properties of the piano roll (the area where the notes are drawn).
 */
public class PianoRoll {
	private Engine engine;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	private NoteLanes noteLanes;
	ComponentMapper<PositionComponent> positionComponentMapper; //TODO Should this class draw all notes?
	ComponentMapper<SpriteComponent> spriteComponentMapper;	

	
	public PianoRoll(Engine engine, SpriteBatch spriteBatch) {
        this.engine = engine;
        this.noteLanes = new NoteLanes();
        batch = spriteBatch;
        camera = new OrthographicCamera();
        viewport = new ScalingViewport(Scaling.fit, Constants.PIANOROLL_DIM_X, Constants.PIANOROLL_DIM_Y, camera);
        viewport.setScreenBounds((int) Constants.PIANOROLL_POS_X, (int) Constants.PIANOROLL_POS_Y, (int) Constants.PIANOROLL_DIM_X, (int) Constants.PIANOROLL_DIM_Y);
        positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

	private void drawEntities(float delta){
		ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
		for(Entity entity : entities){
			PositionComponent pos = positionComponentMapper.get(entity);
	        SpriteComponent spr = spriteComponentMapper.get(entity);
	        batch.draw(spr.sprite.getTexture(), (float)Math.floor(pos.x), (float)Math.floor(pos.y));
		}
	}
	
	public void draw(float delta){
		batch.begin(); 
		drawLanes();
		drawEntities(delta);
		batch.end();
	}
	
	public void resize(int width, int height, int screenX, int screenY){
		viewport.update(width, height);
		viewport.setScreenPosition(screenX, (int)(screenY + Constants.PIANOROLL_BOT_PADDING * (height/Constants.VIEWPORT_DIM_Y)));
//		viewport.setScreenPosition(screenX, 0);
		
	}
	
	public void activateLane(int lane){
		this.noteLanes.activateLane(lane);
	}
	
	public void deactivateLane(int lane){
		this.noteLanes.deactivateLane(lane);
	}
	
	private void drawLanes(){
            for(int i  = 0; i < Constants.NUMBER_OF_LANES; i ++){
                batch.draw(noteLanes.getLaneTexture(i),Constants.LANE_WIDTH*i,0,Constants.LANE_WIDTH,Constants.SCORE_LINE);
            }
        }

    public NoteLanes getNoteLanes(){
	    return noteLanes;
    }
}
