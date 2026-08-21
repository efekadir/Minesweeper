package UI;

import GameManagement.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinScreen extends StackPane {

    private final Stage primaryStage;
    private final GameSettings settings;

    public WinScreen(Stage primaryStage, GameSettings settings) {
        this.primaryStage = primaryStage;
        this.settings = settings;

        // Arka dikdörtgen
        Rectangle background = new Rectangle(500, 300);
        background.setArcWidth(25);
        background.setArcHeight(25);
        background.setFill(Color.rgb(236, 233, 227, 0.97));
        background.setStroke(Color.rgb(190, 190, 190));
        background.setStrokeWidth(2);

        // WIN yazısı
        Label winText = new Label("YOU WIN!");
        winText.setFont(Font.font("Arial", 40));
        winText.setTextFill(Color.rgb(60, 60, 60));

        // Butonlar
        PlayingAnimatedButton restartButton =
                new PlayingAnimatedButton("RESTART");

        PlayingAnimatedButton mainMenuButton =
                new PlayingAnimatedButton("MAIN MENU");

        restartButton.setPrefSize(150, 60);
        mainMenuButton.setPrefSize(150, 60);

        restartButton.setOnAction(e -> restartGame());
        mainMenuButton.setOnAction(e -> openMainMenu());

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(
                restartButton,
                mainMenuButton
        );
        buttons.setAlignment(Pos.CENTER);

        // İçerik
        VBox content = new VBox(25);
        content.getChildren().addAll(
                winText,
                buttons
        );
        content.setAlignment(Pos.CENTER);

        // Kutu
        StackPane winBox = new StackPane();
        winBox.getChildren().addAll(
                background,
                content
        );

        getChildren().add(winBox);

        StackPane.setAlignment(winBox, Pos.CENTER);

        // Başlangıçta gizli
        setVisible(false);
    }

    private void restartGame() {
        PlayingScreen playingScreen =
                new PlayingScreen(primaryStage, settings);

        primaryStage.setScene(playingScreen.getScene());
    }

    private void openMainMenu() {
        MainMenuScreen mainMenuScreen =
                new MainMenuScreen(primaryStage);

        primaryStage.setScene(mainMenuScreen.getScene());
    }
}
