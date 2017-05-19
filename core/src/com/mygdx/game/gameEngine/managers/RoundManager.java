package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.gameEngine.sound.Metronome;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.model.Ticker;
import com.badlogic.ashley.core.Engine;
import com.mygdx.game.gameEngine.systems.HitSystem;
import com.mygdx.game.model.Round;

/**
 * 
 * @author soflarb
 * Revised by Arvid
 *
 */

public class RoundManager {
	private Round round;
	private EntityManager entityManager;
	private Ticker ticker;
	private HitManager hitManager;
	private Synth synth;
	private Metronome metronome;

	public RoundManager(Round round, EntityManager entityManager, Ticker ticker, Engine engine){
		this.round = round;
		this.entityManager = entityManager;
		this.ticker = ticker;
		this.metronome = new Metronome(new Synth(),round.song.getBpm());
		this.metronome.start();
		initSynth();
		HitSystem hitSystem = new HitSystem();
		engine.addSystem(hitSystem);
		this.hitManager = new HitManager(round.getPlayers(), this.synth, hitSystem);
	}

	public void endRound(){
		this.metronome.stopMetronome();
	}
	
	public void notePlayStart(int lane){
		hitManager.notePlayStart(lane);
	}
	
	public void notePlayStop(int lane){
		hitManager.notePlayStop(lane);
	}
	
	public int getTick(){
		return ticker.getTick();
	}
	//returns true if game is over
	public boolean gameOver(){
		//TODO use ticker do not use position or anything
		//temporary this will end the game too early but it's a test:
		return (ticker.getTick() > 0 && !ticker.isTicking());
	}
	
	public void update(float delta){
		ticker.updateTick(delta);
		entityManager.update(getTick());
	}
	private void initSynth(){
		this.synth = new Synth();
		synth.setInstrument(1);
		synth.setSongTimeSignaure(round.song.getTime()[1],round.song.getBpm());
	}
}
