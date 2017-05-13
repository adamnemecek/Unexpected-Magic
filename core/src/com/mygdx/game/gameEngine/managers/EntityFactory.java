package com.mygdx.game.gameEngine.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.components.SpriteComponent;
import com.mygdx.game.gameEngine.components.VelocityComponent;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.song.INote;

public class EntityFactory {

	private static final float NoteOrigPosY = Constants.VIEWPORT_DIM_Y;
	private static final int NoteVelY = -64;
	
	public static Entity createNoteEntity(INote note){
		Entity entity = new Entity();
		float posX = (note.getPitch() % Constants.NUMBER_OF_LANES)*Constants.LANE_WIDTH +(Constants.LANE_WIDTH/2)-10;
		PositionComponent positionComponent = new PositionComponent(posX, NoteOrigPosY);
		VelocityComponent velocityComponent = new VelocityComponent(0, NoteVelY);
		NoteComponent noteComponent = new NoteComponent(note);
		SpriteComponent normalSprite = new SpriteComponent(new Sprite(new Texture("sprites/note1pink.png")));
		HitComponent hitComponent = new HitComponent();
		entity.add(positionComponent).add(velocityComponent).add(noteComponent).add(normalSprite).add(hitComponent);
		return entity;
	}
	//TODO remove?
	public static Entity createPlayerEntity(float posX, Sprite sprite){
		Entity entity = new Entity();
		/*
		PositionComponent positionComponent = new PositionComponent(posX, NoteOrigPosY);
		VelocityComponent velocityComponent = new VelocityComponent(0, NoteVelY);
		NoteComponent noteComponent = new NoteComponent(note);
		SpriteComponent spriteComponent = new SpriteComponent(sprite);
		entity.add(positionComponent).add(velocityComponent).add(noteComponent).add(spriteComponent);
		*/
		return entity;
	}

}
/*
//Create a test entity----
Entity testEntity = new Entity();
PositionComponent testPositionComponent = new PositionComponent();
VelocityComponent testVelocityComponent = new VelocityComponent();
SpriteComponent testSpriteComponent = new SpriteComponent();
RenderableComponent testRenderableComponent = new RenderableComponent();
//TODO How does one give the components values? I'll do it like this now.
testPositionComponent.x = 0;
testPositionComponent.y = Constants.VIEWPORT_DIM_Y;
testVelocityComponent.x = 0;
testVelocityComponent.y = -4;
testSpriteComponent.sprite = new Sprite(new Texture("sprites/note1.png"));
testEntity.add(testPositionComponent).add(testVelocityComponent).add(testSpriteComponent).add(testRenderableComponent);
//----

//TODO How does one give the components values? I'll do it like this now.
testPositionComponent2.x = 20;
testPositionComponent2.y = Constants.VIEWPORT_DIM_Y;
testVelocityComponent2.x = 0;
testVelocityComponent2.y = -4;
testSpriteComponent2.sprite = new Sprite(new Texture("sprites/note1.png"));
testEntity2.add(testPositionComponent2).add(testVelocityComponent2).add(testSpriteComponent2).add(testRenderableComponent2);
//----
//Add all entities to engine
//engine.addEntity(testEntity);
//engine.addEntity(testEntity2);

public Entity createTestEntity(){
//Create a test entity
Entity entity = new Entity();
PositionComponent positionComponent = new PositionComponent();
VelocityComponent velocityComponent = new VelocityComponent();
SpriteComponent spriteComponent = new SpriteComponent();
RenderableComponent renderableComponent = new RenderableComponent();
return entity;
}
*/
