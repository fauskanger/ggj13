package no.troll;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Wall implements Drawable {

	private Image image;
	private int posX;
	private int posY;
	
	public Wall(Image image, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.image = image;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY);
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		posX += delta_pos.x;
		posY += delta_pos.y;
	}

	@Override
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
		posX += delta_x;
		posY += delta_y;
	}
	
	@Override
	public Pair getZ() {
		return new Pair(posX, posY + image.getHeight());
	}


	
}
