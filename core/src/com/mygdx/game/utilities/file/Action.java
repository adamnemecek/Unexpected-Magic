package com.mygdx.game.utilities.file;

/**
 * Enum for different input actions.
 * @author rastom
 */

public enum Action {
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
		if(!isNote()) return -1;
		return noteValueInOctave(5);
	}
	public int noteValueInOctave(int oct) {
		if(!isNote()) return -1;
		int ret = value + oct * 12;
		if(ret < 0 || 127 < ret) throw new IllegalArgumentException();
		return ret;
	}
}
