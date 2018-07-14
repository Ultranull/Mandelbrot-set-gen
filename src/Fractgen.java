
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


import static java.lang.Math.*;
/**
 * Created by usr on 8/20/2017.
 */
public class Fractgen {
    public BufferedImage map;
    public int Height,Width;
    public double zoom;
    public int maxiter;
    public Complex pos,jpos;
    public boolean julia=false;

    public Fractgen(){
        Height=Width=200;
        zoom=1;
        maxiter=100;
        pos=new Complex();
        jpos=new Complex();
        map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);
    }
    private double sigmoid(double x){
        return 1/(1+pow(E,-x));
    }
    private void draw(int x,int y,int h,int w,int i,double zmag,int ticks) {
        //if(i<=5&&i>-1)return;
        double r, g, b;
        double it;ticks=0;
        if (i < 0) {
            it = ((log((zmag)) / log(2)))*120.0d;
//            r = abs(sin( 0.0023* it +0.007))*255;
//            g = abs(sin( 0.001* it +0.006))*255;
//            b = abs(sin( 0.003* it +0.0045))*255;
             r = g = b = (abs((ticks+log((zmag)) / log(2)))/255)*255*2;
            r=g=b=(r>255)?255:r;
        } else {
            if(Interactor.smooth.isSelected())
                it =( i + 1 - log(log(abs(zmag)) / log(30)));
            else
                it=i;
            double fre=ticks/10;
            r = abs(sin(((double)Interactor.rfreq.getValue()+fre) * it +
                    ((double)Interactor.rshft.getValue()*0)))*
                    ((double)Interactor.rbrt.getValue());
            g = abs(sin(((double)Interactor.gfreq.getValue()+fre) * it +
                    ((double)Interactor.gshft.getValue())))*
                    ((double)Interactor.gbrt.getValue());
            b = abs(sin(((double)Interactor.bfreq.getValue()+fre) * it +
                    ((double)Interactor.bshft.getValue())))*
                    ((double)Interactor.bbrt.getValue());
            Color c=new Color(Color.HSBtoRGB(0.95f + 0.1f * (float)zmag ,0.6f,1.0f));
            r=c.getRed();
            g=c.getGreen();
            b=c.getBlue();


        }
        Graphics gl=map.getGraphics();
        gl.setColor(new Color((int)r,(int)g,(int)b));
        gl.fillRect(x,y,h,w);

    }
    double inmandel(int[] it,double x,double y,int max){
        Complex z=new Complex(),c=new Complex(x,y);
        double smoothcolor = Math.exp(-z.mag());
        while(z.mag() <= 30&&it[0]<max){
            z=z.pow((int)Interactor.order.getValue()).add(c);
            if(Interactor.swapset.getModel().isSelected())
                z=z.swap();
            it[0]++;
            smoothcolor += Math.exp(-z.mag());
        }
        return smoothcolor;
    }
    double injulia(int[] it,double x,double y,double x1,double y1,int max){
        Complex z=new Complex(x,y),c=new Complex(x1,y1);
        double smoothcolor = Math.exp(-z.mag());
        while(z.mag() <= 4&&it[0]<max){
            it[0]++;
            z=z.pow((int)Interactor.order.getValue()).add(c);
            if(Interactor.swapset.getModel().isSelected())
                z=z.swap();
            smoothcolor += Math.exp(-z.mag());
        }
        return smoothcolor;
    }
    void genpart(int ticks,Point xb,Point yb){
        for(int x=(int)xb.x;x<xb.y;x++){
            for(int y=(int)yb.x;y<yb.y;y++){
                int max=maxiter;
                int[] it={0};
                double zmag;
                if(julia) {
                    double xc=jpos.x+(((double)x/Width)*4-2)/zoom,
                            yc=jpos.y+(((double)y/Height)*4-2)/zoom;
                    zmag = injulia(it, xc, yc, pos.x, pos.y, max);
                } else {
                    double xc=pos.x+(((double)x/Width)*4-2)/zoom,
                            yc=pos.y+(((double)y/Height)*4-2)/zoom;
                    zmag = inmandel(it, xc, yc, max);
                }
                if(it[0]>=max)it[0]=-it[0];
                draw( x,y,1,1,it[0],zmag,ticks);
            }
        }

    }
    public void generate(int ticks){

        maxiter=Interactor.maxiter.getValue();
        julia=Interactor.julia.getModel().isSelected();
        long t=System.currentTimeMillis();
        genpart(ticks,new Point(0,Width), new Point(0, Height));
       // System.out.println(System.currentTimeMillis()-t);
    }
    void setDem(int h,int w){
        Width=w;Height=h;
        map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);

    }

void clear(){
    map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);
}

}
