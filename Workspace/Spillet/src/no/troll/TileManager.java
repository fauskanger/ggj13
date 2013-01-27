
package no.troll;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;



public class TileManager implements Drawable {

	private Resources resources;
	private ArrayList<Tile> tileList;
	
	private int tileWidth;
	private int tileHeight;
	
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
		columns = 12;
		rows = 38;
		topX = -100;
		topY = -100;
//		addTileGrid(-50, 50, columns, rows, tileWidth, tileHeight);
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
		Collections.sort(tileList, new Comparator<Tile>() {
			@Override
			public int compare(Tile a, Tile b) {
				return Integer.signum(a.getZ().y - b.getZ().y);
			}
		});
		for (Tile tile : tileList) {
			tile.draw(g);
		}
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		movedX += delta_pos.x;
		movedY += delta_pos.y;
		topX += delta_pos.x;
		topY += delta_pos.y;
		
		boolean removeRows = false;
		boolean removeAbove = false;
		int removeY = 0;
		
		if (Math.abs(movedY) > numerOfNewRows * 25) {
			if (movedY > 0) {
				topY -= numerOfNewRows * 25;
				addTileGrid(topX, topY, columns, numerOfNewRows, tileWidth, tileHeight);
				removeY = topY + rows * 25;
				movedY -= numerOfNewRows * 25;
			}
			else {
				//Slette på toppen
				//Legge til i bunn
				addTileGrid(topX, topY + rows * 25, columns, numerOfNewRows, tileWidth, tileHeight);
				topY += numerOfNewRows * 25;
				removeY = topY + (numerOfNewRows - 2) * 25;
				removeAbove = true;
				movedY += numerOfNewRows * 25;
			}
			removeRows = true;
		}
		Iterator<Tile> tileIterator = tileList.iterator(); 
		while (tileIterator.hasNext()) {
			Tile t = tileIterator.next();
			if (removeRows) {
				if (removeAbove) {
					if (t.getZ().y - 25 < removeY) {
						tileIterator.remove();
						continue;
					}
				}
				else {
					if (t.getZ().y - 25 > removeY) {
						tileIterator.remove();
						continue;
					}
				}
			}
			t.update(delta, delta_pos);
		}		
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
	}

	@Override
	public Pair getZ() {
		return new Pair(0, -100);
	}

	public Tile[] mouseClick(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		return null;
	}

	public void mouseHover(int mouseX, int mouseY) {
		for (Tile t : tileList) {
			if (t.pointIsInside(mouseX, mouseY)) {
				t.mouseHover();
			}
		}
	}

	
}
