package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.services.file.Action;

/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{

	private SoundManager soundManager;
	
	public InputAction(){
		soundManager = new SoundManager();
	}
	
	@Override
	public void noteKeyPressed(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		soundManager.noteOn(note.noteValue());
		System.out.println(note.noteValue()); //TODO remove debug print
		
	}

	@Override
	public void noteKeyReleased(Action note) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		soundManager.noteOff(note.noteValue());
		// TODO Auto-generated method stub
		
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
