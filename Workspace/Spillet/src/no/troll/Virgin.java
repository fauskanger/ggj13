package no.troll;

import java.util.ArrayList;
import java.util.HashMap;

import no.troll.Resources.AttackImageName;
import no.troll.Resources.CharacterImageName;
import no.troll.Resources.SoundName;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;


public class Virgin implements Drawable {

	private enum MoveDirection { UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, STILL };
	private enum Animation { NONE, WEAPON }

	private int posX;
	private int posY;
	private double delta_posX;
	private double delta_posY; 
	private boolean lastWeaponState;
	private ArrayList<Brick> fixedObjects;

	private HashMap<MoveDirection, Image[]> images;	
	private HashMap<Animation, Image[]> animations;
	private Resources resources;

	private Animation currentAnimation;
	private int angle = 45;
	private SoundManager Lyd; 

	boolean oldMoseState;
	
	private Brick lastCollisionBrick;

	private int timePerSpriteLoop; // in msec
	private int deltaTimeSpriteLoop; // in msec
	private int animPerSpriteLoop;
	private int animTimeSpriteLoop;
	
	private int freeZoneTop;
	private int freeZoneRight;
	private int freeZoneBottom;
	private int freeZoneLeft;

	private Polygon shape;

	private MoveDirection currentMoveDirection;
	private int currentSpriteFrame;
	
	private TileManager tileManager;
	private Graphics ggg;

	private int currentAnimationSpriteFrame;


	public Virgin(Resources resources, int posX, int posY, int[] freeZone, ArrayList<Brick> fixedObjects, TileManager tileManager) {
		this.tileManager = tileManager;
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

		oldMoseState = false;
		addImages();
		
		ggg = null;

		lastWeaponState = false;
		currentAnimation = Animation.NONE;
		animations = new HashMap<Virgin.Animation, Image[]>();
		
		AddAnimationImages();
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
	
	private void AddAnimationImages() {
		Image[] arry = new Image[1];
		arry[0] = resources.getAttackImage(AttackImageName.att_1);
		animations.put(Animation.NONE, arry);
		arry = new Image[3];
		arry[0] = resources.getAttackImage(AttackImageName.att_rot1);
		arry[1] = resources.getAttackImage(AttackImageName.att_rot2);
		arry[2] = resources.getAttackImage(AttackImageName.att_rot3);
		animations.put(Animation.WEAPON, arry);
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
		if (ggg != null) {
			ggg = g;
		}

		drawTileLines(g);
		//		System.out.println("Koza: " + currentMoveDirection + " " + currentSpriteFrame);

		Image i = images.get(currentMoveDirection)[currentSpriteFrame];
		g.drawImage(i, posX-25, posY+10);
		
		if(currentAnimation!=Animation.NONE)
		{
			Image a = animations.get(currentAnimation)[currentAnimationSpriteFrame];
			Pair rotasjon = rotasjon(); 
			a.rotate(rotasjon.x);
			g.drawImage(a,getZ().x-a.getWidth()+rotasjon.y,getZ().y-a.getHeight()); 
			a.rotate(-rotasjon.x);
		}

		//g.drawImage(image, posX, posY);
	}
	
	public Pair rotasjon()
	{
		switch(currentMoveDirection)
		{
		case UP: 
		return new Pair(0,20);
		case DOWN:
			return new Pair(180,3);
		case DOWNRIGHT:
			return new Pair(-135,3);
		case DOWNLEFT:
			return new Pair(45,3);
		case LEFT:
			return new Pair(90,3);
		case UPLEFT:
			return new Pair(135,3);
		case UPRIGHT:
			return new Pair(-45,3);
		}
		return new Pair(0,0);
	}

	private void drawTileLines(Graphics g) {
		g.draw(shape);
	}
	
	
	@Override
	public void updatePosition(int delta_x, int delta_y) {
		this.posX += delta_x;
		this.posY += delta_y;
		translatePolygon(delta_x, delta_y);
	}

	@Override
	public void update(int delta, Pair delta_pos) {
	}



	public void attack(Input input)
	{		
		boolean currentWeaponState= input.isKeyDown(Input.KEY_X);
		
		if(currentWeaponState&&!lastWeaponState)
		{			
		
			Sound att = resources.getSound(SoundName.Sword); 
			if(!att.playing())
			{
				att.play(1.0f,0.2f);
			
			}
			
			currentAnimation = Animation.WEAPON;
		}
		lastWeaponState = currentWeaponState;
	}
	
	public Pair update(int delta, Input input) {
		testMus(input);
		int pixels_per_sec = 150;
		double time = (double)delta / 1000;
		double pixels = time * pixels_per_sec;

		int buttons = 0;
		boolean moveUp = false;
		boolean moveDown = false;
		boolean moveLeft = false;
		boolean moveRight = false;
		boolean attack = false;

		attack(input);

		if (input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft = true;
			buttons = 1;
		}
		else if (input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight = true;
			buttons = 1;
		}
		else if (input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)) {
			moveDown = true;
			buttons = 1;
		}
		else if (input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)) {
			moveUp = true;
			buttons = 1;
		}

		else if (input.isKeyDown(Input.KEY_UP)) {
			moveUp = true;
			moveLeft = true;
			buttons = 2;
		}
		else if (input.isKeyDown(Input.KEY_DOWN)) {
			moveDown = true;
			moveRight = true;
			buttons = 2;
		}
		else if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft = true;
			moveDown = true;
			buttons = 2;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight = true;
			moveUp = true;
			buttons = 2;
		}
		if(input.isKeyDown(Input.KEY_X))
			attack = true;
		
		currentMoveDirection = getCurrentMoveDirection(moveUp, moveDown, moveLeft, moveRight,attack);
		int numberOfSpriteElements = images.get(currentMoveDirection).length;
		deltaTimeSpriteLoop += delta;
		if (deltaTimeSpriteLoop > timePerSpriteLoop / numberOfSpriteElements) {
			currentSpriteFrame = (currentSpriteFrame + 1);
			deltaTimeSpriteLoop -= timePerSpriteLoop / numberOfSpriteElements;
		}
		currentSpriteFrame %= numberOfSpriteElements;

		numberOfSpriteElements = animations.get(currentAnimation).length;
		animTimeSpriteLoop += delta;
		if(animTimeSpriteLoop>animPerSpriteLoop/numberOfSpriteElements){
			currentAnimationSpriteFrame = (currentAnimationSpriteFrame +1);
			animTimeSpriteLoop -= animPerSpriteLoop/numberOfSpriteElements;
		}
		if(currentAnimationSpriteFrame==numberOfSpriteElements)
		{currentAnimation=Animation.NONE;
		currentAnimationSpriteFrame=0;
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

		updatePosition(moveX, moveY);

		if (posX < 0) {
			updatePosition(-posX, 0);
		}
		if (posX > Program.windowWidth - 100) {
			updatePosition(Program.windowWidth - 100 - posX, 0);
		}

		int correctX = 0;
		int correctY = 0;

		if (collisionDetection(currentMoveDirection)) {			

			int dy,dx;
			boolean isAboveMiddle, isRight;
			boolean randomBool = (Math.random()>=0.5);
			
			boolean previousIsAboveMiddle;
			boolean previousIsRight;
			

			previousIsRight = isRight = (getZ().x > lastCollisionBrick.getZ().x);
			previousIsAboveMiddle = isAboveMiddle = (getZ().y < lastCollisionBrick.getZ().y);
			
			while(collisionDetection(currentMoveDirection)) {
				
				isRight = (getZ().x > lastCollisionBrick.getZ().x);
				isAboveMiddle = (getZ().y < lastCollisionBrick.getZ().y);
				
				if (isRight!=previousIsRight || isAboveMiddle!=previousIsAboveMiddle)
					break;
				
				if (isRight)
				{
					switch (currentMoveDirection)
					{
					case UP:
						dx = 1;
						dy = 1;
						break;
					case DOWN:
						dx = 1;
						dy = -1;
						break;
					case DOWNLEFT:
						dx = 1; 
						dy = -1;
						break;
					case UPLEFT:
						dx = 1; 
						dy = 1;
						break;
					case LEFT:
						if(isAboveMiddle) {
							dx = 1;
							dy = -1;
						} else {
							dx = 1;
							dy = 1;
						}
						break;
					default:
						dx = moveX>0? 1:-1;
						dy = moveY>0? 1:-1;
						System.out.println("ERROR! Switch resulted in default on isRight collision " +  currentMoveDirection);
//						System.exit(0);
						break;
					}
				}
				else // !isRight => isLeft
				{
					switch (currentMoveDirection)
					{
					case UP:
						dx = -1;
						dy = 1;
						break;
					case DOWN:
						dx = -1;
						dy = -1;
						break;
					case RIGHT:
						if (isAboveMiddle) {
							dx = -1;
							dy = -1;
						} else {
							dx = -1;
							dy = 1;
						}
						break;
					case UPRIGHT:
						dx = -1;
						dy = 1;
						break;
					case DOWNRIGHT:
						dx = -1;
						dy = -1;
						break;
					default:
						dx = moveX>0? 1:-1;
						dy = moveY>0? 1:-1;
						System.out.println("ERROR! Switch resulted in default on isLeft collision " + currentMoveDirection);
//						System.exit(0);
						break;
					}
				}

				updatePosition(dx, dy);
				previousIsAboveMiddle = isAboveMiddle;
				previousIsRight = isRight;
			}	
		}

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

		updatePosition(correctX, correctY);

		
		return new Pair(correctX, correctY);
	}
	
	private void testMus(Input input) {
		boolean newMouseState = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		int mx = input.getMouseX();
		int my = input.getMouseY();
		mouseHover(mx, my);
		if (!oldMoseState && newMouseState) {
			mouseClick(mx, my);
		}
		oldMoseState = newMouseState;
	}

	private void mouseClick(int mouseX, int mouseY) {
		Tile[] tiles = tileManager.mouseClick(mouseX, mouseY);
	}
	
	private void mouseHover(int mouseX, int mouseY) {
		tileManager.mouseHover(mouseX, mouseY);
	}

	private void translatePolygon(int moveX, int moveY) {
		shape = (Polygon) shape.transform(Transform.createTranslateTransform(moveX, moveY));
	}

	private MoveDirection getCurrentMoveDirection(boolean moveUp, boolean moveDown,	boolean moveLeft, boolean moveRight,boolean attack) {
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
		return new Pair(posX+Program.tileWidth/2, posY + images.get(MoveDirection.STILL)[0].getHeight());
	}

	boolean collisionDetection(MoveDirection moveDirection) {
		for(Brick b: fixedObjects) {
			if(b.shape.intersects(shape)) {
				lastCollisionBrick = b;
				return true;
			}
		}
		return false;
	}

	@Override
	public void onWalk() {
		// TODO Auto-generated method stub
		
		// Walking on Virgin
		
	}




}
