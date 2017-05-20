package com.mygdx.game.gameEngine.managers;

import java.util.Set;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public interface IMusicPlayer extends Disposable {
	//void add(String name, Music music);
	void play(String name);
	void play();
	void pause();
	void stop();
	String playing();
	Set<String> songs();
}
