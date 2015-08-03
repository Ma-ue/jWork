import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MPtest3 extends JPanel
	implements MouseListener, MouseMotionListener{

	int x0, y0, x1, y1;
	int count = 0;
	int mjLineX[] = new int[65535];
	int mjLineY[] = new int[65535];

	public MPtest3(){
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.white);
	}

	public void mousePressed(MouseEvent e){
		x0 = x1 = e.getX();
		y0 = y1 = e.getY();
	}

	public void mouseDragged(MouseEvent e){
		x1 = e.getX();
		y1 = e.getY();
		lineDraw(getGraphics());
		mjLineX[count] = e.getX();
		mjLineY[count] = e.getY();
 		System.out.println("("+mjLineX[count]+","+mjLineY[count]+")");
		count++;
		x0 = e.getX();
		y0 = e.getY();
	}

	public void mouseClicked(MouseEvent e){
		if(e.getClickCount() == 2) repaint();
	}

	public void mouseEntered(MouseEvent e){
		rectDraw(getGraphics());
	}

	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}

	public void lineDraw(Graphics g){
		g.drawLine(x0, y0, x1, y1);
	}

	public void rectDraw(Graphics g){
		for(int i = 0; i < 10; i++){
			g.drawRect(5+i*100, 5, 100, 100);
		}
	}

	public static void main(String[] args){
		JButton button1;
		MPtest3 mp = new MPtest3();
		JFrame jf = new JFrame("draw with mouse");
		jf.setSize(1025,180);
		jf.setLocation(100,100);
		jf.getContentPane().add(mp, "Center");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		button1 = new JButton("ENTER");
		button1.setSize(100,25);
		button1.setLocation(455,110);
		button1.addActionListener(new ActionAdapter());
		mp.add(button1);
	}
}

class ActionAdapter implements ActionListener{
	public void actionPerformed(ActionEvent ev){
		System.out.println("ﾌｧーｗｗｗｗｗｗｗ");
	}
}





































