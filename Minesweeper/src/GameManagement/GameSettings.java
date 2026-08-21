package GameManagement;

public class GameSettings {

    private int rows;
    private int columns;
    private int bombCount;
    private int cellSize;
    private int bombSize;
    private int flagSize;

    public GameSettings(int rows, int columns, int bombCount, int cellSize, int bombSize, int flagSize) {
        this.rows = rows;
        this.columns = columns;
        this.bombCount = bombCount;
        this.cellSize = cellSize;
        this.bombSize = bombSize;
        this.flagSize = flagSize;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getBombCount() {
        return bombCount;
    }
    
    public int getCellSize() {
        return cellSize;
    }

	public int getBombSize() {
		return bombSize;
	}

	public int getFlagSize() {
		return flagSize;
	}
    
}
