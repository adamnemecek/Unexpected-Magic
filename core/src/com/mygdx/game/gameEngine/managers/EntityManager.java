package com.mygdx.game.gameEngine.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.systems.MovementSystem;
import com.mygdx.game.gameEngine.systems.SoundSystem;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;

/**
* Class for managing entities.
*/
public class EntityManager {
	private Engine engine;
	private SpriteBatch batch;
	private ISong song;

	
	public EntityManager(Engine engine, SpriteBatch batch, ISong song){
		this.engine = engine;
		this.batch = batch;
		this.song = song;
		
		//Create all the systems
		MovementSystem movementSystem = new MovementSystem();
        SoundSystem soundSystem = new SoundSystem(new SoundManager());
		//Add all the systems to the engine
		this.engine.addSystem(movementSystem);
        this.engine.addSystem(soundSystem);
	}
	
	public void update(int tick){
		manageNoteEntities(tick);
	}
	int prevTick = -1;

	private void manageNoteEntities(int tick) {
		for(int i = prevTick+1; i <= tick; i++) {
			for(IVoice voice : song.getVoices()){
				if(i >= voice.length()) continue;
				INote note = voice.noteAtTick(i);
				//float posX = 100;
				if(note == null || note.isRest()) continue;
                    Entity newNoteEntity = EntityFactory.createNoteEntity(note);
                    engine.addEntity(newNoteEntity);


                }
			}

		prevTick = tick;
	}

}
