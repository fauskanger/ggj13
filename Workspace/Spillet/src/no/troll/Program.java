package no.troll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {

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
		g.drawLine(top_x, top_y, left_x, left_y);
		g.drawLine(top_x, top_y, right_x, right_y);
		g.drawLine(bottom_x, bottom_y, left_x, left_y);
		g.drawLine(bottom_x, bottom_y, right_x, right_y);
	}
	
	public void makeGrid(Graphics g, int x, int y, int columns, int rows, int tileWidth) {
		for (int c=0; c<columns; c++) {
			int x_cord = x + tileWidth * c; 
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Program());		 
	     app.setDisplayMode(1024, 768, false);
	     app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 255, 255));
		int width = 100;
		drawTile(g, 40, 40, width);
		drawTile(g, 40+width, 40, width);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
