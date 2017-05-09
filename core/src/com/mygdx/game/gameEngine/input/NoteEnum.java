package com.mygdx.game.gameEngine.input;

/**
 * Created by rasmus on 2017-05-09.
 */
public enum NoteEnum {
	C (0),
	C_SHARP (1),
	D (2),
	D_SHARP (3),
	E (4),
	F (5),
	F_SHARP (6),
	G (7),
	G_SHARP (8),
	A (9),
	A_SHARP (10),
	B (11);

	private final int value;
	NoteEnum(int v){
		value = v;
	}

	public int defaultValue() {
		return valueInOctave(5);
	}
	public int valueInOctave(int oct) {
		int ret = value + oct * 12;
		if(ret < 0 || 127 < ret) throw new IllegalArgumentException();
		return ret;
	}
}
