package com.mygdx.game.model.song;

/**
 * A collection of <code>INote</code>s.
 * @author car0b1nius
 * 
 * Uses: INote
 * 
 * Used by: Voice, Song, ISong, EntityFactory, HitManager, RoundManager, GameScreen, SongPlayback, IPlayer, Player, Ticker
 */
public interface IVoice {
	/**
	 * The note starting at the specified tick, or <code>null</code> if none.
	 * @param tick
	 * @return the note, or <code>null</code>
	 */
	INote noteAtTick(int tick);
	/**
	 * The octave of the closest note.
	 * @param tick
	 * @return the octave as an int between 0-11 (inclusive)
	 */
	int octaveAtTick(int tick);
	/**
	 * The pitch of the currently playing note.
	 * @param tick
	 * @return the pitch as an int between 0-127 (inclusive), or -1 if pause.
	 */
	int pitchAtTick(int tick);
	int length();
	int max();
	int min();
	@Deprecated
	int voiceNumber();
}
