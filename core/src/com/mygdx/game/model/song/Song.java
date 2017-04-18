package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.Queue;

import com.mygdx.game.services.file.FileReader;

public class Song {
	public final String title, time;
	public final int bpm;
	private final Voice[] voices;
	public Song(String path) throws IOException {
		Queue<String> sList = FileReader.readUXM(path);
		title = sList.poll().replaceAll("\"", "");
		time = sList.poll().replaceAll("\"", "");
		bpm = Integer.parseInt(sList.poll());
		voices = new Voice[sList.size()];
		int i = 0;
		for(String s : sList) {
			voices[i++] = new Voice(s, null);
		}
	}
	public Voice[] getVoices() {
		return voices.clone();
	}
}
