import javax.swing.*;

/**
 * Created by usr on 8/20/2017.
 *
 *
 */
public class Driver {

    public static void main(String... a){
        JFrame frame=new JFrame("mandelbrot gen");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setContentPane(new MainFrame());
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
