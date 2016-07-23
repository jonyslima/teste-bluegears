package com.jonylima.testebluegears.helper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImagemUtils {

	public static Icon processaImagem(URL url,int largura ,int altura) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(url);
		Image img = bufferedImage.getScaledInstance(largura,largura, Image.SCALE_SMOOTH);
		
		return  new ImageIcon(img);
	}
}
