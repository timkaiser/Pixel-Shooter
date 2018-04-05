package GUIObjekte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Textauswahlregler extends JLabel implements GUIObjekt,ActionListener{
    private double x,y,b,h;
    protected int zaehler;
    private Font font;
    private Knopf plus, minus;
    protected Schriftzug label;
    private String[] maps;
    

    public Textauswahlregler(double x, double y, double b, double h, Font font, Container jc, String...maps){
        super("");
        zaehler = 0;
        this.maps = maps;
        this.font = font;
        minus = new Knopf("<",0.0,0.1,0.2,0.8,font,this);
        minus.addActionListener(this);
        plus = new Knopf(">",0.8,0.1,0.2,0.8,font,this);     
        plus.addActionListener(this);
        label = new Schriftzug("",0.2,0.0,0.6,1,font,this);
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        textAktualisieren();
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
        minus.skallieren(getWidth(),getHeight());
        plus.skallieren(getWidth(),getHeight());
        label.skallieren(getWidth(),getHeight());
    }   

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == minus){
            if(zaehler>0){
                zaehler--;;
            }else{
                zaehler=maps.length-1;
            }
        }else 
        if(e.getSource() == plus){
            if(zaehler<maps.length-1){
                zaehler++;;
            }else{
                zaehler=0;
            }
        }
        textAktualisieren();
    }

    public void textAktualisieren(){
        label.setText(maps[zaehler]);
    }

    public String getAuswahlText(){
        return maps[zaehler];
    }
    
    public void setText(String... text){
        maps = text;
        zaehler=0;
        textAktualisieren();
    }
}
