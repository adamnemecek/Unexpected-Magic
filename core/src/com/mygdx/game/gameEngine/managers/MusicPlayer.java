package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer implements IMusicPlayer{
	private MusicPlayer() {}
	
	private final Music mainMenu = Gdx.audio.newMusic(Gdx.files.internal("music/Unexpected_Magic_Theme_by_Isaskar.mp3"));

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
