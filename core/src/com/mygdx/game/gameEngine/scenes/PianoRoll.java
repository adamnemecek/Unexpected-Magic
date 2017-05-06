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
 */
public class PianoRoll {
	private Engine engine;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	ComponentMapper<PositionComponent> positionComponentMapper;
	ComponentMapper<SpriteComponent> spriteComponentMapper;	
	private Texture activeLaneTexture;
	private Texture inactiveLaneTexture;
	private boolean [] laneStates; //True = active, False = inactive
	private int nLanes = Constants.NUMBER_OF_LANES; //TODO
	private float laneWidth;
	
	
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
		activeLaneTexture = new Texture("images/Lanes/Lime.png");
		inactiveLaneTexture = new Texture("images/Lanes/Yellow.png");
		laneStates = new boolean[nLanes];
		laneWidth = Constants.PIANOROLL_DIM_X/nLanes;
		
		//set all lanes inactive
		for (int i = 0; i < nLanes; i ++){
			laneStates[i] = false;
		}
		
		//backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
		spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
	}

	private void drawEntities(float delta){
		ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
		for(Entity entity : entities){
			PositionComponent pos = positionComponentMapper.get(entity);
	        SpriteComponent spr = spriteComponentMapper.get(entity);
	        
	        batch.draw(spr.sprite.getTexture(), (float)Math.floor(pos.x)+Constants.LANE_WIDTH/2, (float)Math.floor(pos.y));
	        //batch.draw(spr.sprite.getTexture(), pos.x, pos.y);

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
		this.laneStates[lane] = true;
	}
	
	public void deactivateLane(int lane){
		this.laneStates[lane] = false;
	}
	
	public void drawLanes(){
		for (int i = 0; i < laneStates.length; i ++) {
            if (laneStates[i]) {
                batch.draw(activeLaneTexture, laneWidth * i, 50, laneWidth, 50, 0, 10, 10, 0);
            } else {
                batch.draw(inactiveLaneTexture, laneWidth * i, 50, laneWidth, 50, 0, 10, 10, 0);
            }
        }

		}

	public boolean getLaneState(int i){
    return this.laneStates[i];
	}
}
