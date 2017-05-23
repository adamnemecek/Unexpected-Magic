package com.mygdx.game.model;

import com.mygdx.game.utilities.file.Constants;

/**
 * A class that represents all the note lanes
 * @author rastom
 */

public class NoteLanes {

	private static boolean[] laneStates = new boolean[Constants.NUMBER_OF_LANES]; //false = inactive, true = active

	public static int xCoordinate(int lane, int playerIndex){
		return (int) (lane*Constants.LANE_WIDTH+(Constants.LANE_WIDTH/4)*playerIndex);
	}
	
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
