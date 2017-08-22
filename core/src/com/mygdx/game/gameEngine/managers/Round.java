package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.gameEngine.sound.Metronome;
import com.mygdx.game.gameEngine.sound.SongPlayback;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.gameEngine.sound.ISynth;
import com.mygdx.game.model.RoundData;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.IVoice;

import java.util.List;

/**
 * Class that instantiates the classes needed to play a round
 * @author soflarb
 * Revised by rarvid, rastom, car0b1nius
 * 
 * Uses: ISynth, Synth, Metronome, SongPlayback, Round, Ticker, ISong, IVoice, HitManager
 * Used by: InputAction, GameScreen
 */

public class Round {
	private RoundData round;
	private HitManager hitManager;
	private ISynth synth;
	private Ticker ticker;


	public Round(RoundData round, List<IVoice> nonPlayerVoices, Ticker ticker){
		this.round = round;
		this.ticker = ticker;
		new Metronome(round.song.getTime()[1]);
		initSynth();
		this.hitManager = new HitManager(round.getPlayers(), this.synth);
		new SongPlayback(this.synth,nonPlayerVoices);
	}
	// tells hitManager that a note key was pressed
	public void notePlayStart(int lane,int player){
		hitManager.notePlayStart(lane, player);
	}
	//tells hitManager that a note key stopped being presssed
	public void notePlayStop(int lane, int player){
		hitManager.notePlayStop(lane, player);
	}
	//creates a Synth
	private void initSynth(){
		this.synth = new Synth();
		synth.setInstrument(1);
		synth.setSongTimeSignaure(round.song.getTime()[1],round.song.getBpm());
	}
	//tells ticker that the pause key was pressed
	public void pauseGame(){
		this.ticker.togglePauseTicker();
	}
}
