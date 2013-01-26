package no.troll;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Brick implements Drawable {

	private Image image;
	private int posX;
	private int posY;
	
	private Pair brickTop;
	private Pair brickBottom;
	private Pair brickLeft;
	private Pair brickRight;
	
	public Brick(Image image, int posX, int posY, int xPosForZ) {
		this.posX = posX;
		this.posY = posY;
		this.image = image;
		brickTop = new Pair(posX + image.getWidth() - xPosForZ, getZ().y - image.getWidth() / 2);
		brickBottom = new Pair(posX + xPosForZ, getZ().y);
		brickLeft = new Pair(posX, getZ().y - xPosForZ / 2);
		brickRight = new Pair(posX + image.getWidth(), getZ().y - (brickTop.x - posX) / 2);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY);
		g.drawLine(brickTop.x, brickTop.y, brickLeft.x, brickLeft.y);
		g.drawLine(brickTop.x, brickTop.y, brickRight.x, brickRight.y);
		g.drawLine(brickBottom.x, brickBottom.y, brickLeft.x, brickLeft.y);
		g.drawLine(brickBottom.x, brickBottom.y, brickRight.x, brickRight.y);
	}

	@Override
	public void update(int delta, Pair delta_pos) {
		updatePosition(delta_pos.x, delta_pos.y);
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
		posX += delta_x;
		posY += delta_y;
		brickTop.x += delta_x;
		brickTop.y += delta_y;		
		brickRight.x += delta_x;
		brickRight.y += delta_y;
		brickBottom.x += delta_x;
		brickBottom.y += delta_y;
		brickLeft.x += delta_x;
		brickLeft.y += delta_y;
	}
	
	@Override
	public Pair getZ() {
		return new Pair(posX, posY + image.getHeight());
	}


	
}
