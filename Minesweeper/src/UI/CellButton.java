package UI;

import GameLogic.Board;
import GameLogic.Cell;
import GameManagement.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CellButton{
	
	public CellButtonGenerator[][] cells;
    public Board board;
    public GameOverScreen gameOverScreen;
    public WinScreen winScreen;
    private Stage primaryStage;
    private GameSettings settings;
    private int openedCellCount = 0;
    private int totalSafeCells;
    private int remainingBombs;
    private Label bombCounter;
	
    public CellButton(Stage primaryStage, GameSettings settings, Label bombCounter) {
		this.primaryStage = primaryStage;
		this.settings = settings;
		this.totalSafeCells = settings.getRows() * settings.getColumns() - settings.getBombCount();
		this.remainingBombs = settings.getBombCount();
		this.bombCounter = bombCounter;
		
		cells = new CellButtonGenerator[settings.getRows()][settings.getColumns()];
		board = new Board(settings);
	    gameOverScreen = new GameOverScreen(primaryStage, settings);
	    winScreen = new WinScreen(primaryStage, settings);

		generateCells();
	}
	
	public void generateCells() {
		for(int i = 0; i < settings.getRows(); i++){
			for(int j = 0; j < settings.getColumns(); j++) {
				cells[i][j] = generateButton(board.cells[i][j]);
				cells[i][j].row = i;
				cells[i][j].column = j;
			}
		}
	}
	
	public CellButtonGenerator generateButton(Cell cell) {
		CellButtonGenerator cellButton = new CellButtonGenerator(cell, this);
		
		return cellButton;
	}
	
	public void showBombs() { 
		for(int i = 0; i < settings.getRows(); i++){
			for(int j = 0; j < settings.getColumns(); j++) {
				if(board.cells[i][j].hasBomb) {
					this.cells[i][j].mine.setVisible(true);
				}
			}
		}
	}
	
	public void safeCell() {
	    openedCellCount++;

	    if (openedCellCount >= totalSafeCells) {
	        winScreen.setVisible(true);
	    }
	}
	
	public int getRemainingBombs() {
		return remainingBombs;
	}

	public void setRemainingBombs(int remainingBombs) {
		this.remainingBombs = remainingBombs;
	}
	
	public Label getBombCounter() {
	    return bombCounter;
	}

	public GameSettings getGameSettings() {
		return settings;
	}
}
