package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Song {
	public final String title, time;
	public final int bpm;
	private final Voice[] voices;
	//TODO more input validation
	public Song(String uxm) throws IOException {
		StringBuilder sb = new StringBuilder(uxm);
		//First pass: strip comments
		{
			for(int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				//Skip strings
				if(ch == '"') {
					i = sb.indexOf("\"", i+1);
				}
				//Remove comments
				if(ch == '%') {
					sb.delete(i, sb.indexOf("\n", i--));
				}
			}
		}
		//Second pass: remove non-string whitespace
		{
			for(int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				//Skip strings
				if(ch == '"') {
					i = sb.indexOf("\"", i+1);
				}
				if(Character.isWhitespace(ch)) {
					sb.deleteCharAt(i--);
				}
			}
		}
		//Final pass: split to list
		Queue<String> sList = new LinkedList<>();
		{
			for(int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				//Skip strings
				if(ch == '"') {
					i = sb.indexOf("\"", i);
				}
				if(ch == ';') {
					sList.add(sb.substring(0, i));
					sb.delete(0, i+1);
					i = 0;
				}
			}
		}
		//It's parsing time.
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
