package no.troll;

import org.newdawn.slick.Graphics;

public interface Drawable {
	public void draw(Graphics g);
	public void update(int delta, Pair delta_pos);
	public void updatePosition(int delta_x, int delta_y);
	public Pair getZ();
}
