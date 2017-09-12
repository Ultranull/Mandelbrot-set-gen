import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * Created by usr on 8/22/2017.
 */
public class Interactor {
    private static JFrame frame;
    public static JSpinner rfreq,rshft,gfreq,gshft,bfreq,bshft,rbrt,gbrt,bbrt;
    public static JSpinner hsp,wsp;
    public static JSlider maxiter;
    public static JButton save;
    public static JToggleButton julia,swapset;
    public static JTextField fname;
    public static void init(){

        frame = new JFrame("options");
        JPanel panel=new JPanel();
        JPanel colorset=ColorSettings_Init();
        JPanel saveset=Size_n_save();

        julia=new JToggleButton("toggle Julia Set");
        swapset=new JToggleButton("toggle Swap Set");
        maxiter=new JSlider(JSlider.VERTICAL,0,2048,300);
        maxiter.setMajorTickSpacing(128);
        maxiter.setMinorTickSpacing(64);
        maxiter.setPaintTicks(true);
        maxiter.setPaintLabels(true);

        panel.add("Color Settings",colorset);
        panel.add(saveset);
        panel.add(maxiter);
        panel.add(julia);
        panel.add(swapset);
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        frame.setSize(700, 50 + 300);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private static JPanel ColorSettings_Init(){
        JPanel colorset=new JPanel(new GridLayout(9,2));
        rfreq=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.01));
        rshft=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.1));
        gfreq=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.01));
        gshft=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.1));
        bfreq=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.01));
        bshft=new JSpinner(new SpinnerNumberModel(0.0,0.0,0xffd,0.1));
        rbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));
        gbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));
        bbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));

        colorset.add(new JLabel("Red Freq"));
        colorset.add(rfreq);
        colorset.add(new JLabel("Red shift"));
        colorset.add(rshft);
        colorset.add(new JLabel("Red Brightness"));
        colorset.add(rbrt);

        colorset.add(new JLabel("Green Freq"));
        colorset.add(gfreq);
        colorset.add(new JLabel("Green shift"));
        colorset.add(gshft);
        colorset.add(new JLabel("Green Brightness"));
        colorset.add(gbrt);

        colorset.add(new JLabel("Blue Freq"));
        colorset.add(bfreq);
        colorset.add(new JLabel("Blue shift"));
        colorset.add(bshft);
        colorset.add(new JLabel("Blue Brightness"));
        colorset.add(bbrt);


        return colorset;
    }
    private static JPanel Size_n_save(){
        JPanel saveset=new JPanel(new GridLayout(3,2));
        hsp=new JSpinner(new SpinnerNumberModel(1080,10,3000,2));
        wsp=new JSpinner(new SpinnerNumberModel(1920,10,3000,2));
        save=new JButton("Save image");
        fname=new JTextField("image",15);
        saveset.add(new JLabel("Width:"));
        saveset.add(wsp);
        saveset.add(new JLabel("Height:"));
        saveset.add(hsp);
        saveset.add(save);
        saveset.add(fname);
        return saveset;
    }
    public static void destroy(){
        frame.dispose();
    }
}
