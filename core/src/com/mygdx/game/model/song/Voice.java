package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Voice {
	private final Note[] notes;
	public final String instrument;
	public Voice(String voice, String instrument) throws IOException {
		this.instrument = instrument;
		/*
		List<Note[]> bList = new LinkedList<>();
		for(String bar : voice.split("[,|]")) {
			List<Note> nList = new LinkedList<>();
			for(String note : bar.split(",")) {
				nList.add(Note.getNote(note));
			}
			bList.add(nList.toArray(new Note[0]));
		}
		bars = bList.toArray(new Note[0][0]);
		*/
		List<Note> nList = new LinkedList<>();
		for(String note : voice.split("[,|]")) {
			nList.add(Note.getNote(note));
		}
		notes = nList.toArray(new Note[0]);
	}
	public Note[] getNotes() {
		return notes.clone();
	}
	
	//method 
}
