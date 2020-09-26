package com.eateat.quan.yantuanzi;
import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eateat.quan.res.Resources;

/**
 * 
 * @author quan
 * �ɴ���
 */
public class Ship implements Runnable{
	private int x=10;             //x����
	private int y=40;            //y����
	private int v=4;            //�ٶ�
	private boolean isStop=false; //ֹͣ��־λ
	private Missile[] missiles =new Missile[5];      //�巢����
	private ExecutorService missilesthreadpool;
	
	
	public Ship() {
		for (int i = 0; i < 5; i++) {
			missiles[i] = new Missile();
		}
		missilesthreadpool =Executors.newFixedThreadPool(5);
	}
	
	public void setStop(boolean flag) {
		isStop =flag;
	}
	
	
	
	public void paint(Graphics g) {
		if (v>0) {
			g.drawImage(Resources.rightshipImage, x, y, 100, 70, null);
		}else {
			g.drawImage(Resources.leftshipImage, x, y, 100, 70, null);
		}
		for (Missile missile : missiles) {
			if (missile.isAlive()) {
				missile.paint(g);
			}
		}
		
	}
	
	public Missile[] getMissiles() {
		return missiles;
	}
	
	//���䵼��
	private void Shit() {
		for (int i = 0; i < 5; i++) {
			if (!missiles[i].isAlive()) {
				missiles[i].setVx(v);
				missiles[i].setX(x);
			    missiles[i].setY(y);
				missilesthreadpool.execute(missiles[i]);
				break;
			}
		}
	}
	
	
	public void run() {
	
		//�������������߳�
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				while (!isStop) {
					Shit();
					
					try {
						Thread.sleep(Resources.random.nextInt(2000)+1000);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
		while (!isStop) {
			x+=v;
			if (x>1000) {
				v=-v;
			}else if (x<=0) {
				v=-v;
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		missilesthreadpool.shutdown();	
	}
	
	
	
}
