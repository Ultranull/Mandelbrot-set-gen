import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by usr on 8/20/2017.
 */
public class MainFrame extends JPanel {
    Fractgen gen=new Fractgen();
    int ticks=0;
    boolean clear;

    public MainFrame(){
        Interactor.init();
        gen.generate(ticks);
        addKeyListener(new KeyA());
        setFocusable(true);
        Timer t=new Timer(0,e->repaint());
        t.start();

    }
    @Override
    protected void paintComponent(Graphics g) {
        Interactor.set_all();
        super.paintComponent(g);
        if(clear){
            gen.clear();
            clear=false;
        }
        g.drawImage(gen.map,0,0,getWidth(),getHeight(),null);
        g.setColor(new Color(0xFFFFFF));
        g.drawString(rot+"",getWidth()/2,getHeight()/2);
        gen.pos=rot;//new Point(Math.cos(ticks/1.0*Math.PI/180)*rot.x,Math.sin(ticks/1.0*Math.PI/180)*rot.y);
        gen.generate(ticks);

        if(Interactor.save.getModel().isPressed()){
            String fn=Interactor.fname.getText();
            int t1=gen.Height,t2=gen.Width;
            gen.setDem((int)Interactor.hsp.getValue(),(int)Interactor.wsp.getValue());
            gen.generate(ticks);
            File outputfile = new File(fn+".png");
            try {
                ImageIO.write(gen.map, "png", outputfile);
            }catch (Exception ignored){}
            gen.setDem(t1,t2);
        }
        ticks+=1;
    }

    Complex rot=new Complex();
    private class KeyA extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                Interactor.destroy();
                System.exit(0);
            }
            double spd=1.0/(gen.zoom*25.0);
            if(e.getKeyCode()==KeyEvent.VK_UP)
                rot.y-=spd;
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
                rot.y+=spd;
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                rot.x+=spd;
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
                rot.x-=spd;

            if(e.getKeyCode()==KeyEvent.VK_I)
                gen.jpos.y-=spd;
            if(e.getKeyCode()==KeyEvent.VK_K)
                gen.jpos.y+=spd;
            if(e.getKeyCode()==KeyEvent.VK_L)
                gen.jpos.x+=spd;
            if(e.getKeyCode()==KeyEvent.VK_J)
                gen.jpos.x-=spd;

            if(e.getKeyCode()==KeyEvent.VK_OPEN_BRACKET)
                gen.zoom-=0.5*gen.zoom;
            if(e.getKeyCode()==KeyEvent.VK_CLOSE_BRACKET)
                gen.zoom+=0.5*gen.zoom;

            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                clear=true;
            }
        }
    }
}
