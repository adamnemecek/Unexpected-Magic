package com.mygdx.game.gameEngine.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.gameEngine.components.*;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

import java.util.List;

/**
 * A factory class that handles creation of entities.
 * @author soflarb
 * Revised by 
 */
public class EntityFactory {

	private static final float NoteOrigPosY = Constants.VIEWPORT_DIM_Y;
	private static final int NoteVelY = -64;
	private static int nextColor =0;
	private static final SpriteFactory spriteFactory = new SpriteFactory();

	public static List<Entity> createNoteEntity(List<Player> players){
		for (int playerIndex = 0; playerIndex < players.size(); playerIndex ++){
			Player player = players.get(playerIndex);

			IVoice voice = player.getVoice();
			for (int tick = 0; tick < voice.length(); tick ++){
				INote note = voice.noteAtTick(tick);
				if (note == null || note.isRest()) continue;

				createNoteEntity(note,voice,playerIndex, tick);

			}
		}
		return null;
	}

	public static Entity createNoteEntity(INote note, IVoice voice, int playerNumber, int tick){

		Entity entity = new Entity();
		int posX = (note.getPitch() % Constants.NUMBER_OF_LANES);
		PositionComponent positionComponent = new PositionComponent(posX, tick);
		NoteComponent noteComponent = new NoteComponent(note);
		CompositeSpriteComponent spriteComponent = new CompositeSpriteComponent(spriteFactory.createSprites(note.getDuration(),playerNumber));
		HitComponent hitComponent = new HitComponent();
		VoiceComponent voiceComponent = new VoiceComponent(voice);
		entity.add(positionComponent).add(noteComponent).add(hitComponent).add(voiceComponent);
		entity.add(spriteComponent);
		return entity;
	}


}

