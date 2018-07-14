import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * Created by usr on 8/22/2017.
 */
public class Interactor {
    private static JFrame frame;
    public static JSpinner rfreq,rshft,gfreq,gshft,bfreq,bshft,rbrt,gbrt,bbrt,mag;
    public static SpinnerNumberModel rdef,gdef,bdef;
    public static JSpinner hsp,wsp,order;
    public static JSlider maxiter;
    public static JButton save;
    public static JToggleButton julia,swapset,smooth;
    public static JTextField fname;
    public static void init(){
        rdef=new SpinnerNumberModel(0.0,-0xffd,0xffd,0.01);
        gdef=new SpinnerNumberModel(0.0,-0xffd,0xffd,0.01);
        bdef=new SpinnerNumberModel(0.0,-0xffd,0xffd,0.01);

        frame = new JFrame("options");
        JPanel panel=new JPanel();
        JPanel colorset=ColorSettings_Init();
        JPanel saveset=Size_n_save();

        julia=new JToggleButton("toggle Julia Set");
        swapset=new JToggleButton("toggle Swap Set");
        smooth=new JToggleButton("toggle smooth coloring");
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
        panel.add(smooth);
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
        JPanel colorset=new JPanel(new GridLayout(10,2));
        rfreq=new JSpinner(rdef);
        rshft=new JSpinner(new SpinnerNumberModel(0.0,-0xffd,0xffd,0.1));
        gfreq=new JSpinner(gdef);
        gshft=new JSpinner(new SpinnerNumberModel(0.0,-0xffd,0xffd,0.1));
        bfreq=new JSpinner(bdef);
        bshft=new JSpinner(new SpinnerNumberModel(0.0,-0xffd,0xffd,0.1));
        rbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));
        gbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));
        bbrt=new JSpinner(new SpinnerNumberModel(200.0,0.0,255,1));
        mag=new JSpinner(new SpinnerNumberModel(2,-0xff,0xff,1));

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

        colorset.add(new JLabel("magnitude"));
        colorset.add(mag);


        return colorset;
    }
    private static JPanel Size_n_save(){
        JPanel saveset=new JPanel(new GridLayout(4,2));
        hsp=new JSpinner(new SpinnerNumberModel(2500,10,3000,2));
        wsp=new JSpinner(new SpinnerNumberModel(2500,10,3000,2));
        order=new JSpinner(new SpinnerNumberModel(2,0,100,1));
        save=new JButton("Save image");
        fname=new JTextField("image",15);
        saveset.add(new JLabel("Order: "));
        saveset.add(order);
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
    public static void set_all(){
        double step=Math.pow(10,-1*(int)mag.getValue());
        //System.out.println(step);
        rdef.setStepSize(step);
        gdef.setStepSize(step);
        bdef.setStepSize(step);
    }
}
