package com.mygdx.game.model;

import com.mygdx.game.utils.Constants;

/**
 * A class that represents all the note lanes
 * @author rastom
 * 
 * Uses: None
 * 
 * Used by: Hud
 */

public class NoteLanes {

	private NoteLanes(){
	}

	private static boolean[] laneStates = new boolean[Constants.NUMBER_OF_LANES]; //false = inactive, true = active

	public static boolean getLaneState(int i){
		return laneStates[i];
	}

	public static void activateLane(int i){
		laneStates[i] = true;
	}

	public static void deactivateLane(int i){
		laneStates[i] = false;
	}
}
