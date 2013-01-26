
package no.troll;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;



public class TileManager implements Drawable {

	private Resources resources;
	private ArrayList<Tile> tileList;
	
	private int tileWidth;
	private int tileHeight;
	
	private final int limit_change_tilesX = 100;
	private final int limit_change_tilesY = 50;
	private final int numerOfNewRows = 2;
	private final int numerOfNewColumns = 2;
	private int movedX;
	private int movedY;
	
	private int topX;
	private int topY;
	private int rows;
	private int columns;
	
	
	public TileManager(Resources resources, int windowWidth, int windowHeight, int tileWidth, int tileHeight) {
		this.resources = resources;
		tileList = new ArrayList<Tile>();
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
//		int tileColumns = windowWidth / tileWidth + 1;
//		int tileRows = (windowHeight / tileHeight + 1) * 2;
		movedX = 0;
		movedY = 0;
		columns = 6;
		rows = 8;
		topX = 200;
		topY = 400;
		//addTileGrid(-50, -25, tileColumns, tileRows, tileWidth, tileHeight);
		addTileGrid(topX, topY, columns, rows, tileWidth, tileHeight);
	}
	
	public void addTile(int x, int y) {
		Image i = resources.getRandomTileImage();
		tileList.add(new Tile(i, x, y - i.getHeight() + tileHeight));
	}
	
	public void addRowOfTiles(int x, int y, int columns, int tileWidth) {
		for (int c=0; c<columns; c++) {
			int x_cord = x + tileWidth * c;
			addTile(x_cord, y);
		}
	}
	
	public void addTileGrid(int x, int y, int columns, int rows, int tileWidth, int tileHeight) {
		for (int r=0; r<rows; r++) {
			int x_cord = x + (tileWidth / 2) * (r % 2);
			int y_cord = y + r * tileHeight / 2;
			addRowOfTiles(x_cord, y_cord, columns, tileWidth);
		}
	}

	
	@Override
	public void draw(Graphics g) {
		for (Tile tile : tileList) {
			tile.draw(g);
		}
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		movedX += delta_pos.x;
		movedY += delta_pos.y;
		for (Tile tile : tileList) {
			tile.update(delta, delta_pos);
		}		
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
	}

	@Override
	public Pair getZ() {
		return new Pair(0, -100);
	}

	
}
