package no.troll;

import org.newdawn.slick.Graphics;

public interface Drawable {
	public void draw(Graphics g);
	public void update(int delta, Pair delta_pos);
	public void setPosition(int x, int y);
	public void updatePosition(int delta_x, int delta_y);
}
