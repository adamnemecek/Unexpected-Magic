package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * A class that represents a single note.
 */
public class Note {
	//static
	private static Map<String, Note> noteMap = new HashMap<>();
	public static Note getNote(String note) throws IOException {
		//sanity checking
		if(!note.contains("/")) return getNote(note + "/1");
		if(!note.matches("([A-G][b#]?\\d|-):\\d+/\\d+")) //Regex might not be the most readable solution, but it's very writable.
			throw new IOException("Invalid note " + note);
		if(noteMap.containsKey(note)) return noteMap.get(note);
		//allocations
		String[] sa = note.split(":");
		String pitch = sa[0];
		sa = sa[1].split("/");
		int[] value = new int[]{Integer.parseInt(sa[0]), Integer.parseInt(sa[1])};
		//note construction/storage
		Note n = new Note(pitch, value);
		noteMap.put(note, n);
		return n;
	}
	//instance
	final String pitch;
	private final int[] value;
	private Note(String pitch, int[] value) {
		this.pitch = pitch;
		this.value = value;
	}
	public String getPitch() {
		return pitch;
	}
	public int[] getValue() {
		return value.clone(); //defensive copying
	}
	@Override
	public String toString() {
		return pitch + ":" + value[0] + "/" + value[1];
	}
	
	
}
