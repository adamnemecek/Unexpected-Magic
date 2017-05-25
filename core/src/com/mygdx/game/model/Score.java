package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

public class Score implements IScore{

	/**
	 * A class that holds score.
	 * Revised by Arunvik
	 * Primitive, mostly made for me to test testing
	 */

	//TODO if score is only part of model should it calculate things?
	private double score;
	private int streak;
	private int bestStreak;
	private int multiplier; //higher score if higher streak¨
	private final ListenerHandler listenerHandler;

	public Score(){
		score = 0;
		streak = 0;
		bestStreak = 0;
		multiplier = 1; //TODO this could perhaps work
		listenerHandler = new ListenerHandler();
		
	}
	//first draft, score calculation? Keep track of percentage of note hit?
	public void hitNote(boolean previouslyHit){
		
		if(!previouslyHit){
			streak++;
			if (streak > bestStreak){
				bestStreak = streak;
			}
			if (multiplier < 10){
				multiplier = 1 + streak/10;
			}
		}
		score += 0.3*multiplier;
		listenerHandler.NotifyListeners((int)score);
	}


	public void missedNote(){
		streak=0;
		multiplier = 1;
	}
	
	public void wrongNote(){
		if (score > 0){
			score -= 0.2;
			listenerHandler.NotifyListeners((int)score);
		}
		
	}
	public int getMultiplier(){
		return multiplier;
	}

	public int getStreak(){
		return streak;
	}
	public int getScore(){
		return (int)Math.round(score);
	}
	public void addListener(ScoreListener sl){
		listenerHandler.addListener(sl);
	}
	public void removeListener(ScoreListener sl){
		listenerHandler.removeListener(sl);
	}
	
	private class ListenerHandler{
		private  final List<ScoreListener> listeners;
		public ListenerHandler(){
			listeners = new ArrayList<>();
		}
		public void addListener(ScoreListener sl){
			if (!listeners.contains(sl)){
				listeners.add(sl);
			}
		}
		public void removeListener(ScoreListener sl){
			listeners.remove(sl);
		}
		public void NotifyListeners(int newScore){
			for (ScoreListener sl : listeners){
				sl.newScore(newScore);
			}
		}
	}

}

