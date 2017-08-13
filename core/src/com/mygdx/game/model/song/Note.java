package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * A class that represents a single note.
 * @author car0b1nius
 * 
 * Uses: INote
 * 
 * Used by: Voice, NoteTest, ScoreTest
 */
public class Note implements INote {
	//static
	private static final int minPitch = 0;
	private static final int maxPitch = 127;
	private static final int octaveSize = 12;
	private static final int minNoteDenom = 64;
	
	private static Map<String, Note> noteMap = new HashMap<>();
	public static Note getNote(String note) throws IOException {
		//sanity checking
		if(!note.contains("/")) return getNote(note + "/1");
		/*
		 * Regex explanation
		 * [ABC]: any of A, B and C
		 * [A-C]: any character between A-C (inclusive)
		 * A?: zero or one A
		 * \d: any single digit (needs Java string escaping)
		 * (ABC): the sequence ABC as a capture group, as a single regex entity
		 * (ABC|DEF): either sequence ABC or DEF
		 * A+: any non-zero number of A
		 * [A-G][b#]?\d+: a character A-G, followed by optional b or #, followed by any number
		 * ([A-G][b#]?\d+|-): either the above or a -
		 * \d+/\d+: Any fraction
		 * ([A-G][b#]?\d+|-):\d+/\d+: both the above two lines, separated by a :
		 */
		if(!note.matches("([A-G][b#]?\\d+|-):\\d+/\\d+")) //Regex might not be the most readable solution, but it's very writable.
			throw new IOException("Invalid note " + note);
		if(noteMap.containsKey(note)) return noteMap.get(note);
		//allocations
		String[] sa = note.split(":");
		int num = getNum(sa[0]);
		int dur = getDur(sa[1]);
		//note construction/storage
		Note n = new Note(note, num, dur);
		noteMap.put(note, n);
		return n;
	}
	// http://www.midimountain.com/midi/midi_note_numbers.html
	private static final int[] MAJOR_SCALE = new int[] {0,2,4,5,7,9,11};
	private static int getNum(String str) throws IOException {
		if(str.equals("-")) return -1;
		char pitch = str.charAt(0);
		int alt = 0;
		switch(str.charAt(1)) {
			case 'b':
				alt = -1;
				break;
			case '#':
				alt = +1;
				break;
		}
		//System.out.println(str+ ", " + str.substring(alt == 0 ? 1 : 2));
		int oct = Integer.parseInt(str.replaceAll("[^\\d]", ""));
		int num = MAJOR_SCALE["CDEFGAB".indexOf(pitch)] + alt + oct * octaveSize;
		if(num < minPitch || maxPitch < num) throw new IOException("Invalid pitch: " + str + " (" + num + ")");
		return num;
	}
	
	/* takes in a string representing a Note duration (E.G. 3/4) and returns its length 
	 as a factor of the programs' minimum note length*/
	private static int getDur(String str) {
		String[] sa = str.split("/");
		int num = Integer.valueOf(sa[0]);
		int denom = Integer.valueOf(sa[1]);
		int factor = minNoteDenom / denom;
		return num * factor;
	}
	//instance
	private final int number, duration;
	private final String str;
	private Note(String str, int number, int duration) {
		this.number = number;
		this.duration = duration;
		this.str = str;
	}
	
	@Override
	public String toString() {
		return str;
	}
	@Override
	public int getPitch() {
		return number;
	}
	@Override
	public int getDuration() {
		return duration;
	}
}
