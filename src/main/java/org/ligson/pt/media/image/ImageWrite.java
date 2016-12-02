package org.ligson.pt.media.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/***
 * 绘制图片
 * @author trq
 *
 */
public class ImageWrite {
	public static void main(String[] args) throws Exception{
		File file = new File("./cang2.jpg");
		
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		/*for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(i%10==0){
					image.setRGB(i, j, new Color(255, 0, 0).getRGB());
				}else{
					image.setRGB(i, j, new Color(0, 255, 0).getRGB());
				}
			}
		}*/
		graphics.setColor(Color.RED);
		graphics.drawLine(0, 0, 100, 100);
		
		ImageIO.write(image,"JPG",file);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.exists());
		//graphics.dispose();
	}
}
