import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;


public class Player extends JComponent  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point xy;
	private Dimension widHt;
	private Color borderColor;
	private Color fillColor;
	private boolean onScreen;
	private Container parent;	
	private InputHandler i;
	private final static int speed = 5;
	public static boolean fire;
	
	public Player(Container p){
		parent = p;			
		onScreen = false;
		i = BoxDropper.input;
	}
	public void initialize(){
		widHt = new Dimension(20,20);
		xy = new Point(parent.getWidth()/2,parent.getHeight()/2);
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
		borderColor = Color.WHITE;
		fillColor = Color.GRAY;
		onScreen = true;
	}
	public Graphics paintBox(Graphics g){
		g.setColor(fillColor);
		g.fillRect(xy.x, xy.y, widHt.width, widHt.height);
		g.setColor(borderColor);
		g.drawRect(xy.x, xy.y, widHt.width, widHt.height);
		
		update();
		return g;
	}
	public void update(){
		if(i.down.pressed&&i.shift.pressed)xy.y+=speed/2;else if(i.down.pressed)xy.y+=speed;
		if(i.up.pressed&&i.shift.pressed)xy.y-=speed/2;else if(i.up.pressed)xy.y-=speed;
		if(i.right.pressed&&i.shift.pressed)xy.x+=speed/2; else if(i.right.pressed)xy.x+=speed;
		if(i.left.pressed&&i.shift.pressed)xy.x-=speed/2; else if(i.left.pressed)xy.x-=speed;
		if(i.space.pressed){fire = true;}else{fire = false;}
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
	}
	
}
