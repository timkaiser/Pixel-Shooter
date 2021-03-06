package GUIObjekte;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Bildanzeige extends JLabel implements GUIObjekt{
    private double x,y,b,h;
    private Font font;
    private BufferedImage bild;
    
    public Bildanzeige(BufferedImage img, double x, double y, double b, double h, Container jc)    {
        super("");
        bild = img;
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        setVisible(true);
        this.x=x;
        this.y=y;
        this.b=b;
        this.h=h;
        this.font=font;
        jc.add(this); 
        skallieren(jc.getWidth(),jc.getHeight());     
    }

    public void skallieren(int frameBreite, int frameHoehe){
        setBounds((int)(frameBreite*x),(int)(frameHoehe*y),(int)(frameBreite*b),(int)(frameHoehe*h));
        setFont(font.deriveFont((float)(frameHoehe*h/2.5)));
    }   

    protected void paintComponent( Graphics g ) { 
        super.paintComponent(g);
        g.drawImage(bild,0,0,getWidth(),getHeight(),this);
    }
    
}
