package no.troll;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class InputManager {

	GameContainer gc;
	
	public boolean upDown;
	public boolean key;
	
	public InputManager(GameContainer gc) {
		this.gc = gc;
	}

	public void update(int delta) {
		Input input = gc.getInput();
		int r = 4;
		if(input.isKeyDown(Input.KEY_UP)){r -=1;}
		if(input.isKeyDown(Input.KEY_DOWN)){r ++;}
		if(input.isKeyDown(Input.KEY_LEFT)){r -=1;}
		if(input.isKeyDown(Input.KEY_RIGHT)){r ++;}
	}
	
}
