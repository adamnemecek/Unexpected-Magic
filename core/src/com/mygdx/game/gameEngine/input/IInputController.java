package com.mygdx.game.gameEngine.input;

import com.mygdx.game.utilities.file.Action;

/**
 * Created by rasmus on 2017-05-09.
 */
public interface IInputController {

	void noteKeyPressed(Action note);
	
	void noteKeyReleased(Action note);

	void uiKeyPressed();
	
	void uiKeyReleased();
}
