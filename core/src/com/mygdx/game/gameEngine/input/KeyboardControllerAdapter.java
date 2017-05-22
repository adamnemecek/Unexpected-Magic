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
		Action action = ConfigService.keyAction(keyCode);
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyPressed(action);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
	}
	public void keyReleased(int keyCode) {
		Action action = ConfigService.keyAction(keyCode);
		if(action == null) return;
		if(action.isNote()) inputAction.noteKeyReleased(action);
		else if(action == Action.PAUSE_GAME); // TODO something with pause
		
	}
}