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
	SoundManager soundManager;
	
	public EntityManager(Engine engine, SpriteBatch batch/*, UnexpectedMagic game*/, Song song){
		//this.game = game;
		this.engine = engine;
		this.batch = batch;
		this.song = song;
		
		//Create all the systems
		MovementSystem movementSystem = new MovementSystem();
		//Add all the systems to the engine
		this.engine.addSystem(movementSystem);
		soundManager = new SoundManager();
	}
	
	public void update(int tick){
		manageNoteEntities(tick);
	}
	int prevTick = -1;
	private void manageNoteEntities(int tick) {
		for(int i = prevTick+1; i <= tick; i++) {
			System.out.println(i);
			for(Voice voice : song.getVoices()){
				if(i >= voice.length) continue;
				Note note = voice.noteAtTick(i);
				//float posX = 100;
				if(note == null) continue;
				Entity newNoteEntity = EntityFactory.createNoteEntity(note);
				engine.addEntity(newNoteEntity);
				soundManager.play(note);
			}
		}
		prevTick = tick;
	}	
}
