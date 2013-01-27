package no.troll;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Tile implements Drawable {

	private int posX;
	private int posY;
	private Image image;
	public Polygon shape;
	private boolean mouseHovering;
	
	public Tile(Image image, int posX, int posY) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		shape = new Polygon();
		shape.addPoint(posX, getZ().y - 25);
		shape.addPoint(posX + 50, getZ().y - 50); 
		shape.addPoint(posX + 100, getZ().y - 25);
		shape.addPoint(posX + 50, getZ().y);
		mouseHovering = false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY);
		//g.draw(shape);
		if (mouseHovering) {
			Color c = g.getColor();
			g.setColor(new Color(255, 0, 0));
			g.draw(shape);		g.setColor(c);
			mouseHovering = false;
		}
	}

	public void mouseHover() {
		mouseHovering = true;
	}

	public void onTheWay(Graphics g) {
		Color c = g.getColor();
		g.setColor(new Color(0, 255, 0));
		g.draw(shape);
		g.setColor(c);
	}

	public boolean pointIsInside(int x, int y) {
		return shape.contains(x, y);
	}
	
	@Override
	public void updatePosition(int delta_x, int delta_y) {
		this.posX += delta_x;
		this.posY += delta_y;
		shape = (Polygon) shape.transform(Transform.createTranslateTransform(delta_x, delta_y));
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		updatePosition(delta_pos.x, delta_pos.y);
	}

	@Override
	public Pair getZ() {
		return new Pair(posX, posY + image.getHeight());
	}

	@Override
	public void onWalk() {
		// TODO Auto-generated method stub
		
		// Walking on a tile
		
	}

}
