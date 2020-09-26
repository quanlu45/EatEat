package com.eateat.quan.yantuanzi;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.eateat.quan.res.Resources;

public class MyPanel extends JPanel{

	private YanTuanzi yanTuanzi;
	private Ship ship;           
	private Image buffImage;
	private Graphics buffg;
	private SmilingFace[] faces =new SmilingFace[8];
	private ExecutorService threadpool;
	public   MyPanel()  {
		super();
		ship = new  Ship();
		yanTuanzi = new YanTuanzi(650,700);
		threadpool = Executors.newFixedThreadPool(12);

	    for (int i = 0; i < faces.length; i++) {
			faces[i] =new SmilingFace();
	      }
		this.setFocusable(true); 
		this.addKeyListener(new MykeyListener());
		this.startThreadPool(); 
	}
	
	//�����̳߳�
	private void startThreadPool() {
		for (int i = 0; i < faces.length; i++) {
			threadpool.execute(faces[i]);
		}
		threadpool.execute(ship);
		
		//�ύ����������
		threadpool.execute(new Runnable(){
			public void run() {
				while(yanTuanzi.isAlive())
				{	
					 try {
					      Thread.sleep(5);
					     } catch (InterruptedException e) {
					      e.printStackTrace();
					     }
					  repaint();
			}}});
		
		//�ύ�������ºͼ��������
		threadpool.execute(new Runnable() {
			public void run() {
				while (yanTuanzi.isAlive()) {
					yanTuanzi.checkScores(faces);
					yanTuanzi.isHit(ship.getMissiles());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				ship.setStop(true);
				JOptionPane.showMessageDialog(null, "Game Over!", null,JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
	}
	
	
	//˫�����ͼ
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (buffImage==null) {
			buffImage =this.createImage(getWidth(), getHeight());
			buffg =buffImage.getGraphics();
		}
		buffg.setColor(getBackground());
		buffg.fillRect(0, 0, getWidth(), getHeight());
		buffg.drawImage(Resources.backgroundImage, 0, 0, 1280,960,null);
		yanTuanzi.paint(buffg);
		for (int i=0 ;i<faces.length;i++) {
				faces[i].paint(buffg);
		}
		
		ship.paint(buffg);
		g.drawImage(buffImage, 0, 0,null);
		
	}


	//�����ӵĶ�������
	class MykeyListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO �Զ����ɵķ������

			if (e.getKeyCode() ==KeyEvent.VK_LEFT) {
				yanTuanzi.move(Direction.LEFT);
			}
			else if (e.getKeyCode() ==KeyEvent.VK_RIGHT) {
				yanTuanzi.move(Direction.RIGHT);
			} 
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO �Զ����ɵķ������
			
		}
		
	}
}


