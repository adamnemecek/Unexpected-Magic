package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.model.song.INote;

/**
* Component that holds the note.
* @author soflarb
* Revised by car0b1nius
*/
public class NoteComponent implements Component{
	private INote note;
	public NoteComponent(INote note){
		this.note = note;
	}
	
	public INote getNote(){
		return note;
	}
	
}
