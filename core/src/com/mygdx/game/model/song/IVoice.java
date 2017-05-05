package com.mygdx.game.model.song;

/**
 * A collection of <code>INote</code>s.
 * @author car0b1nius
 */
public interface IVoice {
	INote noteAtTick(int tick);
	int length();
	int max();
	int min();
}
