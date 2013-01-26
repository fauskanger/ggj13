
package no.troll;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TileImages {

	private String tileImagePath = "img/tiles/";
	public enum TileImageName {Fence, Dirt1, Dirt2, Dirt3, RockBlock};
	private HashMap<TileImageName,Image> imageList;
	
	public TileImages() {
		imageList = new HashMap<TileImages.TileImageName, Image>();
		addImage("DirtTileMal.png", TileImageName.Dirt1);
		addImage("DirtTileMal2.png", TileImageName.Dirt2);
		addImage("DirtTileMal3.png", TileImageName.Dirt3);
		addImage("SteinblokkTing.png", TileImageName.RockBlock);
	}
	
	private void addImage(String fileName, TileImageName tileName) {
		try {
			imageList.put(tileName, new Image(tileImagePath + fileName));
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: no image at location: " + fileName);
		}
	}
	
	public Image getTileImage(TileImageName tileName) {
		return imageList.get(tileName);
	}
	
	public Image getRandomTileImage() {
		return imageList.get(Math.random()*TileImageName.values().length);
	}
	
	
	
}
