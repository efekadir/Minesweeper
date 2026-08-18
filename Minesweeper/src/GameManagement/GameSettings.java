package GameManagement;

public class GameSettings {

    private int rows;
    private int columns;
    private int bombCount;
    private int cellSize;

    public GameSettings(int rows, int columns, int bombCount, int cellSize) {
        this.rows = rows;
        this.columns = columns;
        this.bombCount = bombCount;
        this.cellSize = cellSize;
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
}