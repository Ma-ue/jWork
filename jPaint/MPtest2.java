//MousePaint test
//枠＆マウス＆仕事をしないボタン

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.util.Vector;

public class MPtest2 extends Applet
	implements MouseListener,
		MouseMotionListener, ActionListener{
	Vector shapes = new Vector();
	int x1, y1;
	
	public MPtest2(){
		setForeground(Color.black);
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
		Button but1 = (Button)add(new Button("Input"));
		but1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
	}		

	public void mousePressed(MouseEvent e){
		e.consume();
		x1 = e.getX();
		y1 = e.getY();
		shapes.addElement(new Rectangle(x1,y1,x1,y1));
		paint(getGraphics());
	}

	public void mouseDragged(MouseEvent e){
		e.consume();
		shapes.addElement(new Rectangle(x1,y1,e.getX(),e.getY()));
		x1 = e.getX();
		y1 = e.getY();
		paint(getGraphics());
	}
	
	public void mouseMoved(MouseEvent e){
	}

	public void mouseReleased(MouseEvent e){
	}

	public void mouseEntered(MouseEvent e){
	}

	public void mouseExited(MouseEvent e){
	}

	public void mouseClicked(MouseEvent e){
	}

	public void paint(Graphics g){
		int number = shapes.size();
		for(int i=0; i<number; i++){
			Rectangle data = (Rectangle)shapes.elementAt(i);
			g.drawLine(data.x,data.y,data.width,data.height);
		}
		for(int i=0; i<14; i++){
			g.drawRect(i*80+5,50,80,80);
		}
		g.drawString("にょわあああああ",10,200);
	}
}
