// Nadia Iskandar
// APCS F
// HW 21
import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel {

    private final Canvas canvas;

    public CanvasPanel(Canvas canvas) {
        super();
        this.canvas = canvas;
        int height = canvas.rows();
        int width = canvas.columns();
        setPreferredSize(new Dimension(width, height));
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    @Override
    public void paint(Graphics graphics) {
        int rows = this.canvas.rows();
        int columns = this.canvas.columns();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Color color = this.canvas.isSet(row,column) ? Color.BLACK : Color.WHITE;
                graphics.setColor(color);
                graphics.fillRect(column, row, 1, 1);
            }
        }
    }
}