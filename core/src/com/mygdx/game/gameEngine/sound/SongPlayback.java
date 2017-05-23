package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * @author rastom
 * class for playing non-player voices
 */
public class SongPlayback {

	private static Synth synth;
	private static List<IVoice> voices;
	static {
		ObserverHandler.addTickListener(
				(int tick) -> {

					for(IVoice v : voices){
						INote note = v.noteAtTick(tick);
						if(note == null) continue;
						synth.play(note);
					}
				}
		);
	}

	public static void setSong(Synth s, List<IVoice> v){
		synth = s;
		voices = v;
	}
}
