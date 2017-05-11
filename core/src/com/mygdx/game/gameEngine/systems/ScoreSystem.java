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
        PositionComponent pos = pm.get(entity);
        if (noteLanes.getLaneState(not.getNote().getPitch() % Constants.NUMBER_OF_LANES)) { //checks if the note's noteLane is active
        	//PositionComponent pos = pm.get(entity);

        	if(pos.getY() < Constants.SCORE_BOUNDS_UPPER && pos.getY() > Constants.SCORE_BOUNDS_LOWER) { //checks if the note is in the playable area TODO THIS MAY NOT BE ACCURATE
        		score.hitNote();
        		
        		if(!not.getNote().isHit()){
        			score.incStreak();
        			soundManager.play(not.getNote().getPitch(),not.getNote().getDuration());
        			getEngine().removeEntity(entity);
        		}
        	}
        }
        else if(pos.getY() >=0 && pos.getY() <=1 && !not.getNote().isHit()){ //checks if note has passed the play area without being played
        	score.missedNote();
            //this.soundManager.play(new Random().nextInt(100), 10);
        
        }
    }
    
}
