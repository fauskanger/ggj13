package no.troll;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resources {
	
	public enum TileImageName {Dirt1, Dirt2, Dirt3, RockBlock};
	public enum BrickImageName {Wall};
	public enum CharacterImageName {Virgin, Troll};

	private HashMap<TileImageName, Image> tileImageMap;
	private HashMap<BrickImageName, Image> brickImageMap;
	private HashMap<CharacterImageName, Image> characterImageMap;
	
	private TileImageName[] tileImageNames;
	private BrickImageName[] brickImageNames;
	
	private String base_path;
	
	
	public Resources(String base_path) {
		this.base_path = base_path;
		tileImageMap = new HashMap<TileImageName, Image>();
		brickImageMap = new HashMap<Resources.BrickImageName, Image>();
		tileImageNames = TileImageName.values();
		brickImageNames = BrickImageName.values();
		
		loadTiles();
		loadBricks();
		loadCharacters();
	}
	
	private void loadTiles() {
		addTile("DirtTileMal.png", TileImageName.Dirt1);
		addTile("DirtTileMal2.png", TileImageName.Dirt2);
		addTile("DirtTileMal3.png", TileImageName.Dirt3);
		addTile("SteinblokkTing.png", TileImageName.RockBlock);
	}

	private void loadCharacters() {
	}

	private void addImage(String fileName, BrickImageName imageName) {
		String location = base_path + "img/koz/" + fileName;
		try {
			brickImageMap.put(imageName, new Image(location));
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no image at location: " + location);
		}		
	}

	private void loadBricks() {
		addImage("Wall.png", BrickImageName.Wall);		
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

	public Image getImage(BrickImageName imageName) {
		return brickImageMap.get(imageName);
	}

}
