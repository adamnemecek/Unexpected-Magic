package com.mygdx.game.gameEngine.screens;

/**
 * Interface for classes that wish to listen to screen changes
 * @author rarvid
 * 
 * Uses: AbstractScreen
 * 
 * Used by: AbstractScreen, ScreenListener
 */

public interface ScreenListener {
	void screenChanged(AbstractScreen screen);

}
