package com.mygdx.game.model;

/**
 * A class that holds global constants.
 */

public final class Constants {
	private Constants(){}
	//The virtual screen
	public static final float VIEWPORT_DIM_X = 340; //SNES
	public static final float VIEWPORT_DIM_Y = 244;
	/*
	public static final float VIEWPORT_DIM_X = 160; //Game Boy
	public static final float VIEWPORT_DIM_Y = 144;
	*/
	//The piano roll
	public static final float PIANOROLL_TOP_PADDING = 40;
	public static final float PIANOROLL_DIM_X = VIEWPORT_DIM_X;
	public static final float PIANOROLL_DIM_Y = VIEWPORT_DIM_Y - 2*PIANOROLL_TOP_PADDING;
	public static final float PIANOROLL_POS_X = 0;
	public static final float PIANOROLL_POS_Y = VIEWPORT_DIM_Y - PIANOROLL_DIM_Y - PIANOROLL_TOP_PADDING;
}
