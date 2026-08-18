package GameLogic;

import java.util.Random;

import GameManagement.GameSettings;

public class Board {

    public Cell[][] cells;
    private GameSettings settings;

    public Board(GameSettings settings) {
    	this.settings = settings;
        cells = new Cell[settings.getRows()][settings.getColumns()];
        generateCells();
        giveBomb();
        countBombAmount();
        
    }
    
    public void generateCells() {
    	for (int i = 0; i < settings.getRows(); i++) {
            for (int j = 0; j < settings.getColumns(); j++) {
                cells[i][j] = new Cell();
                cells[i][j].row = i;
                cells[i][j].column = j;
            }
        }
    }
    
    public void giveBomb() {
        Random random = new Random();
    	int bombAmount = 0;
    	
    	while(bombAmount < settings.getBombCount()) {
    		int randomRow = random.nextInt(settings.getRows());
    		int randomColumn = random.nextInt(settings.getColumns());
    		
    		if(!cells[randomRow][randomColumn].hasBomb) {
    			cells[randomRow][randomColumn].hasBomb = true;
    			bombAmount++;
    		}
    	}
    }
    
    public void countBombAmount() { 
    	for(int i = 0; i < settings.getRows(); i++) {
    		for(int j = 0; j < settings.getColumns(); j++) {
    			for(int a = i - 1; a <= i + 1; a++) {
    				for(int b = j - 1; b <= j + 1; b++) {
        	    		if((a >= 0 && b >= 0 && a < settings.getRows() && b < settings.getColumns()) && !(a == i && b == j)) {
        	    			if(cells[a][b].hasBomb) {
            	    			cells[i][j].howManyBombs++;
            	    		}
        	    		}
        	    	}
    	    	}
        	}
    	}
    }
}