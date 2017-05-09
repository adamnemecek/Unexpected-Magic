package com.mygdx.game.gameEngine.input;

/**
 * Created by rasmus on 2017-05-09.
 */
public enum NoteEnum {
	C (80),
	C_SHARP (81),
	D (82),
	D_SHARP (83),
	E (84),
	F (85),
	F_SHARP (86),
	G (87),
	G_SHARP (88),
	A (89),
	A_SHARP (90),
	B (91);

	private final int value;
	NoteEnum(int v){
		value = v;
	}

	public int defaultValue() {
		return value;
	}
}
