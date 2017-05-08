package com.mygdx.game.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.services.file.FileReader;

public class SongList {
	
	private final Map<String, String> map;
	
	private ArrayList<String> retrieveFileNames(){
		File folder = new File("songmaps");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> l = new ArrayList<String>(listOfFiles.length);
		System.out.println("SongList.class folder.listFiles(): " + folder.listFiles());
		for(File file : listOfFiles) {
			if(file.isFile() && isUXM(file)){
				System.out.println("File " + file.getName() + " is an .uxm and was added to list.");
				l.add(file.getPath());
			} else if (file.isDirectory()) {
				System.out.println("Directory " + file.getName() + " was not added.");
			} else {
				System.out.println("File " + file.getName() + " was not added.");
			}
		}
		return l;
	}
	private static boolean isUXM(File file) {
		return FileReader.fileExtension(file).equalsIgnoreCase("uxm");
	}
	public SongList(){
		List<String> fList = retrieveFileNames();
		map = new HashMap<>();
		for(String s : fList) {
			String name;
			try {
				name = FileReader.readUXM(s).peek();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			map.put(name, s);
		}
	}
	public ISong getSong(String name) throws IOException {
		return new Song(map.get(name));
	}
	public Set<String> songs() {
		return map.keySet();
	}
}
