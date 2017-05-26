package com.mygdx.game.utils;

/**
 * A class that holds global constants.
 * @author soflarb
 * Revised by car0b1nius, rastom, rarvid
 */

public final class Constants {
	private Constants(){}
	//The virtual screen
	/*
	public static final float VIEWPORT_DIM_X = 340; //SNES
	public static final float VIEWPORT_DIM_Y = 244;
	*/
	public static final float VIEWPORT_DIM_X = 390;
	public static final float VIEWPORT_DIM_Y = 244;
	/*
	public static final float VIEWPORT_DIM_X = 160; //Game Boy
	public static final float VIEWPORT_DIM_Y = 144;
	*/
	//The piano roll
	public static final float PIANOROLL_TOP_PADDING = 25;
	public static final float PIANOROLL_BOT_PADDING = 35;
	public static final float PIANOROLL_DIM_X = VIEWPORT_DIM_X;
	public static final float PIANOROLL_DIM_Y = VIEWPORT_DIM_Y - (PIANOROLL_TOP_PADDING + PIANOROLL_BOT_PADDING );
	public static final float PIANOROLL_POS_X = 0;
	public static final float PIANOROLL_POS_Y = VIEWPORT_DIM_Y - PIANOROLL_DIM_Y - PIANOROLL_TOP_PADDING;
	public static final int NUMBER_OF_LANES = 12;
    public static final float LANE_WIDTH = VIEWPORT_DIM_X/NUMBER_OF_LANES;
	public static final float SCORE_BOUNDS_UPPER= PIANOROLL_BOT_PADDING;
    public static final float SCORE_BOUNDS_LOWER= 25;
}
