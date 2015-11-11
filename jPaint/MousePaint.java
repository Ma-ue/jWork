import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MousePaint extends Frame
	implements MouseListener, MouseMotionListener{

	/***各種変数定義***/
	Button button1 = new Button("ENTER");		//書き終わり用のボタン(button1)
	int x, y;									//取得する座標(x, y)
	int disArr[][] = new int[30][30];			//出力用配列(disArr)
	int compX, compY;							//圧縮後の座標(compX, compY)
	int r = 10;									//ペン半径＆判定用
	
	int count = 0;								//mouseDraggedが呼びだされた回数
	int logSampling = 0;						//表示を間引くための変数
	int lineNo = 0;								//ひいた線の数(押されて離された回数)
	int mjLineX[] = new int[65535];				//x座標表示用の配列
	int mjLineY[] = new int[65535];				//y座標表示用の配列

	/***コンストラクタ***/
	public MousePaint(){
		super("Mouse Paint");
		setSize(300,350);
		setLocation(100,100);
		setBackground(Color.white);
		
		/*マウスリスナー*/
		addMouseListener(this);
		addMouseMotionListener(this);
		
		/*ボタン設置*/
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		panel.add(button1);
		
		/*ウィンドウリスナー*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setVisible(true);
	}

	/***マウスが押された時***/
	public void mousePressed(MouseEvent e){
		x = e.getX();
		y = e.getY();
		System.out.println("Start x:"+e.getX()+" y:"+e.getY()+" Line:"+lineNo);
		lineNo++;
	}

	/***マウスがドラッグされた時***/
	public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
		lineDraw(getGraphics());
		mjLineX[count] = x;
		mjLineY[count] = y;
		if(logSampling % 10 == 0){
			System.out.println("("+mjLineX[count]+","+mjLineY[count]+")");
		}
		count++;
		logSampling++;
		
		/*座標を10で割って丸める(圧縮)*/
		compX = (int)Math.floor(((double)x)/10);
		compY = (int)Math.floor(((double)y-20)/10);
		
		/*丸めた座標を中心に半径rまでをペン太さとして認識*/
		for(int inX = -10; inX < 10; inX++){
			for(int inY = -10; inY < 10; inY++){
				if(inX*inX+inY*inY < r*r){
					compX = (int)Math.floor(((double)x+inX)/10);
					compY = (int)Math.floor(((double)y+inY-20)/10);
					disArr[compX][compY] = 1;
				}
			}
		}
	}

	/***マウスがクリックされた時***/
	public void mouseClicked(MouseEvent e){
		/*クリック2回(=ダブルクリック)の時*/
		if(e.getClickCount() == 2){
			for(int i = 0; i < 30 ; i++){
				for(int j = 0; j < 30 ; j++){
					System.out.print(disArr[j][i] + " ");
				}
				System.out.println("");
			}
			repaint();
		}
	}

	/***マウスが離れた時***/
	public void mouseReleased(MouseEvent e){
		System.out.println("Line:"+lineNo+" End");
	}

	public void mouseExited(MouseEvent e){}		//マウスが領域外に出た時
	public void mouseMoved(MouseEvent e){}		//マウスが動いた時
	public void mouseEntered(MouseEvent e){}	//領域内にマウスが入った時
	
	/***x,yを中心とした半径rの円を描画***/
	public void lineDraw(Graphics g){
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}
		
	/***メインクラス***/
	public static void main(String[] args){
		Frame w = new MousePaint();
		w.setVisible(true);
	}

	/***取得したデータを渡す***/
	public void sendData(){
		//ファイル送信
	}
		
	/***ボタンが押された時***/
	public boolean action(Event ev, Object o){
		if(o.equals("ENTER")){
			for(int i = 0; i < 30 ; i++){
					for(int j = 0; j < 30 ; j++){
						System.out.print(""+disArr[j][i] + " ");
						//textに落としこむ
						disArr[j][i] = 0;
					}
					System.out.println("");
				}
			sendData();
			repaint();
		}
		return true;
	}
}