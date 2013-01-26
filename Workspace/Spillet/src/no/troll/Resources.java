package no.troll;


import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {
	
	public enum TileImageName {Dirt1, Dirt2, Dirt3};
	public enum BrickImageName {Wall, Fjell};
	public enum CharacterImageName {Virgin, Troll};
	public enum SoundName {Theme,Sword};

	private HashMap<TileImageName, Image> tileImageMap;
	private HashMap<BrickImageName, Image> brickImageMap;
	private HashMap<BrickImageName, Integer> brickSizes;
	private HashMap<CharacterImageName, Image> characterImageMap;
	private HashMap<SoundName,Sound> SoundMap;
	
	private TileImageName[] tileImageNames;
	private BrickImageName[] brickImageNames;
	
	private String base_path;
	
	
	public Resources(String base_path) {
		this.base_path = base_path;
		tileImageMap = new HashMap<TileImageName, Image>();
		brickImageMap = new HashMap<Resources.BrickImageName, Image>();
		characterImageMap = new HashMap<Resources.CharacterImageName, Image>();
		brickSizes = new HashMap<Resources.BrickImageName, Integer>();
		SoundMap = new HashMap<Resources.SoundName,Sound>();
		
		tileImageNames = TileImageName.values();
		brickImageNames = BrickImageName.values();
		
		loadTiles();
		loadBricks();
		loadCharacters();
		loadSound();
	}
	
	private void loadSound() 
	{	
		addSound("GGJ13_Theme.wav",SoundName.Theme);
		addSound("Sword.wav",SoundName.Sword);
		
	}

	private void loadTiles() {
		addTile("DirtTileMal.png", TileImageName.Dirt1);
		addTile("DirtTileMal2.png", TileImageName.Dirt2);
		addTile("DirtTileMal3.png", TileImageName.Dirt3);
	}

	private void loadCharacters() {
		addCharacter("Troll.png", CharacterImageName.Troll);
		addCharacter("Virgin.png", CharacterImageName.Virgin);
	}

	private void loadBricks() {
		addBrick("Wall.png", BrickImageName.Wall, 50);
		addBrick("Fjell.png", BrickImageName.Fjell, 100);
	}
	
	private void addSound(String fileName, SoundName SName) 
	{
		String location = base_path + "sound/" + fileName;
		try{
			SoundMap.put(SName, new Sound(location));
		}catch (SlickException e){
			e.printStackTrace();
			System.out.println("ERROR: no sounds at location: " + location);
		}
		
	}

	private void addCharacter(String fileName, CharacterImageName imageName) {
		String location = base_path + "img/characters/" + fileName;
		try {
			characterImageMap.put(imageName, new Image(location));
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no image at location: " + location);
		}		
	}	
	
	private void addBrick(String fileName, BrickImageName imageName, int xPosForZ) {
		String location = base_path + "img/bricks/" + fileName;
		try {
			brickImageMap.put(imageName, new Image(location));
			brickSizes.put(imageName, xPosForZ);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no image at location: " + location);
		}		
	}	
	
	private void addTile(String fileName, TileImageName tileName) {
		String location = base_path + "img/tiles/" + fileName;
		try {
			tileImageMap.put(tileName, new Image(location));
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no image at location: " + location);
		}
	}
	
	public Image getTileImage(TileImageName tileName) {
		return tileImageMap.get(tileName);
	}
	
	public Image getRandomTileImage() {
		int r = (int) (Math.random() * TileImageName.values().length);
		return tileImageMap.get(tileImageNames[r]);
	}

	public Image getBrick(BrickImageName imageName) {
		return brickImageMap.get(imageName);
	}

	public int getBrickXPosForZ(BrickImageName imageName) {
		return brickSizes.get(imageName);
	}

	public Image getRandomBrickImage() {
		int r = (int) (Math.random() * BrickImageName.values().length);
		return brickImageMap.get(brickImageNames[r]);
	}

	public Image getCharacter(CharacterImageName imageName) {
		return characterImageMap.get(imageName);
	}
	
	public Sound getSound(SoundName SName)
	{
		return SoundMap.get(SName);
	}

}
