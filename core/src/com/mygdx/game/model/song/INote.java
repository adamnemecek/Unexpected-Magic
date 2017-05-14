package com.mygdx.game.model.song;

/**
 * A note for use with the <code>javax.sound.midi</code> package.
 * @author car0b1nius
 */
public interface INote {
	int getPitch();
	int getDuration();
	default boolean isRest() {
		return getPitch() == -1;
	}
}
