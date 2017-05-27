package com.mygdx.game.gameEngine.input;
import com.mygdx.game.utils.ConfigService.Action;
import com.mygdx.game.utils.ConfigService;

/**
 * Class for translating input to action, adapter layer.
 * @author rastom
 * Revised by car0b1nius, soflarb
 * 
 * Uses: ConfigService, Configservice.Action, ConfigService.ActionObject, InputAction
 * Used by: KeyboardInputManager, GameScreen
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
		if(action.isNote() && actionObject.hasPlayer()) inputAction.noteKeyPressed(action, playerIndex);
		else if(action == Action.PAUSE_GAME){
			inputAction.uiKeyPressed(Action.PAUSE_GAME);
		}
		else{System.out.println("Error in keyPressed(), could not call call inputAction properly.");}
	}
	public void keyReleased(int keyCode) {
		ConfigService.ActionObject actionObject = ConfigService.keyAction(keyCode);
		Action action = actionObject.action;
		int playerIndex = actionObject.playerIndex;
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyReleased(action,playerIndex);
		else if(action == Action.PAUSE_GAME){
			inputAction.uiKeyReleased(Action.PAUSE_GAME);
		}
		
	}
}