package com.mygdx.game.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * Class for reading .uxm files.
 * @author car0b1nius
 * 
 * Uses: None
 * 
 * Used by: Song, SongList
 */
public class FileReader {
	private FileReader(){}
	public static String readFile(String path) throws FileNotFoundException {
		return readFile(new File(path));
	}
	public static String readFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		String s = sc.next();
		sc.close();
		return s;
	}
	public static String fileExtension(String path) {
		if(!path.contains(".")) return null;
		return path.substring(path.lastIndexOf(".")+1);
	}
	public static String fileExtension(File file) {
		return fileExtension(file.getName());
	}
	public static LinkedList<String> readUXM(String path) throws IOException {
		return readUXM(new File(path));
	}
	public static LinkedList<String> readUXM(File file) throws IOException {
		StringBuilder sb = new StringBuilder(readFile(file));
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
		LinkedList<String> sList = new LinkedList<>();
		{
			for(int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				//Skip strings
				if(ch == '"') {
					sb.deleteCharAt(i--);
					i = sb.indexOf("\"", i);
					sb.deleteCharAt(i--);
				}
				if(ch == ';') {
					sList.add(sb.substring(0, i));
					sb.delete(0, i+1);
					i = 0;
				}
			}
		}
		return sList;
	}
}
