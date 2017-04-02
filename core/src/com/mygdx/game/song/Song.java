package com.mygdx.game.song;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
					i = sb.indexOf("\"", i);
				}
				//Remove comments
				if(ch == '%') {
					sb.delete(i, sb.indexOf("%", i--)+1);
				}
			}
		}
		//Second pass: remove non-string whitespace
		{
			for(int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				//Skip strings
				if(ch == '"') {
					i = sb.indexOf("\"", i);
				}
				if(Character.isWhitespace(ch)) {
					sb.deleteCharAt(i--);
				}
			}
		}
		//Final pass: split to list
		List<String> sList = new LinkedList<>();
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
		sList.set(0, sList.get(0).replaceAll("\"", ""));
		title = sList.remove(0);//For performance reasons, as getting the nth element in a linked list is expensive.
		sList.remove(0);
		sList.set(0, sList.get(0).replaceAll("\"", ""));
		time = sList.remove(0);
		bpm = Integer.parseInt(sList.remove(0));
		voices = new Voice[sList.size()];
		int i = 0;
		for(String s : sList) {
			voices[i++] = new Voice(s, null);
		}
	}
}
