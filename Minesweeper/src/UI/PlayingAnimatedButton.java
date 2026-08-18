package UI;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class PlayingAnimatedButton extends Button {

    public PlayingAnimatedButton(String text) {
        super(text);

        setPrefSize(80, 80);
        setFont(Font.font("Arial", 20));

        setStyle(
            "-fx-background-color: #ECE9E3;" +
            "-fx-text-fill: #3C3C3C;" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: #C8C8C8;" +
            "-fx-border-width: 1.5;"
        );

        setOnMouseEntered(e -> {
            setStyle(
                "-fx-background-color: #F6F4EF;" +
                "-fx-text-fill: #2F2F2F;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #B8B8B8;" +
                "-fx-border-width: 1.5;"
            );
            animate(1.03);
        });

        setOnMouseExited(e -> {
            setStyle(
                "-fx-background-color: #ECE9E3;" +
                "-fx-text-fill: #3C3C3C;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #C8C8C8;" +
                "-fx-border-width: 1.5;"
            );
            animate(1.0);
        });

        setOnMousePressed(e -> animate(0.97));

        setOnMouseReleased(e -> {
            if (isHover())
                animate(1.03);
            else
                animate(1.0);
        });
    }

    private void animate(double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(120), this);
        st.setToX(scale);
        st.setToY(scale);
        st.play();
    }
}