package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * A class that represents a single note.
 */
public class Note {
	//static
	private static Map<String, Map<String, Note>> noteMap = new HashMap<>();
	public static Note getNote(String note) throws IOException {
		//allocations
		Note n;
		Map<String, Note> pitchMap;
		String[] sa = note.split(":");
		String pitch = sa[0], value = sa[1];
		//sanity checking
		if(sa.length != 2) throw new IOException("Note should have exactly 1 colon");
		if(!(pitch.equals("-") || pitch.matches("[A-G][b#]?\\d"))) throw new IOException("Invalid note pitch " + pitch);
		if(!value.matches("\\d+(/\\d+)?")) throw new IOException("Invalid note value " + value);
		//note retrieval/construction
		if(noteMap.containsKey(pitch)) {
			pitchMap = noteMap.get(pitch); 
		} else {
			pitchMap = new HashMap<>();
			noteMap.put(pitch, pitchMap);
		}
		if(pitchMap.containsKey(value)) {
			n = pitchMap.get(value);
		} else {
			n = new Note(pitch, value);
			pitchMap.put(value, n);
		}
		return n;
	}
	//instance
	final String pitch;
	final String value;
	private Note(String pitch, String value) {
		this.pitch = pitch;
		this.value = value;
	}
}
