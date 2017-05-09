package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.song.Note;

/**
 * A class that represents all the note lanes
 */

public class NoteLanes {

	private boolean[] laneStates; //false = inactive, true = active
    private Texture activeTexture;
    private Texture inactiveTexture;

    public NoteLanes(){

        laneStates = new boolean[Constants.NUMBER_OF_LANES];

        //init all lanes inactive
        for(int i = 0; i < Constants.NUMBER_OF_LANES; i++){
            this.laneStates[i] = false;
        }

        this.activeTexture = new Texture("images/lanes/Cyan.png");
        this.inactiveTexture = new Texture("images/lanes/Pink.png");
    }

	public Texture getLaneTexture(int i){
	    if(laneStates[i]){
	        return activeTexture;
        }
        else return inactiveTexture;
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
