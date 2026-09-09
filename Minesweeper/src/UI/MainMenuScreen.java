package UI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import GameManagement.GameSettings;
import javafx.geometry.Insets;

public class MainMenuScreen {

    private static final double DEFAULT_WIDTH = 1100;
    private static final double DEFAULT_HEIGHT = 800;
    
    private static final int BUTTON_SPACING = 15;
    private static final int TOP_PADDING = 240;
    
    private static final String MENU_SCENE_PATH = MainMenuScreen.class.getResource("/UI/images/MainMenuBackground.png").toExternalForm();    private final Stage primaryStage;
 
    private final Scene menuScene;
    private SettingsScreen settingsScreen;

    public MainMenuScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        ImageGenerator mainBackground = new ImageGenerator(MENU_SCENE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        VBox buttonsBox = createButtonsMenu();
        
        StackPane mainPane = new StackPane();
    	settingsScreen = new SettingsScreen();
        mainPane.getChildren().addAll(mainBackground, buttonsBox, settingsScreen);
                
        this.menuScene = new Scene(mainPane);
        
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
