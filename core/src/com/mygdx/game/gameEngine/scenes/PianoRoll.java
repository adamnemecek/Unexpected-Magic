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

/**
 * A class that defines the properties of the piano roll (the area where the notes are drawn).
 * @author ???
 * 
 */
public class PianoRoll {
	private Engine engine;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	private NoteLanes noteLanes;
	private Texture activeTexture;
	private Texture inactiveTexture;
	ComponentMapper<PositionComponent> positionComponentMapper; //TODO Should this class draw all notes?
	ComponentMapper<SpriteComponent> spriteComponentMapper;	

	
	public PianoRoll(Engine engine, SpriteBatch spriteBatch, NoteLanes noteLanes) {
        this.engine = engine;
        this.noteLanes = noteLanes;
        batch = spriteBatch;
        camera = new OrthographicCamera();
        viewport = new ScalingViewport(Scaling.fit, Constants.PIANOROLL_DIM_X, Constants.PIANOROLL_DIM_Y, camera);
        viewport.setScreenBounds((int) Constants.PIANOROLL_POS_X, (int) Constants.PIANOROLL_POS_Y, (int) Constants.PIANOROLL_DIM_X, (int) Constants.PIANOROLL_DIM_Y);
        positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
        
        this.activeTexture = new Texture("images/lanes/Blue.png");
        this.inactiveTexture = new Texture("images/lanes/Red.png");
    }

	private void drawEntities(float delta){
		ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
		for(Entity entity : entities){
			PositionComponent pos = positionComponentMapper.get(entity);
	        SpriteComponent spr = spriteComponentMapper.get(entity);
	        batch.draw(spr.sprite.getTexture(), (float)Math.floor(pos.getX()), (float)Math.floor(pos.getY()));
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
	/**
	 * 
	 * @deprecated go via roundmanager instead
	 */
	@Deprecated
	public void activateLane(int lane){
		this.noteLanes.activateLane(lane);
	}
	
	/**
	 * 
	 * @deprecated go via roundmanager instead
	 */
	@Deprecated
	public void deactivateLane(int lane){
		this.noteLanes.deactivateLane(lane);
	}
	
	private void drawLanes(){
		for(int i  = 0; i < Constants.NUMBER_OF_LANES; i ++){
			batch.draw(getLaneTexture(i),Constants.LANE_WIDTH*i,Constants.SCORE_BOUNDS_LOWER,Constants.LANE_WIDTH,Constants.SCORE_BOUNDS_UPPER-Constants.SCORE_BOUNDS_LOWER);
		}
	}
	public Texture getLaneTexture(int i){
	    if(noteLanes.getLaneState(i)){
	        return activeTexture;
        }
        else return inactiveTexture;
    }

    public NoteLanes getNoteLanes(){
	    return noteLanes;
    }
}
