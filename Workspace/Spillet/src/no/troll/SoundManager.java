package no.troll;

import java.util.HashMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager 
{
	private String SoundPath = "res/sound";
	public enum SoundName{Theme,sword};
	private HashMap<SoundName, Sound> SoundList;
	
	public SoundManager()
	{
		SoundList = new HashMap<SoundManager.SoundName, Sound>();
		addSound("GGJ13_Theme.wav", SoundName.Theme);
		addSound("Sword.wav", SoundName.sword);
	}
	
	private void addSound(String fileName, SoundName tileName) {
		try {
			SoundList.put(tileName, new Sound(SoundPath + fileName));
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no sound at location: " + fileName);
		}
	}
	
	public Sound getSound(SoundName tileName) {
		return SoundList.get(tileName);
	}

}
