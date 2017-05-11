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
		Action action = ConfigService.keyAction(keyCode);
		System.out.println(action); //TODO remove debug print
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyPressed(action);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
	}
	public void keyReleased(int keyCode) {
		Action action = ConfigService.keyAction(keyCode);
		System.out.println(action); //TODO remove debug print
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyReleased(action);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
		
	}
}