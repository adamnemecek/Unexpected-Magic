package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * Class for playing non-player voices
 * @author rastom
 * Revised by car0b1nius
 * 
 * Uses: ISynth, ObserverHandler, TickListener, INote, IVoice
 * 
 * Used by: RoundManager
 */
public class SongPlayback implements TickListener{

	private final ISynth synth;
	private final List<IVoice> voices;

	public SongPlayback(ISynth synth, List<IVoice> v){
		this.synth = synth;
		voices = v;
		ObserverHandler.addTickListener(this);
	}

	@Override
	public void updateTick(int tick) {
		for(IVoice v : voices){
			INote note = v.noteAtTick(tick);
			if(note == null) continue;
			synth.play(note);
		}
	}
}
