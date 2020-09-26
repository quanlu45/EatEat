package com.eateat.quan.yantuanzi;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.eateat.quan.res.Resources;


/**
 * 
 * @author quan
 * 笑脸
 */
public class SmilingFace implements Runnable{
	private int x;                 //x坐标
	private int y;                 //y坐标
	private int angle;             //旋转角度
	private int radius;            //半径
	private int scores;            //分数
	private int v;                 //速度
	private boolean isAlive=true;  //存活标志位
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
		
		//旋转
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
