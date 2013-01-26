package no.troll;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Brick implements Drawable {

	private Image image;
	private int posX;
	private int posY;
	
	private Polygon shape;
	
	public Brick(Image image, int posX, int posY, int xPosForZ) {
		this.posX = posX;
		this.posY = posY;
		this.image = image;
		shape = new Polygon();
		shape.addPoint(posX + image.getWidth() - xPosForZ, getZ().y - image.getWidth() / 2);
		shape.addPoint(posX, getZ().y - xPosForZ / 2);
		shape.addPoint(posX + xPosForZ, getZ().y);
		shape.addPoint(posX + image.getWidth(), getZ().y - (posX + image.getWidth() - xPosForZ - posX) / 2);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY);
		g.draw(shape);
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		updatePosition(delta_pos.x, delta_pos.y);
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
		posX += delta_x;
		posY += delta_y;
		shape = (Polygon) shape.transform(Transform.createTranslateTransform(delta_x, delta_y));
	}
	
	@Override
	public Pair getZ() {
		return new Pair(posX, posY + image.getHeight());
	}


	
}
