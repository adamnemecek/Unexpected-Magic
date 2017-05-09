package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.SoundManager;

/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{

	@Override
	public void noteKeyPressed(NoteEnum note) {
		soundManager.noteOn(note.defaultValue());
		
	}

	@Override
	public void noteKeyReleased(NoteEnum note) {
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
