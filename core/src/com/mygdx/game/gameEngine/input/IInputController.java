package com.mygdx.game.gameEngine.input;

import com.mygdx.game.utilities.file.Action;

/**
 * Interface for a Inputcontroller .
 * @author rastom
 */

public interface IInputController {

	void noteKeyPressed(Action note, int playerIndex);
	
	void noteKeyReleased(Action note, int playerIndex);

	void uiKeyPressed();
	
	void uiKeyReleased();
}
