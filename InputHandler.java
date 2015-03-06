import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener{
	public InputHandler(Container game){
		game.addKeyListener(this);
	}
	public class Key{
		public boolean pressed = false;
		
		public void toggle(boolean isPressed){
			pressed = isPressed;
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key shift = new Key();
	public Key space = new Key();
	public Key r = new Key();
	
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
		
	}

	public void keyTyped(KeyEvent e) {

		
	}
	
	public void toggleKey(int keyCode, boolean isPressed){
		if(keyCode==KeyEvent.VK_W||keyCode==KeyEvent.VK_UP){up.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_A||keyCode==KeyEvent.VK_LEFT){left.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_S||keyCode==KeyEvent.VK_DOWN){down.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_D||keyCode==KeyEvent.VK_RIGHT){right.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_SHIFT){shift.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_SPACE){space.toggle(isPressed);}
		if(keyCode==KeyEvent.VK_R||keyCode==KeyEvent.VK_SLASH){r.toggle(isPressed);
		if(!BoxDropper.ifRunning()){
			BoxDropper.restartAnimation();
			} 
		}
	}
}
