package com.mygdx.game.services.file;

public class ConfigService {
	public static Action keyAction(int keyCode){
		// I really would prefer to use Keys.ESCAPE and such, but that causes "circular dependencies" and "isn't good structure".
		// This whole project structure thing forces us to reimplement half the things GDX can do a lot better than we can.
		System.out.println(keyCode);
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
		return null;
	}
}
