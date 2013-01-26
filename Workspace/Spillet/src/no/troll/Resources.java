

package no.troll;


import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {
	
	public enum TileImageName {Dirt1, Dirt2, Dirt3, Grass1, Grass2, Grass3};
	public enum BrickImageName {Wall, Fjell};

	public enum CharacterImageName { Virgin,
									 Virgin_STILL, 
									 Virgin_UP_1,
									 Virgin_UP_2,
									 Virgin_UP_3,
									 Virgin_RIGHT_1,
									 Virgin_RIGHT_2,
									 Virgin_RIGHT_3,
									 Virgin_LEFT_1,
									 Virgin_LEFT_2,
									 Virgin_LEFT_3,
									 Virgin_DOWN_1,
									 Virgin_DOWN_2,
									 Virgin_DOWN_3,
									 Virgin_UPLEFT_1,
									 Virgin_UPLEFT_2,
									 Virgin_UPLEFT_3,
									 Virgin_UPRIGHT_1,
									 Virgin_UPRIGHT_2,
									 Virgin_UPRIGHT_3,
									 Virgin_DOWNLEFT_1,
									 Virgin_DOWNLEFT_2,
									 Virgin_DOWNLEFT_3,
									 Virgin_DOWNRIGHT_1,
									 Virgin_DOWNRIGHT_2,
									 Virgin_DOWNRIGHT_3,
									 Troll };

	public enum SoundName {Theme,Sword};

	private HashMap<SoundName,Sound> SoundMap;
	private HashMap<TileImageName, Image> tileImageMap;
	private HashMap<BrickImageName, Image> brickImageMap;
	private HashMap<BrickImageName, Integer> brickSizes;
	private HashMap<CharacterImageName, Image> characterImageMap;
	
	private TileImageName[] tileImageNames;
	private BrickImageName[] brickImageNames;
	
	private String base_path;
	
	
	public Resources(String base_path) {
		this.base_path = base_path;
		tileImageMap = new HashMap<TileImageName, Image>();
		brickImageMap = new HashMap<Resources.BrickImageName, Image>();
		characterImageMap = new HashMap<Resources.CharacterImageName, Image>();
		brickSizes = new HashMap<Resources.BrickImageName, Integer>();
		tileImageNames = TileImageName.values();
		brickImageNames = BrickImageName.values();
		SoundMap = new HashMap<Resources.SoundName,Sound>();

		loadSound();
		loadTiles();
		loadBricks();
		loadCharacters();
	}
	
	private void loadSound() 
	{	
		addSound("GGJ13_Theme.wav",SoundName.Theme);
		addSound("Sword.wav",SoundName.Sword);
		
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

	

	public Sound getSound(SoundName SName)
	{
		return SoundMap.get(SName);
	}

	private void loadTiles() {
		addTile("DirtTileMal.png", TileImageName.Dirt1);
		addTile("DirtTileMal2.png", TileImageName.Dirt2);
		addTile("DirtTileMal3.png", TileImageName.Dirt3);
		addTile("GressTile01.png", TileImageName.Grass1);
		addTile("GressTile02.png", TileImageName.Grass2);
		addTile("GressTile03.png", TileImageName.Grass3);
	}

	private void loadCharacters() {
		addCharacter("Troll.png", CharacterImageName.Troll);
		addCharacter("Virgin/DownLeft1.png", CharacterImageName.Virgin);
		addCharacter("Virgin/DownLeft1.png", CharacterImageName.Virgin_DOWN_1);
		addCharacter("Virgin/DownLeft2.png", CharacterImageName.Virgin_DOWN_2);
		addCharacter("Virgin/DownLeft3.png", CharacterImageName.Virgin_DOWN_3);
		addCharacter("Virgin/DownLeft1.png", CharacterImageName.Virgin_DOWNLEFT_1);
		addCharacter("Virgin/DownLeft2.png", CharacterImageName.Virgin_DOWNLEFT_2);
		addCharacter("Virgin/DownLeft3.png", CharacterImageName.Virgin_DOWNLEFT_3);
		addCharacter("Virgin/DownRight1.png", CharacterImageName.Virgin_DOWNRIGHT_1);
		addCharacter("Virgin/DownRight2.png", CharacterImageName.Virgin_DOWNRIGHT_2);
		addCharacter("Virgin/DownRight3.png", CharacterImageName.Virgin_DOWNRIGHT_3);
		addCharacter("Virgin/UpLeft1.png", CharacterImageName.Virgin_LEFT_1);
		addCharacter("Virgin/UpLeft2.png", CharacterImageName.Virgin_LEFT_2);
		addCharacter("Virgin/UpLeft3.png", CharacterImageName.Virgin_LEFT_3);
		addCharacter("Virgin/UpRight1.png", CharacterImageName.Virgin_RIGHT_1);
		addCharacter("Virgin/UpRight2.png", CharacterImageName.Virgin_RIGHT_2);
		addCharacter("Virgin/UpRight3.png", CharacterImageName.Virgin_RIGHT_3);
		addCharacter("Virgin/DownLeft1.png", CharacterImageName.Virgin_STILL);
		addCharacter("Virgin/UpRight1.png", CharacterImageName.Virgin_UP_1);
		addCharacter("Virgin/UpRight2.png", CharacterImageName.Virgin_UP_2);
		addCharacter("Virgin/UpRight3.png", CharacterImageName.Virgin_UP_3);
		addCharacter("Virgin/UpLeft1.png", CharacterImageName.Virgin_UPLEFT_1);
		addCharacter("Virgin/UpLeft2.png", CharacterImageName.Virgin_UPLEFT_2);
		addCharacter("Virgin/UpLeft3.png", CharacterImageName.Virgin_UPLEFT_3);
		addCharacter("Virgin/UpRight1.png", CharacterImageName.Virgin_UPRIGHT_1);
		addCharacter("Virgin/UpRight2.png", CharacterImageName.Virgin_UPRIGHT_2);
		addCharacter("Virgin/UpRight3.png", CharacterImageName.Virgin_UPRIGHT_3);
	}

	private void loadBricks() {
		addBrick("Wall.png", BrickImageName.Wall, 50);
		addBrick("Fjell.png", BrickImageName.Fjell, 100);
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
	
	

}
