package com.eateat.quan.yantuanzi;
import javax.swing.JApplet;

public class MyApplet extends JApplet{
	
	private MyPanel panel;
	public void init() {
		panel =new MyPanel();
		setSize(1000,1000);
		add(panel);
	}
	
	
	public void start() {
		
	}
	
	public void stop() {
		
	}
	
	
	
	public void destroy() {
		remove(panel);
	}
	
}
