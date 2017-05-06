package com.mygdx.game.gameEngine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.Score;

/**
 * Created by rasmus on 2017-05-06.
 */
public class ScoreSystem extends IteratingSystem {

    private final Score score;
    private final PianoRoll pianoRoll;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<NoteComponent> nm = ComponentMapper.getFor(NoteComponent.class);

    public ScoreSystem(Score s, PianoRoll p) {
        super(Family.all(PositionComponent.class, NoteComponent.class).get());
        this.score = s;
        this.pianoRoll = p;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        NoteComponent not = nm.get(entity);
        if (pianoRoll.getLaneState(not.note.lane)) {
            PositionComponent pos = pm.get(entity);

        if(pos.y < 100 && pos.y > 50) { //TODO THIS IS NOT ACCURATE
            score.hitNote();
        }
        }
    }
}
