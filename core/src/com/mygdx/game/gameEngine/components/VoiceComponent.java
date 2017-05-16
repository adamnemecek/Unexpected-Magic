package com.mygdx.game.gameEngine.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.model.song.IVoice;


public class VoiceComponent implements Component{
	private final IVoice voice;
	
	public VoiceComponent(IVoice voice){
		this.voice = voice;
	}
	public IVoice getVoice(){
		return voice;
	}
}
