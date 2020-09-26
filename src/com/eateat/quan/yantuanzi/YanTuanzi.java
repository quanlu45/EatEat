package com.eateat.quan.yantuanzi;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.eateat.quan.res.Resources;


/**
 * 
 * @author quan
 * ����ö����
 */
enum Direction{ RIGHT ,LEFT};

/**
 * 
 * @author quan
 * ������
 */
public class YanTuanzi{

	private int x;
	private int y;
	private float speed=8;
	private Direction currDirection=Direction.LEFT;
    private int scores =0;
    private boolean alive=true;

	public YanTuanzi(int x,int y) {
		this.x=x;
		this.y=y;

	}
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public boolean isAlive() {
		return alive;
	}
	
	//���ƺ���
	public void paint(Graphics g) {
		
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 20));
		g.drawString("����: "+scores,50,50);
		if (currDirection ==Direction.LEFT) {
			g.drawImage(Resources.leftYanTuanZiImage, x,y,160,200,null);
		}
		else {
			g.drawImage(Resources.rightYanTuanZiImage, x,y,160,200,null);
		}
	}
	
	//�ƶ�
	public void move(Direction d) {
		
		if (!alive) {
			return ;
		}
		currDirection =d;
		if (d ==Direction.LEFT&&x-speed>0) {
			x-=speed;
		}
		else if (d==Direction.RIGHT&&x+speed<900) {
			x+=speed;
		}
	}
	
	//�������
	public void checkScores(SmilingFace[] faces) {
		for (SmilingFace face : faces) {
			if (face.getY()>=y+100&&face.getX()>=x&&face.getX()<=x+160) {
				scores+=face.getScores();
				Resources.eatsoundClip.loop(1);
				face.setAlive(false);
			}
		}
	}
	
	//�������м��
	public boolean isHit(Missile[] missiles) {
		for (Missile missile : missiles) {
			if (missile.isAlive()) {
				if (missile.getX()>=x&&missile.getX()<=x+140&&missile.getY()>=y+10) {
					alive =false;
					return true;
				}
			}
		}
		return false;
	}



	
}
