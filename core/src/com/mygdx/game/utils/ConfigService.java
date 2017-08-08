package com.mygdx.game.utils;

/**
 * Class that handles action to key mapping.
 * @author car0b1nius
 * Revised by rastom, soflarb
 * 
 * Uses: None
 * 
 * Used by: IInputController, InputAction, KeyboardControllerAdapter
 */
public class ConfigService {
	public static ActionObject keyAction(int keyCode) {
		// I really would prefer to use Keys.ESCAPE and such, but that causes "circular dependencies" and "isn't good structure".
		// This whole project structure thing forces us to reimplement half the things GDX can do a lot better than we can.
		/*
		if(keyCode == 131) return Action.PAUSE_GAME; //Esc
		if(keyCode == 54) return Action.NOTE_C; //Z
		if(keyCode == 47) return Action.NOTE_C_SHARP; //S
		if(keyCode == 52) return Action.NOTE_D; //X
		if(keyCode == 32) return Action.NOTE_D_SHARP; //D
		if(keyCode == 31) return Action.NOTE_E; //C
		if(keyCode == 50) return Action.NOTE_F; //V
		if(keyCode == 35) return Action.NOTE_F_SHARP; //G
		if(keyCode == 30) return Action.NOTE_G; //B
		if(keyCode == 36) return Action.NOTE_G_SHARP; //H
		if(keyCode == 42) return Action.NOTE_A; //N
		if(keyCode == 38) return Action.NOTE_A_SHARP; //J
		if(keyCode == 41) return Action.NOTE_B; //M
		*/
		Action action;
		int playerIndex = 0; //TODO implement multiplayer and give each player separate controls
		//works on mac
		switch (keyCode) {
			case 131:
				action =  Action.PAUSE_GAME; //Esc
				break;
			case 59:
				action = Action.NOTE_C; //left shift
				break;
			case 68:
				action = Action.NOTE_C_SHARP; //<
				break;
			case 54:
				action = Action.NOTE_D; //z
				break;
			case 52:
				action = Action.NOTE_D_SHARP; //x
				break;
			case 31:
				action = Action.NOTE_E; //c
				break;
			case 50:
				action = Action.NOTE_F; //v
				break;
			case 30:
				action = Action.NOTE_F_SHARP; //b
				break;
			case 42:
				action = Action.NOTE_G; //n
				break;
			case 41:
				action = Action.NOTE_G_SHARP; //m
				break;
			case 55:
				action = Action.NOTE_A; //,
				break;
			case 56:
				action = Action.NOTE_A_SHARP; //.
				break;
			case 76:
				action = Action.NOTE_B; //-
				break;
			default:
				action = null;
		}
				return new ActionObject(playerIndex, action);
	}
	
	/**
	 * Enum for different input actions.
	 * @author car0b1nius
	 * Revised by rastom, soflarb
	 */

	public static enum Action {
		NOTE_C (0),
		NOTE_C_SHARP (1),
		NOTE_D (2),
		NOTE_D_SHARP (3),
		NOTE_E (4),
		NOTE_F (5),
		NOTE_F_SHARP (6),
		NOTE_G (7),
		NOTE_G_SHARP (8),
		NOTE_A (9),
		NOTE_A_SHARP (10),
		NOTE_B (11),
		PAUSE_GAME (12);
		
		private static final int DEFAULT_OCTAVE = 5;
		private static final int NOTES_IN_OCTAVE = 12;
		private static final int MIN_NOTE_VALUE = 0;
		private static final int MAX_NOTE_VALUE = 127;
		private static final int PAUSE_VALUE = -1;
		private final int value;
		Action(int v){
			value = v;
		}
		public boolean isNote() {
			return
				this == NOTE_C ||
				this == NOTE_C_SHARP ||
				this == NOTE_D ||
				this == NOTE_D_SHARP ||
				this == NOTE_E ||
				this == NOTE_F ||
				this == NOTE_F_SHARP ||
				this == NOTE_G ||
				this == NOTE_G_SHARP ||
				this == NOTE_A ||
				this == NOTE_A_SHARP ||
				this == NOTE_B;
		}
		public int noteValue() {
			if(!isNote()) return PAUSE_VALUE;
			return noteValueInOctave(DEFAULT_OCTAVE);
		}
		public int noteValueInOctave(int oct) {
			if(!isNote()) return PAUSE_VALUE;
			int ret = value + oct * NOTES_IN_OCTAVE;
			if(ret < MIN_NOTE_VALUE || MAX_NOTE_VALUE < ret) throw new IllegalArgumentException();
			return ret;
		}
	}
	/**
	 * Class for packaging player action information.
	 * @author soflarb
	 * Revised by rastom, car0b1nius
	 */
	public static class ActionObject{
		public final int playerIndex;
		public final Action action;
		private ActionObject(int playerIndex, Action action){
			this.playerIndex = playerIndex;
			this.action = action;
		}
		public boolean hasPlayer(){
			return playerIndex >= 0;
		}
	}
}
