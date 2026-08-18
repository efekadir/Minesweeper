package UI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import GameLogic.Cell;
import GameManagement.GameSettings;

public class CellButtonGenerator extends Button {
	private StackPane graphicPane;
	
    public Cell cell;
    public CellButton cellButton;
    private final double CELL_SIZE;
    
    public RedFlag redFlag;
    public Mine mine;
    
    public int row;
    public int column;
    
    private static final String NORMAL_STYLE =
            "-fx-background-color: #c8c8c8;" +
            "-fx-border-color: #ffffff #808080 #808080 #ffffff;" +
            "-fx-border-width: 3px;";

    private static final String HOVER_STYLE =
            "-fx-background-color: #d4d4d4;" +
            "-fx-border-color: #ffffff #909090 #909090 #ffffff;" +
            "-fx-border-width: 3px;";

    private static final String PRESSED_STYLE =
            "-fx-background-color: #bdbdbd;" +
            "-fx-border-color: #808080;" +
            "-fx-border-width: 2px;";
    
    private static final String CLICKED_STYLE =
            "-fx-background-color: #999999;" +
            "-fx-border-color: #707070;" +
            "-fx-border-width: 2px;";

    public CellButtonGenerator(Cell cell, CellButton cellButton) {
    	this.cell = cell;
    	this.cellButton = cellButton;
    	CELL_SIZE = cellButton.getGameSettings().getCellSize();
    	
        // Hücrenin boyutu
        this.setPrefSize(CELL_SIZE, CELL_SIZE);
        this.setMinSize(CELL_SIZE, CELL_SIZE);
        this.setMaxSize(CELL_SIZE, CELL_SIZE);

        // Yazı
        this.setFont(Font.font("Arial", 18));

        // Normal görünüm
        this.setStyle(NORMAL_STYLE);

        // Mouse üzerine geldiğinde
        this.setOnMouseEntered(e -> {
            if (!this.isPressed() && redFlag.isVisible() == false) {
                this.setStyle(HOVER_STYLE);
            }
        });

        // Mouse ayrıldığında
        this.setOnMouseExited(e -> {
            if (!this.isPressed()) {
                this.setStyle(NORMAL_STYLE);
            }
        });

        // Basıldığında
        this.setOnMousePressed(e -> {
            this.setStyle(PRESSED_STYLE);
            
            if (e.getButton() == MouseButton.SECONDARY && redFlag.isVisible()) {
                redFlag.setVisible(false);
            }
            else if(e.getButton() == MouseButton.SECONDARY && redFlag.isVisible() == false) {
                redFlag.setVisible(true);
            }
        });

        // Bırakıldığında
        this.setOnMouseReleased(e -> {
            this.setStyle(HOVER_STYLE);
        });
        
        this.setOnAction(e -> {
        	if(redFlag.isVisible() == false) {
        		this.setDisable(true);
                this.setStyle(CLICKED_STYLE);
                         
                if(cell.hasBomb) {
                	cellButton.showBombs();
                	
                	PauseTransition pause = new PauseTransition(Duration.seconds(2));

                    pause.setOnFinished(event -> {
                    	cellButton.gameOverScreen.setVisible(true);
                    });

                    pause.play();
                }
                else {
                	int bombAmount = cell.howManyBombs;
                	System.out.println("Bomba sayısı: " + bombAmount);
                	
                	if(bombAmount > 0) {
                		Text bombAmountText = new Text("" + bombAmount);
                        bombAmountText.setFont(Font.font("Arial", 30));

                        graphicPane.getChildren().add(bombAmountText);
                	}
                }
        	}
            
        });
        
        redFlag = new RedFlag();
        mine = new Mine();

        graphicPane = new StackPane();
        graphicPane.getChildren().addAll(mine, redFlag);

        setGraphic(graphicPane);
    }
}