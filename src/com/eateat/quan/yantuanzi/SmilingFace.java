package com.eateat.quan.yantuanzi;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.eateat.quan.res.Resources;


/**
 * 
 * @author quan
 * Ц��
 */
public class SmilingFace implements Runnable{
	private int x;                 //x����
	private int y;                 //y����
	private int angle;             //��ת�Ƕ�
	private int radius;            //�뾶
	private int scores;            //����
	private int v;                 //�ٶ�
	private boolean isAlive=true;  //����־λ
	private static ArrayList<SmilingFace> faceliList = new ArrayList<SmilingFace>();       //face list
	
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
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public void setAlive(boolean flag){
		this.isAlive =flag;
	}
	
	public SmilingFace() {
		x =Resources.random.nextInt(800);
		y=Resources.random.nextInt(100);
		v=2+Resources.random.nextInt(3);
		radius =Resources.random.nextInt(30)+30;
		scores =radius*10;
		angle =0;
		faceliList.add(this);
	
	}
	
	public void paint(Graphics g) {
		if (!isAlive) {
			return ;
		}
		Graphics2D g2d =(Graphics2D)g.create();
		
		//��ת
		g2d.rotate(Math.toRadians(angle),x+radius/2,y+radius/2);
		g2d.drawImage(Resources.facesImage,x,y,radius,radius,null);
		angle+=1;	
	}
	
	
	public static ArrayList<SmilingFace> getFaceList(){
		return faceliList;
	}
	
	public void run() {
		
		while(true)
		{

			try {
				Thread.sleep(10);
				y+=v;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (y>=900||!isAlive) {
				x =Resources.random.nextInt(800);
				y=Resources.random.nextInt(100);
				radius =Resources.random.nextInt(30)+30;
				v=2+Resources.random.nextInt(3);
				scores =radius*10;
				angle =0;
				//v=-v;
				isAlive =true;
			}
		}
	}	
	
}
