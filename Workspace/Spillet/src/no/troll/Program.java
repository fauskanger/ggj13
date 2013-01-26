package no.troll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {
	
	Image testImg;
	int X = 200;
	int Y = 200;

	public Program() 
	{
		super("Hello World");
		
	}

	public static void main(String[] args) throws SlickException 
	{
		AppGameContainer app = new AppGameContainer(new Program());
		 
	     app.setDisplayMode(800, 600, false);
	     app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Hello World", 100, 100);
		g.drawImage(testImg,X,Y);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		testImg=new Image("C:\\Users\\Guchoo\\Pictures\\Ipod\\Noodle-gorillaz.jpg");
				
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){Y -=1;}
		if(input.isKeyDown(Input.KEY_DOWN)){Y ++;}
		if(input.isKeyDown(Input.KEY_LEFT)){X -=1;}
		if(input.isKeyDown(Input.KEY_RIGHT)){X ++;}

	}

}
