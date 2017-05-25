package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.gameEngine.sound.Metronome;
import com.mygdx.game.gameEngine.sound.SongPlayback;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.gameEngine.sound.ISynth;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.Ticker;
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
	private Ticker ticker;


	public RoundManager(Round round, List<IVoice> nonPlayerVoices, Ticker ticker){
		this.round = round;
		this.ticker = ticker;
		new Metronome(round.song.getTime()[1]);
		initSynth();
		this.hitManager = new HitManager(round.getPlayers(), this.synth);
		new SongPlayback(this.synth,nonPlayerVoices);
	}

	public void notePlayStart(int lane,int player){
		if(round.players.size() > 0) //TODO remove when multiplayer is properly implemented
		hitManager.notePlayStart(lane, player);
	}
	
	public void notePlayStop(int lane, int player){
		if(round.players.size() > 0) //TODO remove when multiplayer is properly implemented
		hitManager.notePlayStop(lane, player);
	}

	private void initSynth(){
		this.synth = new Synth();
		synth.setInstrument(1);
		synth.setSongTimeSignaure(round.song.getTime()[1],round.song.getBpm());
	}

	public void pauseGame(){
		this.ticker.togglePauseTicker();
	}
}
