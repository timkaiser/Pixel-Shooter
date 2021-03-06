import javax.swing.*;
import java.awt.*;

abstract class Objekt{
    protected double x,y;
    protected double breite,hoehe;
    protected Rectangle rect;
    public static final int genauigkeit = 10;
    public Objekt(double x, double y, double breite, double hoehe){
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        rect = new Rectangle((int) x,(int) y, (int) breite, (int) hoehe);
    }

    abstract void zeichnen(Graphics g, JPanel panel);

    public boolean kollision(Rectangle r){
        return rect.intersects(r);
    }

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getHoehe(){
        return hoehe;
    }

    public void setHoehe(double hoehe){
        this.hoehe = hoehe;
    }

    public double getBreite(){
        return breite;
    }

    public void setBreite(double breite){
        this.breite = breite;
    }

}
