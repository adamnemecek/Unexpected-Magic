package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * @author rastom
 * class for playing non-player voices
 */
public class SongPlayback implements TickListener{

	private final Synth synth;
	private final List<IVoice> voices;

	public SongPlayback(Synth s, List<IVoice> v){
		synth = s;
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
