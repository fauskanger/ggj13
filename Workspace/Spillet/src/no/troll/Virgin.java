package no.troll;

import java.awt.Dimension;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Virgin implements Drawable {

	private int posX;
	private int posY;
	private double delta_posX;
	private double delta_posY;
	private Image image;
	
	public Virgin(Image image, int posX, int posY) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		delta_posX = 0;
		delta_posY = 0;
	}

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
	}

	public Pair update(int delta, Input input) {
		int pixels_per_sec = 50;
		double time = (double)delta / 1000;
		if (input.isKeyDown(Input.KEY_DOWN)) {
			delta_posY += time * pixels_per_sec;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			delta_posY -= time * pixels_per_sec;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			delta_posX -= time * pixels_per_sec;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			delta_posX += time * pixels_per_sec;
		}
		int moveX = (int) delta_posX;
		int moveY = (int) delta_posY;
		delta_posX -= moveX;
		delta_posY -= moveY;
		posX += moveX;
		posY += moveY;
		return new Pair(moveX, moveY);
		
	}

	
}
