package no.troll;

import java.util.ArrayList;
import java.util.HashMap;

import no.troll.Resources.SoundName;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager 
{
	private Resources resources;
	private ArrayList<Sound> SoundList;
	
	public SoundManager(Resources resources)
	{
		this.resources=resources;
			
		Sound fx = resources.getSound(SoundName.Theme);
		
		fx.play(1.0f,1.0f);
	}
	
	
}
