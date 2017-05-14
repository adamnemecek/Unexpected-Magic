package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.Queue;

import com.mygdx.game.utilities.file.FileReader;

/**
 * A class representing a single song.
 * @author car0b1nius
 */
public class Song implements ISong {
	private final String title;
	private final int bpm;
	private final int[] time;
	//TODO make IVoice
	private final Voice[] voices;
	public Song(String path) throws IOException {
		Queue<String> sList = FileReader.readUXM(path);
		title = sList.poll();
		String[] t = sList.poll().split("/", 2);
		time = new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])};
		bpm = Integer.parseInt(sList.poll());
		voices = new Voice[sList.size()];
		int i = 0;
		for(String s : sList) {
			voices[i++] = new Voice(s);
		}
	}
	public Voice[] getVoices() {
		return voices.clone();
	}
	public int[] getTime() {
		return time.clone();
	}
	@Override
	public String getTitle() {
		return title;
	}
	@Override
	public int getBpm() {
		return bpm;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Voice v : voices) {
			sb.append("[").append(v.toString()).append("]");
		}
		return sb.toString();
	}
}
