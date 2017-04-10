package com.mygdx.game.services.file;

import java.io.File;
import java.util.ArrayList;

public class SongList {
	
	ArrayList<File> list;

	public SongList(){
		retrieveFileNames();
	}
	
	private ArrayList<File> retrieveFileNames(){
		ArrayList<File> l = new ArrayList<File>();
		File folder = new File("assets/songmaps");
		File[] listOfFiles = folder.listFiles();
		
			for(int i = 0; i < listOfFiles.length; i++){
				if(listOfFiles[i].isFile()){
					System.out.println("File " + listOfFiles[i].getName() + " was added to list.");
					l.add(listOfFiles[i]);
					
				}else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName() + " was not added.");
				}
			}
		return l;
	}
}