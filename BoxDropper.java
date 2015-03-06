import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class BoxDropper extends JPanel {
	private Timer animation;
	private BufferedImage offScrImg;
	private Graphics buffer;
	private static BoxDropper bd;
	
	//Initalizing var
	private int NUM_BOXES;
	private FallingBox[] arrayOfBoxes;
	private int currBox;
	private static Container parent;
	private Player player;
	public static InputHandler input;
	public static int boxesPassed;
	public int time;
	private Bullet bullet;
	private boolean fire;
	private int clip;
	private boolean updateBullet;
	private int collisionNum;
	private int index;
	private int delayTicks;
	private static boolean running;
	private UpdateTimer t;
	//Constructer makes the game logic and starts the animation
	public BoxDropper(Container p){
		//start of dec
		running = true;
		index = 0;
		boxesPassed =0;
		NUM_BOXES = 100;
		time = 16;
		delayTicks =5;
		updateBullet = false;
		parent = p;
		clip = 3;
		//end of dec
		//Gets the input
		input = new InputHandler(p);
		//sets the size of the BoxDropper
		this.setSize(parent.getSize());
		offScrImg = new BufferedImage(parent.getWidth(),parent.getHeight(),BufferedImage.TYPE_INT_RGB);
		buffer = offScrImg.createGraphics();
		animation = new Timer(time, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				go();
			}
		});
		currBox = 0;
		
		this.getBoxes();
		
	}
	private void newTimer(BoxDropper bd1){
		t = new UpdateTimer(bd1);
	}
	public void go(){
		repaint();
		if(currBox < NUM_BOXES-1){
		index++;
		if(index%delayTicks==0){
			currBox++;
		for(int i = 0; i < currBox; i++){
			if(!arrayOfBoxes[i].isOnScreen()){
				arrayOfBoxes[i] = new FallingBox(this);
				arrayOfBoxes[i].initialize();
				}}
			}
		}
	}

	public void getBoxes(){
		player = new Player(parent);
		player.initialize();
		arrayOfBoxes = new FallingBox[NUM_BOXES];
		for(int i = 0; i < arrayOfBoxes.length; i++){
			arrayOfBoxes[i] = new FallingBox(this);
			arrayOfBoxes[i].initialize();
		}
		animation.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int s = 1; s <= FallingBox.getMaxSpeed(); s++){
			for(int i = 0; i <= currBox; i++){
				if(arrayOfBoxes[i].getSpeed() == s)
					// paint the box to the graphics context
					buffer = arrayOfBoxes[i].paintBox(buffer);
			}
		}
		//calls to update player
		buffer = player.paintBox(buffer);
		
		//Writes to the screen
		buffer.setColor(Color.blue);
		buffer.setFont(new Font("Dialog", Font.PLAIN, 20));
		buffer.drawString("Score: "+Integer.toString(+boxesPassed), 20, 40);
		
		   g.drawImage(offScrImg, 0, 0, this);
		   fire = Player.fire;
			if(updateBullet){
				bullet.paintBox(g);
				if(collision(bullet.getBounds())){
					updateBullet = false;
					arrayOfBoxes[collisionNum].initialize();
				}
				if(offScreen(bullet.getBounds())){
					updateBullet = false;
				}
			}else{
			if(fire&&clip>0){
				bullet = new Bullet(parent);
				bullet.initialize(player);
				updateBullet = true;
				clip--;
			}}
		   if(collision(player.getBounds())){
			   stopAnimation(g);
		   }
		   
		   if(offScreen(player.getBounds())){
			   stopAnimation(g);
			   	}
		   if(input.r.pressed){
			   stopAnimation(g);
			   restartAnimation();
		   }
	}
	public void paintEndScreen(Graphics g){
		super.paintComponent(g); 
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, this.getWidth(), this.getHeight());
		buffer.setColor(Color.RED);
		buffer.setFont(new Font("Dialog", Font.PLAIN, 100));
		buffer.drawString("Score: "+Integer.toString(+boxesPassed), (this.getWidth()/2)-185, (this.getHeight()/2)-100);
		buffer.setFont(new Font("Dialog", Font.PLAIN, 50));
		buffer.drawString("Press R or / to play again", (this.getWidth()/2)-280, (this.getHeight()/2)+100);
		
		g.drawImage(offScrImg, 0, 0, this);
		
	}
	
	public boolean collision(Rectangle r){
		for(int i = 0; i < arrayOfBoxes.length; i++){
				if(arrayOfBoxes[i].getBounds().intersects(r)){
					collisionNum = i;
					return true;}}
		return false;
	}
	
	public boolean offScreen(Rectangle p){
		if(p.getX()<0||p.getY()<0||p.getY()>parent.getHeight()||p.getX()>parent.getWidth())return true;
		return false;
	}
	
	public void stopAnimation(Graphics g){
		running = false;
		animation.stop();
	   	paintEndScreen(g);
	   	buffer.dispose();
	}
	public static void restartAnimation(){
		parent.remove(bd);
		bd = null;
		bd = new BoxDropper(parent);
		parent.add(bd);
	}
	
	public FallingBox[] getBoxArray(){
		return arrayOfBoxes;
	}
	public static boolean ifRunning(){
		return running;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Falling Boxes");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bd = new BoxDropper(frame);
		frame.add(bd);
		frame.setVisible(true);
	}
}