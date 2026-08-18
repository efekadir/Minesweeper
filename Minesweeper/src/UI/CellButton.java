package UI;

import GameLogic.Board;
import GameLogic.Cell;
import GameManagement.GameSettings;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class CellButton{
	
	public CellButtonGenerator[][] cells;
    public Board board;
    public GameOverScreen gameOverScreen;
    private Stage primaryStage;
    private GameSettings settings;
	
	public CellButton(Stage primaryStage, GameSettings settings) {
		this.primaryStage = primaryStage;
		this.settings = settings;
		
		cells = new CellButtonGenerator[settings.getRows()][settings.getColumns()];
		board = new Board(settings);
	    gameOverScreen = new GameOverScreen(primaryStage, settings);

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
	
	public GameSettings getGameSettings() {
		return settings;
	}
}