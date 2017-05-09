package com.mygdx.game.gameEngine.input;

/**
 * Created by rasmus on 2017-05-09.
 */
public interface IInputController {

	void noteKeyPressed(NoteEnum note);
	
	void noteKeyReleased(NoteEnum note);

	void uiKeyPressed();
	
	void uiKeyReleased();
}
