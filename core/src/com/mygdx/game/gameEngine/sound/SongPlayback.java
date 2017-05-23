package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * @author rastom
 * class for playing non-player voices
 */
public class SongPlayback {

	private final Synth synth;
	private final List<IVoice> voices;

	public SongPlayback(Synth synth, List<IVoice> voices){
		this.synth = synth;
		this.voices = voices;
	}

	public void update(int tick){
		for(IVoice v : voices){
			INote note = v.noteAtTick(tick);
			if(note == null) continue;
			this.synth.play(note);
		}
	}
}
