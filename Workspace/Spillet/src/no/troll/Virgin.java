package no.troll;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Virgin implements Drawable {
	
	private enum MoveDirection {UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT};
	private int posX;
	private int posY;
	private double delta_posX;
	private double delta_posY; 
	private Image image;
	
	private ArrayList<Drawable> fixedObjects;
	
	private int freeZoneTop;
	private int freeZoneRight;
	private int freeZoneBottom;
	private int freeZoneLeft;

	
	public Virgin(Image image, int posX, int posY, int[] freeZone, ArrayList<Drawable> fixedObjects) {
		this.fixedObjects = fixedObjects;
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		delta_posX = 0;
		delta_posY = 0;
		
		freeZoneTop = freeZone[0];
		freeZoneRight = freeZone[1];
		freeZoneBottom = freeZone[2];
		freeZoneLeft = freeZone[3];

	}

	public void draw(Graphics g) {
//		g.drawImage(image, getScreenPos().x, getScreenPos().y);
		g.drawImage(image, posX, posY);
	}

	@Override
	public void updatePosition(int delta_x, int delta_y) {
		this.posX += delta_x;
		this.posY += delta_y;
	}

	@Override
	public void update(int delta, Pair delta_pos) {
	}

//	private Pair getScreenPos() {
//	    int retX = posX * (Program.tileWidth / 2) + posY * -Program.tileHeight;
//	    int retY = posX * (Program.tileHeight / 2) + posY * (Program.tileHeight / 2); 		
//		return new Pair(retX, retY);
//		
//	}
	
	public Pair update(int delta, Input input) {
		int pixels_per_sec = 150;
		double time = (double)delta / 1000;
		double pixels = time * pixels_per_sec;
		
		int buttons = 0;
		boolean moveUp = false;
		boolean moveDown = false;
		boolean moveLeft = false;
		boolean moveRight = false;
	
		if (input.isKeyDown(Input.KEY_UP)) {
			moveUp = true;
			buttons++;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			moveDown = true;
			buttons++;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft = true;
			buttons++;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight = true;
			buttons++;
		}
		
		if (buttons > 1) {
			if (moveDown == moveLeft) { //Moving left/right
				pixels *= 0.6;
			}
		}
		
		if (moveDown) {
			delta_posX -= pixels * 2;
			delta_posY += pixels;
		}
		if (moveUp) {
			delta_posX += pixels * 2;
			delta_posY -= pixels;
		}
		if (moveLeft) {
			delta_posX -= pixels * 2;
			delta_posY -= pixels;
		}
		if (moveRight) {
			delta_posX += pixels * 2;
			delta_posY += pixels;
		}
		int moveX = (int) delta_posX;
		int moveY = (int) delta_posY;
		delta_posX -= moveX;
		delta_posY -= moveY;
		posX += moveX;
		posY += moveY;

		MoveDirection currentMoveDirection = getCurrentMoveDirection(moveUp, moveDown, moveLeft, moveRight);
		
		collisionDetection(currentMoveDirection);
		
				
		int correctX = 0;
		int correctY = 0;
		if (posY < freeZoneTop) {
			correctY = freeZoneTop - posY;
		}
		if (posY > freeZoneBottom) {
			correctY = freeZoneBottom - posY;
		}
		if (posX < freeZoneLeft) {
			correctX = freeZoneLeft - posX;
		}
		if (posX > freeZoneRight) {
			correctX = freeZoneRight - posX;
		}
		posX += correctX;
		posY += correctY;
		return new Pair(correctX, correctY);
	}

	private MoveDirection getCurrentMoveDirection(boolean moveUp, boolean moveDown,	boolean moveLeft, boolean moveRight) {
	
		return null;
	}

	@Override
	public Pair getZ() {
		return new Pair(posX, posY + image.getHeight());
	}
	
	void collisionDetection(MoveDirection moveDirection) {
		
	}

	
}
