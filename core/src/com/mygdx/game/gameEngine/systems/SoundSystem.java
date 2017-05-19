package com.mygdx.game.gameEngine.systems;

import java.util.List;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.managers.Synth;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

/**
 * Created by rasmus on 2017-05-05.
 * Revised by Arvid
 */
public class SoundSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<NoteComponent> nm = ComponentMapper.getFor(NoteComponent.class);
    private ComponentMapper<HitComponent> hm = ComponentMapper.getFor(HitComponent.class);
    private Synth synth;
    private List<IVoice> autoVoices;

    public SoundSystem(Synth s) {
        super(Family.all(PositionComponent.class, NoteComponent.class).get()); //grekiska
        this.synth = s;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = pm.get(entity);
        NoteComponent not = nm.get(entity);
        HitComponent hit = hm.get(entity);

        if ((int) pos.getY() <= Constants.SCORE_BOUNDS_UPPER && !hit.isHit()) { //TODO Is this accurate
            
        	//soundManager.play(not.getNote());
            hit.hit();
            //}
        }
    }
}
