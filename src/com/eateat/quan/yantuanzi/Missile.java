package com.eateat.quan.yantuanzi;
import java.awt.Graphics;

import com.eateat.quan.res.Resources;

/**
 * 
 * @author quan
 * ������
 */
public class Missile implements Runnable{
	private int x;         //x����
	private int y;         //y����
	private int vx;        //x������ٶ�
	private int vy =5;     //y������ٶ�
	private boolean alive; //����־
	
	public Missile() {
	}
	public boolean isAlive() {
		return alive;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public void setVx(int vx) {
		this.vx =vx;
	}
	public void setVy(int vy) {
		this.vy =vy;
	}
	public void paint(Graphics g) {
		if (vx>0) {
			g.drawImage(Resources.rightmissileImage, x, y, 100,100, null);
		}else {
			g.drawImage(Resources.leftmissileImage, x, y, 100,100, null);
		}
	}
	
	public void run() {
		alive =true;
		while (alive) {
			x+=vx;
			y+=vy;
			if (y>900||x<0||x>1000) {
				alive =false;
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		x=y=0;
	}
	
	
	
	
}
