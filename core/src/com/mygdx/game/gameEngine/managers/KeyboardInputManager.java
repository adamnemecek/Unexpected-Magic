package com.mygdx.game.gameEngine.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.Score;

import java.util.Random;

/**
 * Created by rasmus on 2017-05-05.
 */
public class KeyboardInputManager implements InputProcessor {

    private final SoundManager soundManager;
    private final PianoRoll pianoRoll;

    //TODO I am very confused as to how it should be structured, should this class own a soundmanager
    public KeyboardInputManager(SoundManager s, PianoRoll p){
        this.soundManager = s;
        this.pianoRoll = p;

    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){

            case (Input.Keys.TAB) : soundManager.setInstrument(new Random().nextInt(100));
                break;

            case (Input.Keys.A):
                soundManager.noteOn(57);
                pianoRoll.activateLane(0);
                break;

            case (Input.Keys.W):
                soundManager.noteOn(58);
                pianoRoll.activateLane(1);

                break;

            case (Input.Keys.S):
                soundManager.noteOn(59);
                pianoRoll.activateLane(2);

                break;

            case (Input.Keys.D):
                soundManager.noteOn(60);
                pianoRoll.activateLane(3);

                break;

            case (Input.Keys.R):
                soundManager.noteOn(61);
                pianoRoll.activateLane(4);

                break;

            case (Input.Keys.F):
                soundManager.noteOn(62);
                pianoRoll.activateLane(5);

                break;

            case (Input.Keys.T):
                soundManager.noteOn(63);
                pianoRoll.activateLane(6);

                break;

            case (Input.Keys.G):
                soundManager.noteOn(64);
                pianoRoll.activateLane(7);

                break;

            case (Input.Keys.H):
                soundManager.noteOn(65);
                pianoRoll.activateLane(8);

                break;

            case (Input.Keys.U):
                soundManager.noteOn(66);
                pianoRoll.activateLane(9);
                break;

            case (Input.Keys.J):
                soundManager.noteOn(67);
                pianoRoll.activateLane(10);
                break;

            case (Input.Keys.I):
                soundManager.noteOn(68);
                pianoRoll.activateLane(11);
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){

            case (Input.Keys.A):
                soundManager.noteOff(57);
                pianoRoll.deactivateLane(0);
                break;

            case (Input.Keys.W):
                soundManager.noteOff(58);
                pianoRoll.deactivateLane(1);
                break;

            case (Input.Keys.S):
                soundManager.noteOff(59);
                pianoRoll.deactivateLane(2);
                break;

            case (Input.Keys.D):
                soundManager.noteOff(60);
                pianoRoll.deactivateLane(3);
                break;

            case (Input.Keys.R):
                soundManager.noteOff(61);
                pianoRoll.deactivateLane(4);
                break;

            case (Input.Keys.F):
                soundManager.noteOff(62);
                pianoRoll.deactivateLane(5);
                break;

            case (Input.Keys.T):
                soundManager.noteOff(63);
                pianoRoll.deactivateLane(6);
                break;

            case (Input.Keys.G):
                soundManager.noteOff(64);
                pianoRoll.deactivateLane(7);
                break;

            case (Input.Keys.H):
                soundManager.noteOff(65);
                pianoRoll.deactivateLane(8);

                break;

            case (Input.Keys.U):
                soundManager.noteOff(66);
                pianoRoll.deactivateLane(9);

                break;

            case (Input.Keys.J):
                soundManager.noteOff(67);
                pianoRoll.deactivateLane(10);

                break;

            case (Input.Keys.I):
                soundManager.noteOff(68);
                pianoRoll.deactivateLane(11);

                break;


            default:
                break;
        }

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
