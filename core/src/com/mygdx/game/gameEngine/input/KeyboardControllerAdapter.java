package com.mygdx.game.gameEngine.input;
import com.mygdx.game.utilities.file.Action;
import com.mygdx.game.utilities.file.ConfigService;

/**
 * Class for Input, adapter layer.
 * @author rastom
 */

public class KeyboardControllerAdapter {
	private InputAction inputAction;
	public KeyboardControllerAdapter(InputAction inputAction){
		this.inputAction = inputAction;
	}
	public void keyPressed(int keyCode) {
		ConfigService.ActionObject actionObject = ConfigService.keyAction(keyCode);
		Action action = actionObject.action;
		int playerIndex = actionObject.playerIndex;
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyPressed(action, playerIndex);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
	}
	public void keyReleased(int keyCode) {
		ConfigService.ActionObject actionObject = ConfigService.keyAction(keyCode);
		Action action = actionObject.action;
		int playerIndex = actionObject.playerIndex;
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyReleased(action,playerIndex);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
		
	}
}