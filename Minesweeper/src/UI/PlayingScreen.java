package UI;

import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import GameManagement.GameSettings;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayingScreen {

    private static final double DEFAULT_WIDTH = 1300;
    private static final double DEFAULT_HEIGHT = 800;
    
    private static final int BUTTON_SPACING = 30;
    private static final int TOP_PADDING = 150;
    
    private static final String GAME_SCENE_PATH = "file:/Users/efekadirkucuk/Desktop/ChatGPT Image 23 Tem 2026 17_58_04.png";

    private final Stage primaryStage;
    private final Scene menuScene;
    private CellButton cellButton;
    
    private GameSettings settings;
    
    public PlayingScreen(Stage primaryStage, GameSettings settings) {
        this.primaryStage = primaryStage;
        this.settings = settings;
        
        ImageGenerator mainBackground = new ImageGenerator(GAME_SCENE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        HBox buttonsBox = createButtonsMenu();
        StackPane.setAlignment(buttonsBox, Pos.CENTER);
        buttonsBox.setTranslateY(-80);
        
        GridPane gameGrid = generateCells();
        StackPane.setAlignment(gameGrid, Pos.CENTER);
        
        VBox gameLayout = new VBox();
        gameLayout.getChildren().addAll(buttonsBox, gameGrid);
        gameLayout.setAlignment(Pos.CENTER);
        
        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(mainBackground, gameLayout, cellButton.gameOverScreen);
        
        this.menuScene = new Scene(mainPane, getInitialWidth(), getInitialHeight());
        
        mainBackground.fitWidthProperty().bind(menuScene.widthProperty());
        mainBackground.fitHeightProperty().bind(menuScene.heightProperty());
    }

    private HBox createButtonsMenu() {
    	PlayingAnimatedButton restartButton = new PlayingAnimatedButton("");
    	restartButton.setGraphic(new RestartIcon());
    	restartButton.setPrefSize(60, 60);

    	PlayingAnimatedButton pauseButton = new PlayingAnimatedButton("");
    	pauseButton.setGraphic(new PauseIcon());
    	pauseButton.setPrefSize(60, 60);

    	PlayingAnimatedButton mainMenuButton = new PlayingAnimatedButton("");
    	mainMenuButton.setGraphic(new MainMenuIcon());
    	mainMenuButton.setPrefSize(60, 60);
    	
    	restartButton.setAlignment(Pos.CENTER);
    	pauseButton.setAlignment(Pos.CENTER);
    	mainMenuButton.setAlignment(Pos.CENTER);
        
        restartButton.setOnAction(e -> restartGame());
        
 //       pauseButton.setOnAction(() -> );
        
        mainMenuButton.setOnAction(e -> openMainMenu());        
        
        HBox buttons = new HBox(BUTTON_SPACING);
        buttons.getChildren().addAll(restartButton, pauseButton, mainMenuButton);
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setPadding(new Insets(TOP_PADDING, 0, 0, 0));
        
        return buttons;
    }

    private double getInitialWidth() {
        return (primaryStage.getScene() != null) ? primaryStage.getScene().getWidth() : DEFAULT_WIDTH;
    }

    private double getInitialHeight() {
        return (primaryStage.getScene() != null) ? primaryStage.getScene().getHeight() : DEFAULT_HEIGHT;
    }
    
    private GridPane generateCells() {
    	this.cellButton = new CellButton(primaryStage, settings);
    	
    	GridPane gameGrid = new GridPane();
    	
    	for (int i = 0; i < settings.getRows(); i++) {
    	    for (int j = 0; j < settings.getColumns(); j++) {
    	        gameGrid.add(cellButton.cells[i][j], j, i);
    	    }
    	}
    	
    	gameGrid.setAlignment(Pos.CENTER);
    	return gameGrid;
    }

    private void restartGame() {
    	PlayingScreen restartButton = new PlayingScreen(primaryStage, this.settings);
        primaryStage.setScene(restartButton.getScene());
    }

    private void openMainMenu() {
    	MainMenuScreen mainMenuButton = new MainMenuScreen(primaryStage);
        primaryStage.setScene(mainMenuButton.getScene());
    }

    public Scene getScene() {   
        return menuScene;
    }    
}