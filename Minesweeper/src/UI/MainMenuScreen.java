package UI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import GameManagement.GameSettings;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuScreen {

    private static final double DEFAULT_WIDTH = 1300;
    private static final double DEFAULT_HEIGHT = 800;
    
    private static final int BUTTON_SPACING = 15;
    private static final int TOP_PADDING = 150;
    
    private static final String MENU_SCENE_PATH = "file:/Users/efekadirkucuk/Desktop/ChatGPT Image 23 Tem 2026 17_59_08.png";
    private static final String SETTINGS_PATH = "file:images/GhostMainBackground.png";

    private final Stage primaryStage;
    private final Scene menuScene;
    
    private SettingsScreen settingsScreen;

    public MainMenuScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        ImageGenerator mainBackground = new ImageGenerator(MENU_SCENE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        VBox buttonsBox = createButtonsMenu();
        
        StackPane mainPane = new StackPane();
    	settingsScreen = new SettingsScreen();
        mainPane.getChildren().addAll(mainBackground, buttonsBox, settingsScreen);
                
        this.menuScene = new Scene(mainPane, getInitialWidth(), getInitialHeight());
        
        mainBackground.fitWidthProperty().bind(menuScene.widthProperty());
        mainBackground.fitHeightProperty().bind(menuScene.heightProperty());
    }

    private VBox createButtonsMenu() {
        MainMenuAnimatedButton startGameButton = new MainMenuAnimatedButton("PLAY");
        MainMenuAnimatedButton selectGameButton = new MainMenuAnimatedButton("SETTINGS");
        MainMenuAnimatedButton exitButton = new MainMenuAnimatedButton("EXIT");
        
        startGameButton.setOnAction(e -> playingScreen());
        selectGameButton.setOnAction(e -> settingsMenu());
        exitButton.setOnAction(e -> System.exit(0));
        
        VBox buttons = new VBox(BUTTON_SPACING);
        buttons.getChildren().addAll(startGameButton, selectGameButton, exitButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(TOP_PADDING, 0, 0, 0));
        
        return buttons;
    }

    private double getInitialWidth() {
        return (primaryStage.getScene() != null) ? primaryStage.getScene().getWidth() : DEFAULT_WIDTH;
    }

    private double getInitialHeight() {
        return (primaryStage.getScene() != null) ? primaryStage.getScene().getHeight() : DEFAULT_HEIGHT;
    }

    private void playingScreen() {
    	GameSettings settings = settingsScreen.getSelectedSettings();
    	PlayingScreen playingScreen = new PlayingScreen(primaryStage, settings);
        primaryStage.setScene(playingScreen.getScene());
    }

    private void settingsMenu() { 
    	settingsScreen.setVisible(true);
    }

    public Scene getScene() {   
        return menuScene;
    }    
}