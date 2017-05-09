package com.mygdx.game.gameEngine.input;

/**
 * Created by rasmus on 2017-05-09.
 */
public class KeyboardControllerAdapter {
	private InputAction inputAction;
	public KeyboardControllerAdapter(){
		inputAction = new InputAction();
	}
	@Override
	public void noteKeyPressed(int keyCode) {
		//InputConfig.getKeyAction
	}

	@Override
	public void menuKeyPressed() {

	}
}
