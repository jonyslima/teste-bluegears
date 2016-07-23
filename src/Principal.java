import java.io.IOException;

import com.jonylima.testebluegears.controller.ItemController;
import com.jonylima.testebluegears.view.FrameCarregamento;
import com.jonylima.testebluegears.view.FrameItem;

public class Principal {

	public static void main(String[] args) throws IOException {
		FrameCarregamento carregamento = new FrameCarregamento();
		carregamento.setVisible(true);
		FrameItem frameItem = new FrameItem();
		new ItemController(frameItem);
		frameItem.setVisible(carregamento.isActive());
		carregamento.setVisible(false);
	}

}
