package no.troll;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resources {
	
	public enum TileImageName {Dirt1, Dirt2, Dirt3, RockBlock};

	private HashMap<TileImageName, Image> tileImageMap;
	private TileImageName[] tileImageTypes;
	
	private String base_path;
	
	
	public Resources(String base_path) {
		this.base_path = base_path;
		tileImageMap = new HashMap<TileImageName, Image>();
		tileImageTypes = TileImageName.values();
		
		loadTiles();
		loadImages();
	}
	
	private void loadTiles() {
		addTile("DirtTileMal.png", TileImageName.Dirt1);
		addTile("DirtTileMal2.png", TileImageName.Dirt2);
		addTile("DirtTileMal3.png", TileImageName.Dirt3);
		addTile("SteinblokkTing.png", TileImageName.RockBlock);
	}
	
	private void loadImages() {
		
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
		return tileImageMap.get(tileImageTypes[r]);
	}
	
}
