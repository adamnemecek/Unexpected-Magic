package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.utilities.file.Action;
import com.mygdx.game.utilities.file.Constants;


/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{
	
	private RoundManager roundManager;
	//SoundManager soundManager;
	private Hud hud;
	
	public InputAction(Hud hud, RoundManager roundManager){
		this.roundManager = roundManager;
		//soundManager = new SoundManager(); //TODO would make sense if this was created somewhere else
		this.hud = hud;
	}
	
	@Override
	public void noteKeyPressed(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		//soundManager.noteOn(note.noteValue());
		roundManager.notePlayStart(note.noteValue()%12);
		hud.activateLane(note.noteValue()%12);
		
	}

	@Override
	public void noteKeyReleased(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		roundManager.notePlayStop(note.noteValue()%12);
		//soundManager.noteOff(note.noteValue());
		hud.deactivateLane(note.noteValue()%12);
		
		
	}

	@Override
	public void uiKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uiKeyReleased() {
		// TODO Auto-generated method stub
		
	}
	


}
