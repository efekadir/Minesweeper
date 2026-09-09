package UI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import GameManagement.GameSettings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class PlayingScreen {

    private static final double DEFAULT_WIDTH = 1300;
    private static final double DEFAULT_HEIGHT = 800;
    
    private static final int BUTTON_SPACING = 30;
    private static final int TOP_PADDING = 0;
    
    private static final String GAME_SCENE_PATH = PlayingScreen.class.getResource("/UI/images/PlayingScreenBackground.png").toExternalForm();    private final Stage primaryStage;

            private final Scene playingScene;
    private CellButton cellButton;
    private Label bombCounter;
    
    private GameSettings settings;
    
    public PlayingScreen(Stage primaryStage, GameSettings settings) {
        this.primaryStage = primaryStage;
        this.settings = settings;
        
        ImageGenerator mainBackground = new ImageGenerator(GAME_SCENE_PATH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        bombCounter = new Label("" + settings.getBombCount());
        bombCounter.setFont(Font.font("Arial", 24));

        HBox buttonsBox = createButtonsMenu();
        StackPane.setAlignment(buttonsBox, Pos.CENTER);
        
        GridPane gameGrid = generateCells();
        StackPane.setAlignment(gameGrid, Pos.CENTER);
        
        Mine mine = new Mine(settings.getBombSize());
        mine.setVisible(true);
        
        HBox bombCounterBox = new HBox(5, mine, bombCounter);
        bombCounterBox.setAlignment(Pos.CENTER);

        VBox gameLayout = new VBox(15);
        gameLayout.getChildren().addAll(
            buttonsBox,
            bombCounterBox,
            gameGrid
        );
        gameLayout.setAlignment(Pos.CENTER);
        
        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(
            mainBackground,
            gameLayout,
            cellButton.gameOverScreen,
            cellButton.winScreen
        );
        
        this.playingScene = new Scene(mainPane);
        
        mainBackground.fitWidthProperty().bind(playingScene.widthProperty());
        mainBackground.fitHeightProperty().bind(playingScene.heightProperty());
    }

    private HBox createButtonsMenu() {
        PlayingAnimatedButton restartButton = new PlayingAnimatedButton("");
        restartButton.setGraphic(new RestartIcon());
        restartButton.setPrefSize(60, 60);

        PlayingAnimatedButton mainMenuButton = new PlayingAnimatedButton("");
        mainMenuButton.setGraphic(new MainMenuIcon());
        mainMenuButton.setPrefSize(60, 60);
        
        restartButton.setAlignment(Pos.CENTER);
        mainMenuButton.setAlignment(Pos.CENTER);
        
        restartButton.setOnAction(e -> restartGame());
                
        mainMenuButton.setOnAction(e -> openMainMenu());        
        
        HBox buttons = new HBox(BUTTON_SPACING);
        buttons.getChildren().addAll(
            restartButton,
            mainMenuButton
        );
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(TOP_PADDING, 0, 0, 0));
        
        return buttons;
    }
    
    private GridPane generateCells() {
        this.cellButton = new CellButton(primaryStage, settings, bombCounter);
        
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
        MainMenuScreen mainMenu = new MainMenuScreen(primaryStage);
        primaryStage.setScene(mainMenu.getScene());
    }

    public Scene getScene() {   
        return playingScene;
    }    
}
