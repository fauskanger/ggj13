package no.troll;

import java.util.ArrayList;

import no.troll.Resources.TileImageName;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {

	static private int windowWidth = 1024;
	static private int windowHeight = 768;
	
	private int tileWidth = 100;
	private int tileHeight = 50;

	private Virgin virgin;
	
	private Resources resources;
	private TileManager tileManager;
	private InputManager inputManager;
	private ArrayList<Drawable> drawables;
	
	
	public Program() {
		super("Spillet");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		tileManager.draw(g);
		for (Drawable drawable : drawables) {
			drawable.draw(g);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		resources = new Resources("res/");
		drawables = new ArrayList<Drawable>();
		tileManager = new TileManager(resources, windowWidth, windowHeight, tileWidth, tileHeight);
		virgin = new Virgin(resources.getTileImage(TileImageName.RockBlock), windowWidth/2, windowHeight/2);
		inputManager = new InputManager(gc);
		drawables.add(virgin);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Pair delta_pos = virgin.update(delta, gc.getInput());
		tileManager.update(delta, delta_pos);
		for (Drawable drawable : drawables) {
			drawable.update(delta, delta_pos);
		}
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Program());		 
	     app.setDisplayMode(windowWidth, windowHeight, false);
	     app.start();
	}

}
