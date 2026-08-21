package UI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import GameLogic.Cell;

public class CellButtonGenerator extends Button {
    private StackPane graphicPane;

    public Cell cell;
    public CellButton cellButton;
    private final double CELL_SIZE;
    private final double BOMB_SIZE;
    private final double FLAG_SIZE;

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
        BOMB_SIZE = cellButton.getGameSettings().getBombSize();
        FLAG_SIZE = cellButton.getGameSettings().getFlagSize();

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

            if (!this.isDisabled() && !this.isPressed() && !redFlag.isVisible()) {
                this.setStyle(HOVER_STYLE);
            }
        });

        // Mouse ayrıldığında
        this.setOnMouseExited(e -> {

            if (!this.isDisabled() && !this.isPressed() && !redFlag.isVisible()) {
                this.setStyle(NORMAL_STYLE);
            }
        });

        // Mouse basıldığında
        this.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
            	
                // Bayrak varsa kaldır
                if (redFlag.isVisible()) {
                    redFlag.setVisible(false);
                    cellButton.setRemainingBombs(cellButton.getRemainingBombs() + 1);
                    cellButton.getBombCounter().setText("" + cellButton.getRemainingBombs());

                    // Bayrak kaldırıldıktan sonra hover görünümü
                    this.setStyle(HOVER_STYLE);
                }

                // Bayrak yoksa ve bomba hakkı varsa koy
                else if (cellButton.getRemainingBombs() > 0) {
                    redFlag.setVisible(true);
                    cellButton.setRemainingBombs(cellButton.getRemainingBombs() - 1);

                    cellButton.getBombCounter().setText("" + cellButton.getRemainingBombs());
                }

            } 
            else {
                // Sol tıklama
            	if (!redFlag.isVisible()) {
                    this.setStyle(PRESSED_STYLE);
                }
            }
        });

        // Mouse bırakıldığında
        this.setOnMouseReleased(e -> {

            if (e.getButton() == MouseButton.PRIMARY && !this.isDisabled() && !redFlag.isVisible()) {
                this.setStyle(HOVER_STYLE);
            }
        });

        // Hücreye tıklama
        this.setOnAction(e -> {
        	if(redFlag.isVisible() == false) { 
        		this.setDisable(true); 
        		this.setStyle(CLICKED_STYLE); 
        		if(cell.hasBomb) { 
        			cellButton.showBombs(); 
        			PauseTransition pause = new PauseTransition(Duration.seconds(2)); 
        			pause.setOnFinished(event -> { cellButton.gameOverScreen.setVisible(true); }); 
        			pause.play(); 
        		} 
        		else { 
        			showBombAmount(); 
        			if (cell.howManyBombs == 0) { 
        				cellButton.multiButtonOpen(row, column); 
        			} 
        		} 
        	} 
        });

        mine = new Mine(BOMB_SIZE);
        redFlag = new RedFlag(FLAG_SIZE);

        graphicPane = new StackPane();
        graphicPane.getChildren().addAll(mine, redFlag);

        setGraphic(graphicPane);
    }

    public void showBombAmount() {

        int bombAmount = cell.howManyBombs;

        if (bombAmount > 0) {

            Text bombAmountText =
                    new Text("" + bombAmount);

            bombAmountText.setFont(
                    Font.font("Arial", 30)
            );

            graphicPane.getChildren().add(bombAmountText);
        }
    }

    public void buttonClicked() {

        this.setDisable(true);
        this.setStyle(CLICKED_STYLE);

        cell.isClicked = true;

        showBombAmount();

        cellButton.safeCell();
    }

    public static String getClickedStyle() {
        return CLICKED_STYLE;
    }
}
