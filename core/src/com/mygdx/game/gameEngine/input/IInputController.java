package com.mygdx.game.gameEngine.input;

import com.mygdx.game.utils.ConfigService.Action;

/**
 * Interface for an Inputcontroller.
 * @author rastom
 */

public interface IInputController {

	void noteKeyPressed(Action note, int playerIndex);
	
	void noteKeyReleased(Action note, int playerIndex);

	void uiKeyPressed(Action action);
	
	void uiKeyReleased(Action action);

}
