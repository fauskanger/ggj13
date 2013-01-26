package no.troll;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile implements Drawable {

	private int posX;
	private int posY;
	private Image image;
	
	public Tile(Image image, int posX, int posY) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY);
	}

	@Override
	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
		this.posX += delta_x;
		this.posY += delta_y;
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		// TODO Auto-generated method stub
		
	}

}
