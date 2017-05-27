package com.mygdx.game.model.song;

/**
 * A note for use with the <code>javax.sound.midi</code> package.
 * @author car0b1nius
 * 
 * Uses: None
 * 
 * Used by: IVoice, Voice, EntityFactory, HitManager, SongPlayBack, Synth, ISynth
 * ITrackableNote, TrackableNote, Note
 */
public interface INote {
	int getPitch();
	int getDuration();
	default int getOctave() {
		return getPitch() % 12;
	}
	default boolean isRest() {
		return getPitch() == -1;
	}
}
