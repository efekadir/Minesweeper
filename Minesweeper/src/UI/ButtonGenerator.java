package UI;

import javafx.scene.control.Button;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import javafx.scene.text.Font;

public class ButtonGenerator extends Button{
	public ButtonGenerator(String text) {
        super(text);
        
        //Farenin butün üstüne gelmesi, tıklaması ve butonun boş halinin renk ve arka plan kodları
        String normalColors = "-fx-background-color: #444444; -fx-text-fill: white;";
        String mouseOnColors = "-fx-background-color: #666666; -fx-text-fill: white;";
        String mouseClickedColors = "-fx-background-color: #FFFFFF; -fx-text-fill: red;";
        
        //Butonun boyutları
        this.setPrefSize(180, 60);
        this.setFont(Font.font("Comic Sans MS", 16));
        this.setStyle(normalColors);
        
        //Mouse ile etkileşim için buton event kodları
        this.setOnMouseEntered(e -> this.setStyle(mouseOnColors));
        this.setOnMouseExited(e -> this.setStyle(normalColors));
        this.setOnMousePressed(e -> this.setStyle(mouseClickedColors));
        
        DropShadow glow = new DropShadow();

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), this);
        scaleUp.setToX(1.08);
        scaleUp.setToY(1.08);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), this);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        this.setOnMouseEntered(e -> {
            this.setStyle(mouseOnColors);
            this.setEffect(glow);
            scaleUp.playFromStart();
        });

        this.setOnMouseExited(e -> {
            this.setStyle(normalColors);
            this.setEffect(null);
            scaleDown.playFromStart();
        });

        this.setOnMousePressed(e -> this.setStyle(mouseClickedColors));
    }
}