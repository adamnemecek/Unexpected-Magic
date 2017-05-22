package com.mygdx.game.model;

import com.mygdx.game.utilities.file.Constants;

/**
 * A class that represents all the note lanes
 * @author rastom
 */

public class NoteLanes {

	private boolean[] laneStates; //false = inactive, true = active

    public NoteLanes(){

        laneStates = new boolean[Constants.NUMBER_OF_LANES];

        //init all lanes inactive
        for(int i = 0; i < Constants.NUMBER_OF_LANES; i++){
            this.laneStates[i] = false;
        }

    }



    public boolean getLaneState(int i){
	    return this.laneStates[i];
    }

    public void activateLane(int i){
	    this.laneStates[i] = true;
    }

    public void deactivateLane(int i){
        this.laneStates[i] = false;
    }
}
