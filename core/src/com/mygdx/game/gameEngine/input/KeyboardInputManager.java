package com.mygdx.game.gameEngine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.Score;

import java.util.Random;

/**
 * Created by rasmus on 2017-05-05.
 */
public class KeyboardInputManager implements InputProcessor {

    private final KeyboardControllerAdapter keyboardControllerAdapter;

    //TODO I am very confused as to how it should be structured, should this class own a soundmanager
    public KeyboardInputManager(){
        keyboardControllerAdapter = new KeyboardControllerAdapter();
    }

    @Override
    public boolean keyDown(int keycode) {
        keyboardControllerAdapter.noteKeyPressed(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
