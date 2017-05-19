package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.RoundManager;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.utilities.file.Action;


/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{
	
	private RoundManager roundManager;
	private Hud hud;
	
	public InputAction(Hud hud, RoundManager roundManager){
		this.roundManager = roundManager;
		this.hud = hud;
	}
	
	@Override
	public void noteKeyPressed(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		roundManager.notePlayStart(note.noteValue()%12);
		hud.activateLane(note.noteValue()%12);
		
	}

	@Override
	public void noteKeyReleased(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		roundManager.notePlayStop(note.noteValue()%12);
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
