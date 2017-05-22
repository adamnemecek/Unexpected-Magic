package com.mygdx.game.gameEngine.input;

import com.mygdx.game.utilities.file.Action;

/**
 * Interface for a Inputcontroller .
 * @author rastom
 */

public interface IInputController {

	void noteKeyPressed(Action note);
	
	void noteKeyReleased(Action note);

	void uiKeyPressed();
	
	void uiKeyReleased();
}
