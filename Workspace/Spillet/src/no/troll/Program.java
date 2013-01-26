package no.troll;

import no.troll.TileImages.TileImageName;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {

	static private int width = 1024;
	static private int height = 768;
	static private int tileWidth = 100;
	static private int tileHeight = 50;
	

	Image testImg;
	int X = 200;
	int Y = 200;
	
	private TileImages tileImages;
	
	public Program() {
		super("Spillet");
		// TODO Auto-generated constructor stub
	}

	public void drawTile(Graphics g, int x, int y, int w) {
		int h = w / 2;
		int top_x = x + w / 2;
		int top_y = y;
		int left_x = x;
		int left_y = y + h / 2;
		int bottom_x = x + w / 2;
		int bottom_y = y + h;
		int right_x = x + w;
		int right_y = y + h / 2;
//		g.drawLine(top_x, top_y, left_x, left_y);
//		g.drawLine(top_x, top_y, right_x, right_y);
//		g.drawLine(bottom_x, bottom_y, left_x, left_y);
//		g.drawLine(bottom_x, bottom_y, right_x, right_y);
		g.drawImage(tileImages.getTileImage(TileImageName.Dirt1), x, y);
	}
	
	public void makeTileRow(Graphics g, int x, int y, int columns) {
		for (int c=0; c<columns; c++) {
			int x_cord = x + tileWidth * c;
			drawTile(g, x_cord, y, tileWidth);
		}
	}
	
	public void makeTileGrid(Graphics g, int x, int y, int columns, int rows) {
		for (int r=0; r<rows; r++) {
			int x_cord = x + (tileWidth / 2) * (r % 2);
			int y_cord = y + r * tileHeight / 2;
			makeTileRow(g, x_cord, y_cord, columns);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Program());		 
	     app.setDisplayMode(width, height, false);
	     app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 255, 255));
		int tileWidth = 100;
		int tileColumns = width / tileWidth + 1;
		int tileRows = (height / tileHeight + 1) * 2;
		makeTileGrid(g, -50, -25, tileColumns, tileRows);
		g.drawImage(tileImages.getTileImage(TileImageName.Dirt1), 20, 20);
		
		g.drawString("Hello World", 100, 100);
		g.drawImage(testImg,X,Y);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		tileImages = new TileImages();
		
		testImg=new Image("C:\\Users\\Guchoo\\Pictures\\Ipod\\Noodle-gorillaz.jpg");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){Y -=1;}
		if(input.isKeyDown(Input.KEY_DOWN)){Y ++;}
		if(input.isKeyDown(Input.KEY_LEFT)){X -=1;}
		if(input.isKeyDown(Input.KEY_RIGHT)){X ++;}
	}

}
