package com.mygdx.game.gameEngine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.managers.SoundManager;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.Score;

import java.util.Random;

/**
 * Created by rasmus on 2017-05-06.
 */
public class ScoreSystem extends IteratingSystem {

    private final Score score;
    private final NoteLanes noteLanes;
    private final SoundManager soundManager;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<NoteComponent> nm = ComponentMapper.getFor(NoteComponent.class);

    public ScoreSystem(Score s, NoteLanes n, SoundManager sm) {
        super(Family.all(PositionComponent.class, NoteComponent.class).get());
        this.score = s;
        this.noteLanes = n;
        this.soundManager = sm;
        soundManager.setInstrument(45);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        NoteComponent not = nm.get(entity);
        if (noteLanes.getLaneState(not.note.lane)) {
            PositionComponent pos = pm.get(entity);

        if(pos.y < Constants.SCORE_BOUNDS_UPPER && pos.y > Constants.SCORE_BOUNDS_LOWER) { //TODO IS THIS ACCURATE?
            //score.hitNote();
            Constants.setScore(1);
            soundManager.play(not.note.number,not.note.duration);
            getEngine().removeEntity(entity);
            //soundManager.noteOff(not.note.number);
        }
        else{
            this.soundManager.play(new Random().nextInt(100), 10);
        }

        }
    }
}
