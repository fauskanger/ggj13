package no.troll;


import no.troll.Resources.SoundName;
import org.newdawn.slick.Sound;

public class SoundManager 
{
	private Resources resources;
	
	public SoundManager(Resources resources)
	{
		this.resources=resources;
		
		Sound fx = resources.getSound(SoundName.Theme);
		fx.play(1.0f,1.0f);
		
	}
	
	
}
