package com.jonylima.testebluegears.view;

import java.io.IOException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameCarregamento extends JFrame {

//	
//	URL url = FrameCarregamento.class.getResource("faca.jpg");
//	BufferedImage bufferedImage = ImageIO.read(url);
//	Image ImagemMudada = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//	
//	Icon icon =new ImageIcon(ImagemMudada);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameCarregamento() throws IOException {
		super("TESTE BLUEGEARS");
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		URL url = FrameCarregamento.class.getResource("carregando.gif");
		Icon icon =new ImageIcon(url);
		JLabel label = new JLabel(icon);
		add(label);
	}
}
