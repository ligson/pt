package org.ligson.pt.media.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
/***
 * 灰度图片
 * @author trq
 *
 */
public class ImageToChar {
	/** 此处设置灰度字符，此处只用十个字符，可以设置更多 */
	private static char[] cs = new char[] { '.', ',', '*', '+', '=', '&', '$', '@', '#', ' ' };

	public static void main(String[] args) throws Exception {
		File file = new File("./cang.jpg");
		System.out.println(file.exists());
		BufferedImage bfedimage = ImageIO.read(file);
		// 图片转字符串后的数组
		char[][] css = new char[bfedimage.getWidth()][bfedimage.getHeight()];

		for (int x = 0; x < bfedimage.getWidth(); x++) {
			for (int y = 0; y < bfedimage.getHeight(); y++) {
				int rgb = bfedimage.getRGB(x, y);
				Color c = new Color(rgb);
				// 得到灰度值
				int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				css[x][y] = cs[(int) ((cc * 10 - 1) / 255)];
			}
		}

		// 取得模板HTML
		StringBuffer sb = new StringBuffer();

		// 开始拼接内容
		for (int y = 0; y < css[0].length; y++) {
			for (int x = 0; x < css.length; x++) {
				sb.append(css[x][y]);
			}
			sb.append("\r\n");
		}
		System.out.println(sb);

	}
}
