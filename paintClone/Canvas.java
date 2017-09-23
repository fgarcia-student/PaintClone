package paintClone;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseListener,MouseMotionListener,KeyListener{

	/**
	 * Create the panel.
	 */
	

	protected static Color penAndShapeColor = Color.BLACK;
	protected static int strokeSize;
	protected static Font font;
	protected static FontMetrics fm;
	protected int lastX;
	protected int lastY;
	protected boolean mouseIsPressed;
	protected static String shapeType;
	protected static boolean shapeOn;
	protected int diameter;
	public Canvas() {
		shapeType = "Circle";
		strokeSize = 5;
		penAndShapeColor = Color.BLACK;
		lastX = 0;
		lastY = 0;
		mouseIsPressed = false;
		shapeOn = false;
		font = new Font("Serif", Font.BOLD, strokeSize);
		fm = getFontMetrics(font);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 101, 800, 700);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(shapeOn){
			Graphics g = getGraphics();
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(penAndShapeColor);
			switch(shapeType) {
				case "Circle":{
					drawCircle(g2d);
					break;
				}
				
				case "Square":{
					drawSquare(g2d);
					break;
				}
				
				case "Triangle":{
					drawTriangle(g2d);
					break;
				}
			}
			g.dispose();
			g2d.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseIsPressed = true;
		record(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseIsPressed = false;
		record(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		requestFocus();//request the focus on the jpanel when the mouse enters
		record(e.getX(), e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	protected void record(int x, int y){
		lastX = x;
		lastY = y;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(!shapeOn) {
			mouseIsPressed = true;
			int x = e.getX();
			int y = e.getY();
			Graphics g = getGraphics();
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(penAndShapeColor);
			g2d.setStroke(new BasicStroke(strokeSize));
			g2d.drawLine(lastX, lastY, x, y);
			record(x, y);
			g.dispose();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(!mouseIsPressed){
			String s = String.valueOf(e.getKeyChar());
			font = new Font("Serif", Font.BOLD, strokeSize * 2);
			fm = getFontMetrics(font);
			Graphics g = getGraphics();
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(penAndShapeColor);
			g2d.setFont(font);
			lastX += fm.stringWidth(s) + 5; //increase x by width to move next char over
			g2d.drawString(s, lastX, lastY);
			//going to write all the chars we type on top of each other
			g.dispose();
		}
		
	}

	private void drawCircle(Graphics2D g){
		g.fillOval(lastX - (strokeSize*6)/2, lastY- (strokeSize*6)/2, (strokeSize*6), (strokeSize*6));
	}
	
	private void drawSquare(Graphics2D g) {
		g.fillRect(lastX - (strokeSize*6)/2, lastY - (strokeSize*6)/2, (strokeSize*6), (strokeSize*6));
	}
	
	private void drawTriangle(Graphics2D g) {
		int width = strokeSize * 6;
		int height = strokeSize * 6;
		int[] pX = {lastX-width,lastX,lastX+width};
		int[] pY = {lastY+height,lastY,lastY+height};
		g.fillPolygon(pX,pY,3);
	}
	
}