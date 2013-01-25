package no.troll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {

	public Program() {
		super("Hello World");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Program());
		 
	     app.setDisplayMode(800, 600, false);
	     app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Hello World", 100, 100);
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
