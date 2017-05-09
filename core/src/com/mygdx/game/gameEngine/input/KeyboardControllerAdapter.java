package com.mygdx.game.gameEngine.input;

import com.mygdx.game.services.file.ConfigService;

/**
 * Created by rasmus on 2017-05-09.
 */
public class KeyboardControllerAdapter {
	private InputAction inputAction;
	public KeyboardControllerAdapter(){
		inputAction = new InputAction();
	}
	public void keyPressed(int keyCode) {
		ConfigService.keyAction(keyCode);
		inputAction.noteKeyPressed(NoteEnum.C);
	}
	public void keyReleased(int keyCode) {
		
	}
}