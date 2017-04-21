package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.model.song.Note;

/**
* Component that holds the note.
*/
public class NoteComponent implements Component{
	public Note note;
	public NoteComponent(Note note){
		this.note = note;
	}
}
