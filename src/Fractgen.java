
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
    public Point pos,jpos;
    public boolean julia=false;

    public Fractgen(){
        Height=Width=200;
        zoom=1;
        maxiter=100;
        pos=new Point(0,0);
        jpos=new Point(0,0);
        map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);
    }
    private void draw(int x,int y,int h,int w,int i,double zmag,int ticks) {
        //if(i<=5&&i>-1)return;
        double r, g, b;
        double it;ticks=0;
        if (i < 0) {
            /*it = ((log((zmag)) / log(2)))*128;
            r = abs(sin(((double)Interactor.rfreq.getValue()) * it +
                    ((double)Interactor.rshft.getValue())+ticks/6.0))*
                    ((double)Interactor.rbrt.getValue());
            g = abs(sin(((double)Interactor.gfreq.getValue()) * it +
                    ((double)Interactor.gshft.getValue())+ticks/6.0))*
                    ((double)Interactor.gbrt.getValue());
            b = abs(sin(((double)Interactor.bfreq.getValue()) * it +
                    ((double)Interactor.bshft.getValue())+ticks/6.0))*
                    ((double)Interactor.bbrt.getValue());*/
             r = g = b = (abs((ticks+log((zmag)) / log(2)))/255)*255*2;
            r=g=b=(r>255)?255:r;
        } else {
            it =( i + 1 - (log(log(zmag)) / log(2)));
            r = abs(sin(((double)Interactor.rfreq.getValue()) * it +
                    ((double)Interactor.rshft.getValue())+ticks/6.0))*
                    ((double)Interactor.rbrt.getValue());
            g = abs(sin(((double)Interactor.gfreq.getValue()) * it +
                    ((double)Interactor.gshft.getValue())+ticks/6.0))*
                    ((double)Interactor.gbrt.getValue());
            b = abs(sin(((double)Interactor.bfreq.getValue()) * it +
                    ((double)Interactor.bshft.getValue())+ticks/6.0))*
                    ((double)Interactor.bbrt.getValue());

        }
        Graphics gl=map.getGraphics();
        gl.setColor(new Color((int)r,(int)g,(int)b));
        gl.fillRect(x,y,h,w);

    }
    private double javaswap(double itself,double dummy){
        return itself;
    }
    double inmandel(int[] it,double x,double y,int max){
        double cx=x,cy=y;
        double zx=0,zy=0;
        while(zx*zx+zy*zy <= 4&&it[0]<max){
            double x_new = zx*zx - zy*zy + cx;
            zy = 2*zx*zy + cy;
            zx = x_new;
            if(Interactor.swapset.getModel().isSelected())
                zx=javaswap(zy,zy=zx);
            it[0]++;
        }
        return zx*zx+zy*zy;
    }
    double injulia(int[] it,double x,double y,double x1,double y1,int max){
        double cx=x1,cy=y1;
        double zx=x,zy=y;
        while(zx*zx+zy*zy <= 4&&it[0]<max){
            it[0]++;
            double x_new = zx*zx - zy*zy + cx;
            zy = 2*zx*zy + cy;
            zx = x_new;
            if(Interactor.swapset.getModel().isSelected())
                zx=javaswap(zy,zy=zx);
        }
        return zx*zx+zy*zy;
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
        System.out.println(System.currentTimeMillis()-t);
    }
    void setDem(int h,int w){
        Width=w;Height=h;
        map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);

    }

void clear(){
    map=new BufferedImage(Width,Height,BufferedImage.TYPE_4BYTE_ABGR);
}

}
