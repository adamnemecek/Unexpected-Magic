package com.mygdx.game.gameEngine.sound;

import java.util.Set;
import com.badlogic.gdx.utils.Disposable;

/**
 * 
 * @author car0b1nius
 * 
 * Uses: none
 * 
 * Used By: MusicPlayer
 *
 */
public interface IMusicPlayer extends Disposable {
	//void add(String name, Music music);
	void play(String name);
	void play();
	void pause();
	void stop();
	String playing();
	Set<String> songs();
}
