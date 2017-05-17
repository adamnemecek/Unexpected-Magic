package com.mygdx.game.gameEngine.managers;

import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.components.SpriteComponent;
import com.mygdx.game.gameEngine.components.VelocityComponent;
import com.mygdx.game.gameEngine.components.VoiceComponent;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;
/**
 * A factory class that handles creation of entities.
 * @author soflarb
 * Revised by 
 */
public class EntityFactory {

	private static final float NoteOrigPosY = Constants.VIEWPORT_DIM_Y;
	private static final int NoteVelY = -64;
	private static int nextColor =0;

	
	public static Entity createNoteEntity(INote note, IVoice voice){
		Entity entity = new Entity();
		float posX = (note.getPitch() % Constants.NUMBER_OF_LANES)*Constants.LANE_WIDTH +(Constants.LANE_WIDTH/2)-10;
		PositionComponent positionComponent = new PositionComponent(posX, NoteOrigPosY);
		VelocityComponent velocityComponent = new VelocityComponent(0, NoteVelY);
		NoteComponent noteComponent = new NoteComponent(note);

		SpriteComponent test = new SpriteComponent();
		test.sprite.addSprite(new Sprite(new Texture("sprites/note-top.png")),50,60,10,10);
		test.sprite.addSprite(new Sprite(new Texture("sprites/note-mid.png")),50,50,10,10);
		test.sprite.addSprite(new Sprite(new Texture("sprites/note-mid.png")),50,40,10,10);
		test.sprite.addSprite(new Sprite(new Texture("sprites/note-bot.png")),50,30,10,10);


		HitComponent hitComponent = new HitComponent();
		VoiceComponent voiceComponent = new VoiceComponent(voice);
		entity.add(positionComponent).add(velocityComponent).add(noteComponent).add(hitComponent).add(voiceComponent);
		entity.add(test);
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
	
	private static Texture newNote(int voiceNum){
		
		
		switch(voiceNum){
			case 0: return new Texture("sprites/note1pink.png");
			
			case 1: return new Texture("sprites/note1blue.png");
			
			case 2: return new Texture("sprites/note1yellow.png");
			
			case 3: return new Texture("sprites/note1green.png");
			
			default: return new Texture("sprites/note1green.png");
			}

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
