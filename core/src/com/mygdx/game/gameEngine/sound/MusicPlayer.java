package com.mygdx.game.gameEngine.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * 
 * @author soflarb
 * Revised by car0b1nius
 *
 * Uses: IMusicPlayer
 * 
 * Used by: MainMenuScreen, TitleScreen
 */

public class MusicPlayer implements IMusicPlayer {
	//Singleton implementation
	private static final MusicPlayer INSTANCE = new MusicPlayer();
	private MusicPlayer() {
		Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/Unexpected_Magic_Theme_by_Isaskar.mp3"));
		mainTheme.setLooping(true);
		mainTheme.setVolume(0.5f);
		add("main-theme", mainTheme);
	}
	public static MusicPlayer getInstance() {
		return INSTANCE;
	}
	//private final Music mainMenu = Gdx.audio.newMusic(Gdx.files.internal("music/Unexpected_Magic_Theme_by_Isaskar.mp3"));
	private final Map<String, Music> songs = new HashMap<>();
	private Music song = null;
	private String songName = null;
	private void add(String name, Music music) {
		music.setLooping(true);
		music.setVolume(0.5f);
		songs.put(name, music);
	}
	private void setSong(String name) {
		if(name != null && name.equals(songName)) return;
		if(song != null) song.stop();
		Music s = songs.get(name);
		if(s == null) throw new IllegalArgumentException("Unknown song: " + name);
		songName = name;
		song = s;
	}
	@Override
	public void play(String name) {
		setSong(name);
		play();
	}
	@Override
	public void play() {
		if(!song.isPlaying()) song.play();
	}
	@Override
	public void pause() {
		song.pause();
	}
	@Override
	public void stop() {
		song.stop();
	}
	@Override
	public String playing() {
		return songName;
	}
	@Override
	public Set<String> songs() {
		return songs.keySet();
	}
	@Override
	public void dispose() {
		for(Map.Entry<String, Music> e : songs.entrySet()) {
			e.getValue().dispose();
		}
	}
}
