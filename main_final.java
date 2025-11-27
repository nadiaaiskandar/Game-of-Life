// Nadia Iskandar
// APCS F
// Homework_21
public class main_final {
    public static void sleep(int speed) {

        int milliseconds = 1000/speed;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Sleep Exception");
        }
    }

    public static void main(String[] args){
        int rows = 100;
        int cols = 100;
        int size = 100;
        int generations = Integer.MAX_VALUE;
        int pixels = 5;
        int speed = 10;
        String filename = "";
        boolean centered = false;
        boolean grid = false;
        boolean cylinder = false;
        boolean rod = false;
        boolean torus = false;

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "-rows" -> rows = Integer.parseInt(args[++i]);
                case "-columns" -> cols = Integer.parseInt(args[++i]);
                case "-size" -> size = Integer.parseInt(args[++i]);
                case "-generations" -> generations = Integer.parseInt(args[++i]);
                case "-pixels"-> pixels  = Integer.parseInt(args[++i]);
                case"-speed" -> speed  = Integer.parseInt(args[++i]);
                case "-center" -> centered = true;
                case "-grid" -> grid = true;
                case "-cylinder" -> cylinder = true;
                case "-rod" -> rod = true;
                case "-torus" -> torus = true;

                default -> filename = args[i];
            }

        }

        if(size != 0){
            rows = size;
            cols = size;
        }

        Life life = new Life(generations, rows, cols, filename);
        Canvas canvas = new Canvas(rows*pixels, cols*pixels);
        GUI gui = new GUI(canvas);

        if(centered){
            life.center();
        }

        gui.speedSlider.setValue(speed);

        int i = 0;
        while(true){

            speed = gui.speedSlider.getValue();
            speed = (int) Math.pow(10,(3*speed)/100);

            if(gui.running && i<= generations){
                canvas.clear();
                life.paint(canvas, pixels);
                gui.redraw();
                sleep(speed);
                if(grid){
                    life.next(0);
                }else if(rod){
                    life.next(1);
                }else if(cylinder){
                    life.next(2);
                }else if(torus){
                    life.next(3);
                }else {
                    life.next();
                }
                i++;

            }

            if(gui.step && i <= generations){
                canvas.clear();
                life.paint(canvas, pixels);
                gui.redraw();
                sleep(speed);
                if(grid){
                    life.next(0);
                }else if(rod){
                    life.next(1);
                }else if(cylinder){
                    life.next(2);
                }else if(torus){
                    life.next(3);
                }else {
                    life.next();
                }
                gui.step = false;
                i++;

            }


            gui.genLabel.setText("Generation: " + life.generations());
        }


    }
}
