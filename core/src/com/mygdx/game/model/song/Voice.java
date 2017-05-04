package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A class representing a single voice of a song.
 * @author car0b1nius
 * Revised by soflarb
 */
public class Voice {
	private final Note[] notes;
	public final int length;
	public final int min, max;
	@Deprecated
	public Voice(String voice, String instrument) throws IOException {
		this(voice);
	}
	public Voice(String voice) throws IOException {
		List<Note> nList = new LinkedList<>();
		int sum = 0;
		int min = nList.get(0).number, max = min;
		for(String note : voice.split("[,|]")) {
			Note n = Note.getNote(note);
			min = Math.min(min, n.number);
			max = Math.max(max, n.number);
			nList.add(n);
			sum += n.duration;
		}
		this.min = min;
		this.max = max;
		length = sum;
		notes = new Note[sum];
		sum = 0;
		for(Note note : nList) {
			notes[sum] = note;
			sum += note.duration;
		}
		//oMGIHAVECORNINMYCODEEEEE();
		
	}
	public Note noteAtTick(int tick) {
		return notes[tick];
	}
	public int length() {
		return notes.length;
	}
	@Deprecated
	public Note[] getNotes() {
		return notes.clone();
	}
	@Deprecated
	private void oMGIHAVECORNINMYCODEEEEE(){
		System.out.println(toString());
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(notes[0].toString());
		for(int i=0;i<notes.length;i++){
			sb.append(", ").append(notes[i]);
		}
		return sb.toString();
	}
}
