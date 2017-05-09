package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.SoundManager;

/**
 * Created by rasmus on 2017-05-09.
 */
public class InputAction implements IInputController{

	private SoundManager soundManager;

	@Override
	public void noteKeyPressed(NoteEnum note) {
			soundManager.noteOn(note.defaultValue());
	}

	@Override
	public void menuKeyPressed() {

	}
}
