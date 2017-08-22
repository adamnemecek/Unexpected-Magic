package com.mygdx.game.gameEngine.input;

import com.mygdx.game.gameEngine.managers.Round;
import com.mygdx.game.gameEngine.scenes.Hud;
import com.mygdx.game.utils.ConfigService.Action;

/**
 * Class for handling input, the last layer in the process of handling input, actually does stuff.
 * @author rastom
 * revised by soflarb, car0b1nius, rarvid
 * 
 * Uses: Hud, RoundManager, IInputController, ConfigService
 * Used by: GameScreen, KeyboardControllerAdapter
 */

public class InputAction implements IInputController{
	
	private Round roundManager;
	private Hud hud;
	
	public InputAction(Hud hud, Round roundManager){
		this.roundManager = roundManager;
		this.hud = hud;
	}
	
	@Override
	public void noteKeyPressed(Action note, int playerIndex) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		roundManager.notePlayStart(note.noteValue()%12, playerIndex);
		hud.activateLane(note.noteValue()%12);
		
	}

	@Override
	public void noteKeyReleased(Action note, int playerIndex) {
		if(!note.isNote()) throw new IllegalArgumentException("Cannot play non-note action");
		roundManager.notePlayStop(note.noteValue()%12, playerIndex);
		hud.deactivateLane(note.noteValue()%12);
		
		
	}


	@Override
	public void uiKeyPressed(Action action) {
		if(action == Action.PAUSE_GAME){
			roundManager.pauseGame();
		}
	}

	@Override
	public void uiKeyReleased(Action action) {
		if(action == Action.PAUSE_GAME){
			//NOTHING NOTHING NOTHING
		}
	}
	


}
