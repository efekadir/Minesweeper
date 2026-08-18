package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageGenerator extends ImageView{
	public ImageGenerator(String imagePath, double width, double height) {
		//Resmi oluşturma
		Image background = new Image(imagePath);
		
		//Resmin ayarları
		this.setImage(background);
		this.setFitWidth(width);
		this.setFitHeight(height);
		this.setPreserveRatio(false);
	}
}
