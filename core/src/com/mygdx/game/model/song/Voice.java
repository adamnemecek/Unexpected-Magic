package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * A class representing a single voice of a song.
 * @author car0b1nius
 * Revised by soflarb
 */
public class Voice implements IVoice {
	
	private static int voiceNum;
	private final int voiceNumber;
	private final NavigableMap<Integer, INote> notes;
	private final int min, max;
	public Voice(String voice) throws IOException {
		List<Note> nList = new LinkedList<>();
		int sum = 0;
		for(String note : voice.split("[,|]")) {
			Note n = Note.getNote(note);
			nList.add(n);
			sum += n.getDuration();
		}
		voiceNumber = voiceNum;
		voiceNum++;
		notes = new TreeMap<>();
		sum = 0;
		int min = nList.get(0).getPitch(), max = min;
		for(INote note : nList) {
			if(note != null) {
				min = Math.min(min, note.getPitch());
				max = Math.max(max, note.getPitch());
			}
			notes.put(sum, note);
			sum += note.getDuration();
		}
		
		this.min = min;
		this.max = max;
		//oMGIHAVECORNINMYCODEEEEE();
		
	}
	public INote noteAtTick(int tick) {
		return notes.get(tick);
	}
	int octaveAtTick(int tick) {
		return noteClosestToTick(tick).getOctave();
	}
	private INote noteClosestToTick(int tick) {
		if(notes.get(tick) != null) return notes.get(tick);
		int lo = notes.lowerKey(tick);
		int hi = notes.higherKey(tick);
		return tick - lo <= hi - tick ? notes.get(lo) : notes.get(hi);
	}
	public int length() {
		int last = notes.lastKey();
		return last + notes.get(last).getDuration();
	}
	/*@Deprecated
	private void oMGIHAVECORNINMYCODEEEEE(){
		System.out.println(toString());
	}*/
	@Override
	public String toString() {
		return notes.toString();
	}
	@Override
	public int max() {
		return max;
	}
	@Override
	public int min() {
		return min;
	}
	
	public int voiceNumber(){
		return voiceNumber;
	}
}
