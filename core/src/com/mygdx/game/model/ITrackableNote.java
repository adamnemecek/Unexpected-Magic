package com.mygdx.game.model;

import com.mygdx.game.model.song.INote;
/**
 * Interface for notes with non-static fields
 * @author rarvid
 * 
 * Uses: INote
 * 
 * Used by: TrackableNote, HitManager
 */

public interface ITrackableNote extends INote {
	boolean isHit();
	
	void hit();
}
