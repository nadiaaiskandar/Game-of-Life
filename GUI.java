// Nadia Iskandar
// APCS F
// HW 21
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public  class GUI{

    private final JFrame frame;
    private Canvas canvas;

    public static volatile boolean running = false;
    public static volatile boolean step = false;

    public static JLabel genLabel = new JLabel();
    public static JSlider speedSlider = new JSlider();
    public Run runButton = new Run(this);




    private static class Run extends JButton implements ActionListener {

        private final GUI gui;

        public Run(GUI gui) {
            super("Run");

            this.addActionListener(this);
            this.gui = gui;
        }

        public void actionPerformed(ActionEvent event) {
            // The action to be performed when this button is pressed.
            if(running == false){
                this.setText("Stop");
                running = !running; // toggle

            }else {
                this.setText("Run");
                running = !running;

            }

        }
    }


    private static class Step extends JButton implements ActionListener {

        private final GUI gui;

        public Step(GUI gui) {
            super("Step");

            this.addActionListener(this);
            this.gui = gui;
        }

        public void actionPerformed(ActionEvent event) {
            if(step == false){
                step = !step;
                running = false;
                gui.setStatus(true);

            }else {
                step = !step;

            }

        }
    }


    public  GUI(Canvas canvas){
        this.frame = new JFrame("Iskandar HW 20");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = canvas;

        CanvasPanel cPanel = new CanvasPanel(this.canvas);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel =  new JPanel();
        buttonPanel.add(runButton);
        buttonPanel.add(new Step(this));

        JPanel labelPanel = new JPanel();
        labelPanel.add(genLabel);

        JPanel sliderPanel = new JPanel();
        speedSlider = new JSlider(0, 100,10);

        JLabel slow = new JLabel("Slow");
        JLabel fast = new JLabel("Fast");

        sliderPanel.add(slow);
        sliderPanel.add(speedSlider);
        sliderPanel.add(fast);

        controlPanel.add(labelPanel);
        controlPanel.add(buttonPanel);
        controlPanel.add(sliderPanel);

        JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
        fullPanel.add(cPanel);
        fullPanel.add(controlPanel);

        Container content = frame.getContentPane();
        content.add(fullPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void redraw () {
        // A method that can be called to update the contents of the window.
        this.frame.repaint();
    }

    public void setStatus(boolean value){
        if(value){
            this.runButton.setText("Run");
        }else {
            this.runButton.setText("Step");

        }
    }


}

