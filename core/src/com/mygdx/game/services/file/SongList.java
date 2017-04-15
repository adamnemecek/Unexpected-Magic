package com.mygdx.game.services.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mygdx.game.model.song.Song;

public class SongList {
	
	ArrayList<String> list;

	public SongList(){
		list = new ArrayList<>();
		list = retrieveFileNames();
	}
	
	private ArrayList<String> retrieveFileNames(){
		ArrayList<String> l = new ArrayList<String>();
		File folder = new File("songmaps");
		File[] listOfFiles = folder.listFiles();
		System.out.println("SongList.class folder.listFiles(): " + folder.listFiles());
		
			for(int i = 0; i < listOfFiles.length; i++){
				if(listOfFiles[i].isFile() && isUXM(listOfFiles[i])){
					System.out.println("File " + listOfFiles[i].getName() + " is an .uxm and was added to list.");
					l.add(listOfFiles[i].getName());
					
				}else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName() + " was not added.");
				}
			}
		return l;
	}
	private String readFile(String fileName) throws FileNotFoundException{
		System.out.println(System.getProperty("user.dir"));
		String s;
		{
			System.out.println("SongList. assets/songmaps/"+fileName);
			Scanner sc = new Scanner(new File("songmaps/"+fileName)).useDelimiter("\\Z");
			s = sc.next();
			sc.close();
		}
		return s;
	}
	public Song retrieveSongFromIndex(int index) throws FileNotFoundException, IOException{
		Song r = new Song(readFile(list.get(index)));
		return r;
	}
	
	private boolean isUXM(File file){
		if(file.getName().length() < 4){return false;}
		String fileExtension = file.getName().substring(file.getName().length()-4);
		return fileExtension.equalsIgnoreCase(".uxm");
	}
	
	public ArrayList<String> getList(){
		return list;
	}
}