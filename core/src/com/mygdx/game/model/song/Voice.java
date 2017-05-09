package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a single voice of a song.
 * @author car0b1nius
 * Revised by soflarb
 */
public class Voice implements IVoice {
	//TODO make INote
	private final Note[] notes;
	private final int min, max;
	public Voice(String voice) throws IOException {
		List<Note> nList = new LinkedList<>();
		int sum = 0;
		for(String note : voice.split("[,|]")) {
			Note n = Note.getNote(note);
			nList.add(n);
			sum += n.getDuration();
		}
		notes = new Note[sum];
		sum = 0;
		int min = nList.get(0).getPitch(), max = min;
		for(Note note : nList) {
			if(note != null) {
				min = Math.min(min, note.getPitch());
				max = Math.max(max, note.getPitch());
			}
			notes[sum] = note;
			sum += note.getDuration();
		}
		this.min = min;
		this.max = max;
		//oMGIHAVECORNINMYCODEEEEE();
		
	}
	public Note noteAtTick(int tick) {
		return notes[tick];
	}
	public int length() {
		return notes.length;
	}
	
	public Note[] getNotes(){
		return notes;
	}
	/*@Deprecated
	private void oMGIHAVECORNINMYCODEEEEE(){
		System.out.println(toString());
	}*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(notes[0].toString());
		for(int i=1;i<notes.length;i++){
			sb.append(", ").append(notes[i]);
		}
		return sb.toString();
	}
	@Override
	public int max() {
		return max;
	}
	@Override
	public int min() {
		return min;
	}
}
