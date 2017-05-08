package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.model.song.INote;

/**
* Component that holds the note.
*/
public class NoteComponent implements Component{
	public INote note;
	public NoteComponent(INote note){
		this.note = note;
	}
}
