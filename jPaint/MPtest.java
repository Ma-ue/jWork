//MousePaint test
//枠＆マウス＆仕事をしないボタン

import java.awt.event.*;
import java.awt.*;
import java.applet.*;

public class MPtest extends Applet
	implements MouseListener,
		MouseMotionListener, ActionListener{
	int x1, y1;
	int x2, y2;
	
	public MPtest(){
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
		x1 = x2 = e.getX();
		y1 = y2 = e.getY();
		paint(getGraphics());
	}

	public void mouseDragged(MouseEvent e){
		e.consume();
		x2 = e.getX();
		y2 = e.getY();
		paint(getGraphics());

		x1 = e.getX();
		y1 = e.getY();
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
		g.drawLine(x1,y1,x2,y2);
		for(int i=0; i<14; i++){
			g.drawRect(i*80+5,50,80,80);
		}
	}
}
