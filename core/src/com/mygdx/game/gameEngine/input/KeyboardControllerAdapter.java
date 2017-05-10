package com.mygdx.game.gameEngine.input;

import com.mygdx.game.services.file.ConfigService;
import com.mygdx.game.services.file.Action;

/**
 * Created by rasmus on 2017-05-09.
 */
public class KeyboardControllerAdapter {
	private InputAction inputAction;
	public KeyboardControllerAdapter(){
		inputAction = new InputAction();
	}
	public void keyPressed(int keyCode) {
		int action = ConfigService.keyAction(keyCode);
		switch(action) {
			case ConfigService.NOTE_C:
				break;
			case ConfigService.NOTE_C_SHARP:
				break;
			case ConfigService.NOTE_D:
				break;
			case ConfigService.NOTE_D_SHARP:
				break;
			case ConfigService.NOTE_E:
				break;
			case ConfigService.NOTE_F:
				break;
			case ConfigService.NOTE_F_SHARP:
				break;
			case ConfigService.NOTE_G:
				break;
			case ConfigService.NOTE_G_SHARP:
				break;
			case ConfigService.NOTE_A:
				break;
			case ConfigService.NOTE_A_SHARP:
				break;
			case ConfigService.NOTE_B:
				break;
			case ConfigService.PAUSE:
				break;
				
		}
		inputAction.noteKeyPressed(Action.C);
	}
	public void keyReleased(int keyCode) {
		
	}
}