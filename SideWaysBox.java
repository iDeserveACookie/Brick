import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JComponent;

public class SideWaysBox extends JComponent {	
	private Point xy;
	private Dimension widHt;
	private Color borderColor;
	private Color fillColor;
	private int speed;
	private boolean onScreen;
	private Container parent;
	private static Random rand = new Random();
	private static int MAX_SPEED = 5;
	public void initialize(){
		// this gives each box a unique set of characteristics
		speed = rand.nextInt(MAX_SPEED)+1;
		int colorShift;
		// this makes faster boxes brighter and slower boxes darker
		switch(speed){
		case 5: colorShift = 50;break;
		case 4: colorShift =40;break;
		case 3: colorShift = 30;break;
		case 2: colorShift = 20;break;
		case 1: colorShift = 10;break;
		default: colorShift = 5;break;
		}
		widHt = new Dimension(rand.nextInt(150)+1,rand.nextInt(100)+1);
		
		xy = new Point(rand.nextInt(parent.getWidth()),-widHt.height);
		
		// define the box's outline color
		borderColor = Color.red;
		
		// define the box's fill color
		fillColor = new Color(rand.nextInt(100)+colorShift,0,0);
		
		// now that it exists, make it visible
		onScreen = true;
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
	}
	
	public Graphics paintBox(Graphics g){
		g.setColor(fillColor);
		g.fillRect(xy.x, xy.y, widHt.width, widHt.height);
		g.setColor(borderColor);
		g.drawRect(xy.x, xy.y, widHt.width, widHt.height);
		if(onScreen)
			update();
		else{
			initialize();}
		return g;
			
	}
	public static void addSpeed(){
		MAX_SPEED+=1;
		
	}
	
	public int getSpeed(){
		return speed;
	}
	private void update(){
		xy.y += speed;
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
		if(xy.y > parent.getHeight()){
			BoxDropper.boxesPassed+=1;
			onScreen = false;
			}
	}
	public boolean isOnScreen(){
		return onScreen;
	}

	public static int getMaxSpeed(){
		return MAX_SPEED;
	}
}
