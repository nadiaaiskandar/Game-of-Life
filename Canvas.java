// Nadia Iskandar
public class Canvas {

    private int rows;
    private int columns;
    private boolean[][] canvas;

    public Canvas(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.canvas = new boolean[rows][columns];
    }

    public int rows()    { return this.rows; }
    public int columns() { return this.columns; }

    public boolean isOnCanvas(int row, int column) {
        return (row >= 0 && row < this.rows) &&
                (column >= 0 && column < this.columns);
    }

    public boolean isSet(int row, int column) {
        return isOnCanvas(row, column) && this.canvas[row][column];
    }

    public void set(int row, int column, boolean value) {
        if (isOnCanvas(row, column)) {
            this.canvas[row][column] = value;
        }
    }

    public void set(int row, int column) {
        set(row, column, true);
    }

    public void clear(int row, int column) {
        set(row, column, false);
    }

    public void clear() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                this.canvas[row][column] = false;
            }
        }
    }

    public void fill(int bottom, int top, int left, int right, boolean value) {
        // Fills a rectangle with given coordinates for top and bottom
        // rows and for left and right columns.
        for (int row = bottom; row <= top; row++) {
            for (int column = left; column <= right; column++) {
                set(row, column, value);
            }
        }
    }

    public void fill(int bottom, int top, int left, int right) {
        fill(bottom, top, left, right, true);
    }

    public void clear(int bottom, int top, int left, int right) {
        fill(bottom, top, left, right, false);
    }

    @Override
    public String toString() {
        // Produces an image of the canvas that can be printed on System.out.
        String result = "";
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                result += isSet(row, column) ? "*" : " ";
            }
            result += "\n";
        }
        return result;
    }

    public boolean equals(Canvas other) {
        // Check that two canvas' have the same contents.
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                if (this.canvas[row][column] != other.canvas[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equals(Object other) {
        return other instanceof Canvas && this.equals((Canvas) other);
    }
}
