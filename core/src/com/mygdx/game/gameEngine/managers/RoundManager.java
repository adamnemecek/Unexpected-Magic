package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.gameEngine.sound.Metronome;
import com.mygdx.game.gameEngine.sound.SongPlayback;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * 
 * @author soflarb
 * Revised by Arvid
 *
 */

public class RoundManager {
	private Round round;
	private HitManager hitManager;
	private Synth synth;
	private Metronome metronome;


	public RoundManager(Round round, List<IVoice> nonPlayerVoices){
		this.round = round;
		this.metronome = new Metronome(round.song.getTime()[1]);
		initSynth();

		this.hitManager = new HitManager(round.getPlayers(), this.synth);

		SongPlayback.setSong(this.synth,nonPlayerVoices);
	}

	public void notePlayStart(int lane){
		hitManager.notePlayStart(lane);
	}
	
	public void notePlayStop(int lane){
		hitManager.notePlayStop(lane);
	}

	private void initSynth(){
		this.synth = new Synth();
		synth.setInstrument(1);
		synth.setSongTimeSignaure(round.song.getTime()[1],round.song.getBpm());
	}
}
