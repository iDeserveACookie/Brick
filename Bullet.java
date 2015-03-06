import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class Bullet extends JComponent{
	private Point xy;
	private Dimension widHt;
	private Color borderColor;
	private Color fillColor;
	private Container parent;
	private final static int speed = 10;
	public int clip=2;
	public Bullet(Container p){
		parent = p;
	}
	public void initialize(Player p){
		widHt = new Dimension(5,5);
		xy = new Point(p.getX()+(p.getWidth()/2-widHt.width/2),p.getY()-5);
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
		borderColor = Color.CYAN;
		fillColor = Color.BLUE;
	}
	public void update(){
		xy.y-=speed;
		this.setBounds(new Rectangle(xy.x,xy.y,widHt.width,widHt.height));
	}
	
	public Graphics paintBox(Graphics g){
		g.setColor(fillColor);
		g.fillRect(xy.x, xy.y, widHt.width, widHt.height);
		g.setColor(borderColor);
		g.drawRect(xy.x, xy.y, widHt.width, widHt.height);
		update();
		return g;
	}

}
