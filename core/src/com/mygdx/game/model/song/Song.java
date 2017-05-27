package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.Queue;

import com.mygdx.game.utils.FileReader;

/**
 * A class representing a single song.
 * @author car0b1nius
 * 
 * Uses: ISong, IVoice, Voice, FileReader
 * 
 * Used By: SongList, RoundTest
 */
public class Song implements ISong {
	private final String title;
	private final int bpm;
	private final int[] time;
	private final IVoice[] voices;
	@Deprecated
	public Song(String path) throws IOException {
		this(FileReader.readUXM(path));
	}
	public Song(Queue<String> uxm) throws IOException {
		title = uxm.poll();
		String[] t = uxm.poll().split("/", 2);
		time = new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])};
		bpm = Integer.parseInt(uxm.poll());
		voices = new Voice[uxm.size()];
		int i = 0;
		for(String s : uxm) {
			voices[i++] = new Voice(s);
		}
	}
	@Override
	public IVoice[] getVoices() {
		return voices.clone();
	}
	@Override
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
		for(IVoice v : voices) {
			sb.append("[").append(v.toString()).append("]");
		}
		return sb.toString();
	}
	public int length() {
		int max = 0;
		for(IVoice voice : getVoices()) {
			max = Math.max(max, voice.length());
		}
		return max;
	}
}
