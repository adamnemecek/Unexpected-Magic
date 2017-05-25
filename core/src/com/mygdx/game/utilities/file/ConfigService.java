package com.mygdx.game.utilities.file;

import java.util.Random;

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
		int playerIndex;
		//works on mac
		switch (keyCode) {
			case (131):
				action = Action.PAUSE_GAME; //Esc
				
			case (59):
				action = Action.NOTE_C; //left shift
			case (68):
				action = Action.NOTE_C_SHARP; //<
			case (54):
				action = Action.NOTE_D; //z
			case (52):
				action = Action.NOTE_D_SHARP; //x
			case (31):
				action = Action.NOTE_E; //c
			case (50):
				action = Action.NOTE_F; //v
			case (30):
				action = Action.NOTE_F_SHARP; //b
			case (42):
				action = Action.NOTE_G; //n
			case (41):
				action = Action.NOTE_G_SHARP; //m
			case (55):
				action = Action.NOTE_A; //,
			case (56):
				action = Action.NOTE_A_SHARP; //.
			case (76):
				action = Action.NOTE_B; //-
			default:
				action = null;
				return new ActionObject(new Random().nextInt(10), action); //TODO different players
		}
	}
	public static class ActionObject{
		public final int playerIndex;
		public final Action action;
		private ActionObject(int playerIndex, Action action){
			this.playerIndex = playerIndex;
			this.action = action;
		}
	}
}
