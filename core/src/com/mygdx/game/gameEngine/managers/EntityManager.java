package com.mygdx.game.gameEngine.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.systems.MovementSystem;
import com.mygdx.game.model.song.Note;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.Voice;
import com.mygdx.game.model.Constants;

/**
* Class for managing entities.
*/
public class EntityManager {
	//final UnexpectedMagic game;
	private Engine engine;
	SpriteBatch batch;
	Song song;
	
	public EntityManager(Engine engine, SpriteBatch batch/*, UnexpectedMagic game*/, Song song){
		//this.game = game;
		this.engine = engine;
		this.batch = batch;
		this.song = song;
		
		//Create all the systems
		MovementSystem movementSystem = new MovementSystem();
		//Add all the systems to the engine
		this.engine.addSystem(movementSystem);
	}
	
	public void update(int tick){
		manageNoteEntities(tick);
	}
	int prevTick = -1;
	private void manageNoteEntities(int tick){
		if(tick != prevTick) {
			prevTick = tick;
			for(Voice voice : song.getVoices()){
				if(tick < voice.getNotes().length){
					Note note = voice.getNotes()[tick];
					//float posX = 100;
					if(note != null){
						Entity newNoteEntity = EntityFactory.createNoteEntity(note);
						engine.addEntity(newNoteEntity);
					}
				}
			}
		}
	}	
}
	

