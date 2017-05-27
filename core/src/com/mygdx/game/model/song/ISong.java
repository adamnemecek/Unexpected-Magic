package com.mygdx.game.model.song;

/**
 * A collection of <code>IVoice</code>s.
 * @author car0b1nius
 * 
 * Uses: IVoice
 * 
 * Used by: Song, EntityFactory, RoundManager, PianoRoll, GameScreen, ScoreScreen, Round, SongList, Ticker, TickerTest
 */
public interface ISong {
	int length();
	String getTitle();
	int getBpm();
	IVoice[] getVoices();
	int[] getTime();
}
