package no.troll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import no.troll.Resources.BrickImageName;
import no.troll.Resources.CharacterImageName;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Program extends BasicGame {

	static public final int windowWidth = 1024;
	static public final int windowHeight = 768;
	static private final int freeZoneTop = 100;
	static private final int freeZoneLeft = -100;
	static private final int freeZoneRight = windowWidth;
	static private final int freeZoneBottom = windowHeight - 350;

	
	static final public int tileWidth = 100;
	static final public int tileHeight = 50;

	private Virgin virgin;
	private Troll troll;
	
	private Resources resources;
	private TileManager tileManager;
	private ArrayList<Drawable> drawables;
	private ArrayList<Brick> bricks;
	private ArrayList<Drawable> mobs;

	private SoundManager noe;
	
	public Program() {
		super("Spillet");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setBackground(new Color(98, 51, 0));
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
		bricks = new ArrayList<Brick>();
		mobs = new ArrayList<Drawable>();
		tileManager = new TileManager(resources, windowWidth, windowHeight, tileWidth, tileHeight);
		int[] freeZone = new int[4];
		freeZone[0] = freeZoneTop;
		freeZone[1] = freeZoneRight;
		freeZone[2] = freeZoneBottom;
		freeZone[3] = freeZoneLeft;
		virgin = new Virgin(resources, windowWidth/2, windowHeight/2, freeZone, bricks, tileManager);
		drawables.add(virgin);
		Brick wall = new Brick(resources.getBrick(BrickImageName.Wall), 600, 300, resources.getBrickXPosForZ(BrickImageName.Wall));
		Brick fjell = new Brick(resources.getBrick(BrickImageName.Fjell), 300, 150, resources.getBrickXPosForZ(BrickImageName.Fjell));
		drawables.add(wall);
		bricks.add(wall);
		drawables.add(fjell);
		bricks.add(fjell);

		troll = new Troll(resources, windowWidth/5, windowHeight/5, virgin, bricks);
		drawables.add(troll);
		mobs.add(troll);
		noe = new SoundManager(resources); 
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
//		Polygon s = new Polygon();
//		s.addPoint(-10, 10);
//		s.addPoint(10, 10);
//		s.addPoint(10, -10);
//		s.addPoint(-10, -10);
//		System.out.println(s.contains(4, 2));
		AppGameContainer app = new AppGameContainer(new Program());		 
		app.setDisplayMode(windowWidth, windowHeight, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.start();
	}

}
