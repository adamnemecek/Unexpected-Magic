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
		soundManager.noteOn(note.noteValue());
		
	}

	@Override
	public void noteKeyReleased(Action note) {
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
