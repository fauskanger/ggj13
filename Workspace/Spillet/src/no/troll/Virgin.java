package no.troll;

import java.util.ArrayList;
import java.util.HashMap;

import no.troll.Resources.CharacterImageName;
import no.troll.Resources.SoundName;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;


public class Virgin implements Drawable {

	private enum MoveDirection {UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, STILL};

	private int posX;
	private int posY;
	private double delta_posX;
	private double delta_posY; 

	private ArrayList<Brick> fixedObjects;

	private HashMap<MoveDirection, Image[]> images;	
	private Resources resources;

	private boolean attack = false; 
	private SoundManager Lyd; 


	private int timePerSpriteLoop; // in msec
	private int deltaTimeSpriteLoop; // in msec

	private int freeZoneTop;
	private int freeZoneRight;
	private int freeZoneBottom;
	private int freeZoneLeft;
	
	private Polygon shape;

	private MoveDirection currentMoveDirection;
	private int currentSpriteFrame;

	public Virgin(Resources resources, int posX, int posY, int[] freeZone, ArrayList<Brick> fixedObjects) {
		this.resources = resources;
		this.fixedObjects = fixedObjects;
		this.images = new HashMap<Virgin.MoveDirection, Image[]>();
		this.posX = posX;
		this.posY = posY;
		delta_posX = 0;
		delta_posY = 0;
		currentMoveDirection = MoveDirection.STILL;
		currentSpriteFrame = 0;
		timePerSpriteLoop = 500;
		deltaTimeSpriteLoop = 0;
		
		addImages();
		
		int bottomY = posY + images.get(MoveDirection.STILL)[0].getHeight();
		int topY = bottomY - Program.tileHeight;
		int leftY = bottomY - Program.tileHeight/2;
		int rightY = leftY;

		int bottomX = posX + Program.tileWidth/2;
		int leftX = posX;
		int rightX = posX + Program.tileWidth;
		int topX = bottomX;
		shape = new Polygon();
		shape.addPoint(bottomX, bottomY);
		shape.addPoint(rightX, rightY);
		shape.addPoint(topX, topY);
		shape.addPoint(leftX, leftY);


		freeZoneTop = freeZone[0];
		freeZoneRight = freeZone[1];
		freeZoneBottom = freeZone[2];
		freeZoneLeft = freeZone[3];
	}

	private void addImages() {
		Image[] arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_UP_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_UP_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_UP_3);
		images.put(MoveDirection.UP, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_DOWN_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_DOWN_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_DOWN_3);
		images.put(MoveDirection.DOWN, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_LEFT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_LEFT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_LEFT_3);
		images.put(MoveDirection.LEFT, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_RIGHT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_RIGHT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_RIGHT_3);
		images.put(MoveDirection.RIGHT, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_UPLEFT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_UPLEFT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_UPLEFT_3);
		images.put(MoveDirection.UPLEFT, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_UPRIGHT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_UPRIGHT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_UPRIGHT_3);
		images.put(MoveDirection.UPRIGHT, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_DOWNLEFT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_DOWNLEFT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_DOWNLEFT_3);
		images.put(MoveDirection.DOWNLEFT, arr);

		arr = new Image[3];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_DOWNRIGHT_1);
		arr[1] = resources.getCharacter(CharacterImageName.Virgin_DOWNRIGHT_2);
		arr[2] = resources.getCharacter(CharacterImageName.Virgin_DOWNRIGHT_3);
		images.put(MoveDirection.DOWNRIGHT, arr);

		arr = new Image[1];
		arr[0] = resources.getCharacter(CharacterImageName.Virgin_STILL);
		images.put(MoveDirection.STILL, arr);
	}

	public void draw(Graphics g) {

		//		g.drawImage(image, getScreenPos().x, getScreenPos().y);


		drawTileLines(g);
		//		System.out.println("Koza: " + currentMoveDirection + " " + currentSpriteFrame);

		Image i = images.get(currentMoveDirection)[currentSpriteFrame];
		g.drawImage(i, posX-25, posY+10);

		//g.drawImage(image, posX, posY);
	}

	private void drawTileLines(Graphics g) {
		g.draw(shape);
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
	
	public void attack(Input input)
	{		
		if(input.isKeyDown(Input.KEY_X))
		{
			attack = true;
			
			Sound att = resources.getSound(SoundName.Sword); 
			if(!att.playing())
			{
				att.play(1.0f,0.2f);
			}
			//Bilde av angrep
			
		}
	}

	public Pair update(int delta, Input input) {
		int pixels_per_sec = 150;
		double time = (double)delta / 1000;
		double pixels = time * pixels_per_sec;

		int buttons = 0;
		boolean moveUp = false;
		boolean moveDown = false;
		boolean moveLeft = false;
		boolean moveRight = false;

		attack(input);
	
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

		currentMoveDirection = getCurrentMoveDirection(moveUp, moveDown, moveLeft, moveRight);
		int numberOfSpriteElements = images.get(currentMoveDirection).length;
		deltaTimeSpriteLoop += delta;
		if (deltaTimeSpriteLoop > timePerSpriteLoop / numberOfSpriteElements) {
			currentSpriteFrame = (currentSpriteFrame + 1);
			deltaTimeSpriteLoop -= timePerSpriteLoop / numberOfSpriteElements;
		}
		currentSpriteFrame %= numberOfSpriteElements;

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
		

		shape = (Polygon) shape.transform(Transform.createTranslateTransform(moveX+correctX, moveY+correctY));
		
		return new Pair(correctX, correctY);
	}

	private MoveDirection getCurrentMoveDirection(boolean moveUp, boolean moveDown,	boolean moveLeft, boolean moveRight) {
		if (moveUp && moveLeft) {
			return MoveDirection.UP;
		}
		else if (moveDown && moveRight) {
			return MoveDirection.DOWN;
		}
		else if (moveDown && moveLeft) {
			return MoveDirection.LEFT;
		}
		else if (moveUp && moveRight) {
			return MoveDirection.RIGHT;
		}
		else if (moveUp) {
			return MoveDirection.UPRIGHT;
		}
		else if (moveRight) {
			return MoveDirection.DOWNRIGHT;
		}
		else if (moveDown) {
			return MoveDirection.DOWNLEFT;
		}
		else if (moveLeft) {
			return MoveDirection.UPLEFT;
		}
		return MoveDirection.STILL;
	}

	@Override
	public Pair getZ() {
		return new Pair(posX, posY + images.get(MoveDirection.STILL)[0].getHeight());
	}

	void collisionDetection(MoveDirection moveDirection) {
		for(Brick b: fixedObjects) {
			if(b.shape.intersects(shape)) {
				System.out.println("COLLISION!!");
			}
		}
	}




}
