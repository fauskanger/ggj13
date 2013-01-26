package no.troll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import no.troll.Resources.ImageName;
import no.troll.Resources.TileImageName;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;

public class Program extends BasicGame {

	static private final int windowWidth = 1024;
	static private final int windowHeight = 768;
	static private final int freeZoneTop = 200;
	static private final int freeZoneLeft = 200;
	static private final int freeZoneRight = windowWidth - 200;
	static private final int freeZoneBottom = windowHeight - 200;

	
	static final public int tileWidth = 100;
	static final public int tileHeight = 50;

	private Virgin virgin;
	
	private Resources resources;
	private TileManager tileManager;
	private ArrayList<Drawable> drawables;
	private ArrayList<Drawable> fixedObjects;
	
	
	public Program() {
		super("Spillet");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		tileManager.draw(g);

		Collections.sort(drawables, new Comparator<Drawable>() {
		    public int compare(Drawable a, Drawable b) {
		        return Integer.signum(a.getZ().y - b.getZ().y);
		    }
		});
		
		for (Drawable drawable : drawables) {
			drawable.draw(g);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		resources = new Resources("res/");
		drawables = new ArrayList<Drawable>();
		fixedObjects = new ArrayList<Drawable>();
		tileManager = new TileManager(resources, windowWidth, windowHeight, tileWidth, tileHeight);
		int[] freeZone = new int[4];
		freeZone[0] = freeZoneTop;
		freeZone[1] = freeZoneRight;
		freeZone[2] = freeZoneBottom;
		freeZone[3] = freeZoneLeft;
		virgin = new Virgin(resources.getTileImage(TileImageName.RockBlock), windowWidth/2, windowHeight/2, freeZone, fixedObjects);
		drawables.add(virgin);
		Drawable wall = new Wall(resources.getImage(ImageName.Wall), 500, 300);
		drawables.add(wall);
		fixedObjects.add(wall);
		
//		Sound s = new Sound("res/sound/GGJ13_Theme.wav");
//		s.play();
		
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
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.start();
	}

}
