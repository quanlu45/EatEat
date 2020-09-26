package com.eateat.quan.res;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author quan
 * 资源类
 */
public class Resources {
	
	 public static Random random;

	 public static Image facesImage;
	 public static Image leftshipImage;
	 public static Image rightshipImage;
	 public static Image leftmissileImage;
	 public static Image rightmissileImage;
	 public static Image leftYanTuanZiImage;
	 public static Image rightYanTuanZiImage;
	 public static Image backgroundImage;
	 
	 public static Clip   eatsoundClip;
	 
	 static {
		try {
			random=new Random();
			facesImage =ImageIO.read(new File("face.png"));
		    leftshipImage =ImageIO.read(new File("leftship.png"));
			rightshipImage =ImageIO.read(new File("rightship.png"));
			leftmissileImage=ImageIO.read(new File("leftmissile.png"));
			rightmissileImage=ImageIO.read(new File("rightmissile.png"));
			leftYanTuanZiImage=ImageIO.read(new File("left.png"));
			rightYanTuanZiImage=ImageIO.read(new File("right.png"));
            backgroundImage =ImageIO.read(new File("bg.bmp"));
			
			AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File("sound1.wav"));
			eatsoundClip =AudioSystem.getClip();
			eatsoundClip.open(audioInputStream);
			
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			// TODO 自动生成的 catch 块
			System.err.println("资源加载失败！");
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}

