package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.model.Constants;
import com.mygdx.game.services.file.Action;


/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{

	private SoundManager soundManager;
	private RoundManager roundManager;

	
	public InputAction(RoundManager rm){
		soundManager = new SoundManager(); //TODO would make sense if this was created somewhere else
		roundManager =rm;
	}
	
	@Override
	public void noteKeyPressed(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		soundManager.noteOn(note.noteValue());
		System.out.println(note.noteValue()); //TODO remove debug print
		roundManager.activateLane(note.noteValue()%12);
		
	}

	@Override
	public void noteKeyReleased(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		soundManager.noteOff(note.noteValue());
		roundManager.deactivateLane(note.noteValue()%12);
		
		
	}

	@Override
	public void uiKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uiKeyReleased() {
		// TODO Auto-generated method stub
		
	}
	
	/*private int getNoteLane(Action note){
		switch(note){
		
		case NOTE_C: return 0;
		
		case NOTE_C_SHARP: return 1;
		
		case NOTE_D: return 2;
		
		case NOTE_D_SHARP: return 3;
		
		case NOTE_E: return 4;
		
		case NOTE_F: return 5;
		
		case NOTE_F_SHARP: return 6;
		
		case NOTE_G: return 7;
		
		case NOTE_G_SHARP: return 8;
		
		case NOTE_A: return 9;
		
		case NOTE_A_SHARP: return 10;
		
		case NOTE_B: return 11;
		
		default: return 0;
		}
	}*/

}
