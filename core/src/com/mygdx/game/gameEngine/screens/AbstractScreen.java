package com.mygdx.game.gameEngine.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.UnexpectedMagic;

/**
 * 
 * @author Arvid
 *
 */

public abstract class AbstractScreen extends ScreenAdapter{
	UnexpectedMagic game;
	OrthographicCamera camera;
	Viewport viewport;
	protected Stage stage;

}
