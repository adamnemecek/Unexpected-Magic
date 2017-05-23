package com.mygdx.game.model;

import com.mygdx.game.model.song.INote;
/**
 * Interface for notes with non-static fields
 * @author Arvid
 *
 */

public interface ITrackableNote extends INote {
	boolean isHit();
	
	void hit();

}
