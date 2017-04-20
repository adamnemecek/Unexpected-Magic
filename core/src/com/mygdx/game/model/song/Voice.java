package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Voice {
	private final Note[] notes;
	
	public final String instrument;
	public final int length;
	public Voice(String voice, String instrument) throws IOException {
		this.instrument = instrument;
		
		List<Note> nList = new LinkedList<>();
		int sum = 0;
		for(String note : voice.split("[,|]")) {
			Note n = Note.getNote(note);
			nList.add(n);
			sum += n.calculateLength();
		}
		notes = new Note[sum];
		sum = 0;
		for(Note note : nList) {
			notes[sum] = note;
			sum += note.calculateLength();
		}
		oMGIHAVECORNINMYCODEEEEE();
		length = sum;
	}
	public Note[] getNotes() {
		return notes.clone();
	}
	private void oMGIHAVECORNINMYCODEEEEE(){
		for(int i=0;i<notes.length;i++){
			System.out.print(notes[i]+", ");
		}
	}
	/*
	private int calculateLength(){
		int sum = 0;
		for(Note note : notes){
			sum += note.calculateLength();
		}
		return sum;
	}
	*/
}
