package com.mygdx.game.Observers;

/**
 * Interface for objects that listen to ticks
 * @author rarvid
 * 
 * Uses: None
 * 
 * Used By: HitManager, Metronome, SongPlayback, ObserverHandler
 */

public interface TickListener {

	void updateTick(int tick);

}
