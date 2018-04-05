import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import GUIObjekte.*;
public class Optionsmenue extends JPanel implements ActionListener, KeyListener{
    private Font font;
    private static Knopf steuerung, fenstergroesse, lautstaerke, zurueckInsHauptmenue, zurueckInsOptionsmenue, zurueckZumSpiel; //Im Optionsmenue
    private static Knopf musikLauter, musikLeiser, soundLauter, soundLeiser, musikBalken, soundBalken, musikAn, soundAn, soundAnzahl, musikAnzahl;; //Lautstaerkemenue
    private static Knopf vollbild, klein, mittel, gross; //Fenstergroessemenue
    private static Knopf oben1, oben2, oben3, oben4, links1, links2, links3, links4, rechts1, rechts2, rechts3, rechts4, unten1, unten2, unten3, unten4, schiessen1, schiessen2, schiessen3, schiessen4, ducken1, ducken2, ducken3, ducken4, pause;
    private static Schriftzug sp1, sp2, sp3, sp4, o, u, r, l, s, d;
    private static boolean fullscreenAn=false;
    private static int fenstergroesseX, fenstergroesseY;
    private static boolean musikIstAn=true;
    private static boolean soundIstAn=true;
    private static double musikBalkenPosition=0.5; //Position des Musiklautstaerkebalkens
    private static double soundBalkenPosition=0.5; //Position des Soundlautstaerkebalkens
    private static int soundLautstaerkeBegrenzung=10;
    private static int musikLautstaerkeBegrenzung=10;//Max 20, min 0
    private boolean fensterVeraendern=true;
    public static Fenster container;
    public Optionsmenue(Fenster container){
        this.container=container;
        setBackground(Color.ORANGE);
        setLayout(null);
        setFocusable(true);

        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        steuerung=new Knopf("Steuerung",           0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        steuerung.addActionListener(this);

        fenstergroesse=new Knopf("Fenstergroesse", 0, 3.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        fenstergroesse.addActionListener(this);

        lautstaerke=new Knopf("Lautstaerke",       0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        lautstaerke.addActionListener(this);

        zurueckInsHauptmenue=new Knopf("Zurueck",   0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);        
        zurueckInsHauptmenue.addActionListener(this);

        setVisible(true);
        //Hauptmenue.knoepfeEntfernen();
    }

    public void actionPerformed(ActionEvent e){ 
        if(e.getSource()==steuerung){
            knoepfeEntfernen();           
            sp1=new Schriftzug("Spieler 1",  1.5/10.0, 0.1/10.0, 1.0/4.0, 1.0/15.0,font, this);
            sp2=new Schriftzug("Spieler 2",  3.5/10.0, 0.1/10.0, 1.0/4.0, 1.0/15.0,font, this);
            sp3=new Schriftzug("Spieler 3",  5.5/10.0, 0.1/10.0, 1.0/4.0, 1.0/15.0,font, this);
            sp4=new Schriftzug("Spieler 4",  7.5/10.0, 0.1/10.0, 1.0/4.0, 1.0/15.0,font, this);

            o=new Schriftzug("Oben",         0.02, 1.2/10.0, 1.0/4.0, 1.0/15.0,font, this);            
            l=new Schriftzug("Links",        0.02, 2.4/10.0, 1.0/4.0, 1.0/15.0,font, this);           
            r=new Schriftzug("Rechts",       0.02, 3.6/10.0, 1.0/4.0, 1.0/15.0,font, this);            
            u=new Schriftzug("Unten",        0.02, 4.8/10.0, 1.0/4.0, 1.0/15.0,font, this);            
            s=new Schriftzug("Schiessen",    0.02, 6.0/10.0, 1.0/4.0, 1.0/15.0,font, this);            
            d=new Schriftzug("Ducken",       0.02, 7.2/10.0, 1.0/4.0, 1.0/15.0,font, this);

            oben1=new Knopf(Steuerung.getOben1Text(), 1.0/10.0, 1.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            oben1.addActionListener(this);
            oben1.addKeyListener(this);
            oben2=new Knopf(Steuerung.getOben2Text(), 3.0/10.0, 1.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            oben2.addActionListener(this);
            oben2.addKeyListener(this);
            oben3=new Knopf(Steuerung.getOben3Text(), 5.0/10.0, 1.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            oben3.addActionListener(this);
            oben3.addKeyListener(this);
            oben4=new Knopf(Steuerung.getOben4Text(), 7.0/10.0, 1.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            oben4.addActionListener(this);
            oben4.addKeyListener(this);

            links1=new Knopf(Steuerung.getLinks1Text(), 1.0/10.0, 2.4/10.0, 1.0/5.0, 1.0/15.0,font, this);
            links1.addActionListener(this);
            links1.addKeyListener(this);
            links2=new Knopf(Steuerung.getLinks2Text(), 3.0/10.0, 2.4/10.0, 1.0/5.0, 1.0/15.0,font, this);
            links2.addActionListener(this);
            links2.addKeyListener(this);
            links3=new Knopf(Steuerung.getLinks3Text(), 5.0/10.0, 2.4/10.0, 1.0/5.0, 1.0/15.0,font, this);
            links3.addActionListener(this);
            links3.addKeyListener(this);
            links4=new Knopf(Steuerung.getLinks4Text(), 7.0/10.0, 2.4/10.0, 1.0/5.0, 1.0/15.0,font, this);
            links4.addActionListener(this);
            links4.addKeyListener(this);

            rechts1=new Knopf(Steuerung.getRechts1Text(), 1.0/10.0, 3.6/10.0, 1.0/5.0, 1.0/15.0,font, this);
            rechts1.addActionListener(this);
            rechts1.addKeyListener(this);
            rechts2=new Knopf(Steuerung.getRechts2Text(), 3.0/10.0, 3.6/10.0, 1.0/5.0, 1.0/15.0,font, this);
            rechts2.addActionListener(this);
            rechts2.addKeyListener(this);
            rechts3=new Knopf(Steuerung.getRechts3Text(), 5.0/10.0, 3.6/10.0, 1.0/5.0, 1.0/15.0,font, this);
            rechts3.addActionListener(this);
            rechts3.addKeyListener(this);
            rechts4=new Knopf(Steuerung.getRechts4Text(), 7.0/10.0, 3.6/10.0, 1.0/5.0, 1.0/15.0,font, this);
            rechts4.addActionListener(this);
            rechts4.addKeyListener(this);

            unten1=new Knopf(Steuerung.getUnten1Text(), 1.0/10.0, 4.8/10.0, 1.0/5.0, 1.0/15.0,font, this);
            unten1.addActionListener(this);
            unten1.addKeyListener(this);
            unten2=new Knopf(Steuerung.getUnten2Text(), 3.0/10.0, 4.8/10.0, 1.0/5.0, 1.0/15.0,font, this);
            unten2.addActionListener(this);
            unten2.addKeyListener(this);
            unten3=new Knopf(Steuerung.getUnten3Text(), 5.0/10.0, 4.8/10.0, 1.0/5.0, 1.0/15.0,font, this);
            unten3.addActionListener(this);
            unten3.addKeyListener(this);
            unten4=new Knopf(Steuerung.getUnten4Text(), 7.0/10.0, 4.8/10.0, 1.0/5.0, 1.0/15.0,font, this);
            unten4.addActionListener(this);
            unten4.addKeyListener(this);

            schiessen1=new Knopf(Steuerung.getSchiessen1Text(), 1.0/10.0, 6.0/10.0, 1.0/5.0, 1.0/15.0,font, this);
            schiessen1.addActionListener(this);
            schiessen1.addKeyListener(this);
            schiessen2=new Knopf(Steuerung.getSchiessen2Text(), 3.0/10.0, 6.0/10.0, 1.0/5.0, 1.0/15.0,font, this); 
            schiessen2.addActionListener(this);
            schiessen2.addKeyListener(this);
            schiessen3=new Knopf(Steuerung.getSchiessen3Text(), 5.0/10.0, 6.0/10.0, 1.0/5.0, 1.0/15.0,font, this); 
            schiessen3.addActionListener(this);   
            schiessen3.addKeyListener(this);
            schiessen4=new Knopf(Steuerung.getSchiessen4Text(), 7.0/10.0, 6.0/10.0, 1.0/5.0, 1.0/15.0,font, this); 
            schiessen4.addActionListener(this);
            schiessen4.addKeyListener(this);

            ducken1=new Knopf(Steuerung.getDucken1Text(), 1.0/10.0, 7.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            ducken1.addActionListener(this);
            ducken1.addKeyListener(this);
            ducken2=new Knopf(Steuerung.getDucken2Text(), 3.0/10.0, 7.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            ducken2.addActionListener(this);
            ducken2.addKeyListener(this);
            ducken3=new Knopf(Steuerung.getDucken3Text(), 5.0/10.0, 7.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            ducken3.addActionListener(this);
            ducken3.addKeyListener(this);
            ducken4=new Knopf(Steuerung.getDucken4Text(), 7.0/10.0, 7.2/10.0, 1.0/5.0, 1.0/15.0,font, this);
            ducken4.addActionListener(this);
            ducken4.addKeyListener(this);

            zurueckInsOptionsmenue=new Knopf("Zurueck",1.0/4.0, 5.0/6.0, 1.0/2.0, 1.0/5.0,font,this);
            zurueckInsOptionsmenue.addActionListener(this);
        }       
        else if(e.getSource()==oben1){
            alleEntmarkieren();
            oben1.markieren();
        }
        else if(e.getSource()==oben2){
            alleEntmarkieren();
            oben2.markieren();
        }
        else if(e.getSource()==oben3){
            alleEntmarkieren();
            oben3.markieren();
        }
        else if(e.getSource()==oben4){
            alleEntmarkieren();
            oben4.markieren();
        }
        else if(e.getSource()==links1){
            alleEntmarkieren();
            links1.markieren();
        }
        else if(e.getSource()==links2){
            alleEntmarkieren();
            links2.markieren();
        }
        else if(e.getSource()==links3){
            alleEntmarkieren();
            links3.markieren();
        }
        else if(e.getSource()==links4){
            alleEntmarkieren();
            links4.markieren();
        }
        else if(e.getSource()==rechts1){
            alleEntmarkieren();
            rechts1.markieren();
        }
        else if(e.getSource()==rechts2){
            alleEntmarkieren();
            rechts2.markieren();
        }
        else if(e.getSource()==rechts3){
            alleEntmarkieren();
            rechts3.markieren();
        }
        else if(e.getSource()==rechts4){
            alleEntmarkieren();
            rechts4.markieren();
        }
        else if(e.getSource()==unten1){
            alleEntmarkieren();
            unten1.markieren();
        }
        else if(e.getSource()==unten2){
            alleEntmarkieren();
            unten2.markieren();
        }
        else if(e.getSource()==unten3){
            alleEntmarkieren();
            unten3.markieren();
        }
        else if(e.getSource()==unten4){
            alleEntmarkieren();
            unten4.markieren();
        }
        else if(e.getSource()==schiessen1){
            alleEntmarkieren();
            schiessen1.markieren();
        }
        else if(e.getSource()==schiessen2){
            alleEntmarkieren();
            schiessen2.markieren();
        }
        else if(e.getSource()==schiessen3){
            alleEntmarkieren();
            schiessen3.markieren();
        }
        else if(e.getSource()==schiessen4){
            alleEntmarkieren();
            schiessen4.markieren();
        }
        else if(e.getSource()==ducken1){
            alleEntmarkieren();
            ducken1.markieren();
        }
        else if(e.getSource()==ducken2){
            alleEntmarkieren();
            ducken2.markieren();
        }
        else if(e.getSource()==ducken3){
            alleEntmarkieren();
            ducken3.markieren();
        }
        else if(e.getSource()==ducken4){
            alleEntmarkieren();
            ducken4.markieren();
        }
        //-------------------------------------------------

        else if(e.getSource()==fenstergroesse){
            knoepfeEntfernen();
            vollbild=new Knopf("Vollbild An/Aus", 0, 1.0/20.0, 1, 1.0/6.0, font, this);
            vollbild.addActionListener(this); 
            vollbild.setVisible(true);
            zurueckInsOptionsmenue=new Knopf("Zurueck",0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
            zurueckInsOptionsmenue.addActionListener(this);  
            klein=new Knopf("Klein",              0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
            klein.addActionListener(this);
            mittel=new Knopf("Mittel",              0, 3.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
            mittel.addActionListener(this);
            gross=new Knopf("Gross",              0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
            gross.addActionListener(this);
            if(fullscreenAn==true){
                klein.setVisible(false);
                mittel.setVisible(false);
                gross.setVisible(false);
            }
        }
        else if(e.getSource()==vollbild){
            if(fullscreenAn==false){
                container.fullscreenAn();
                knoepfeEntfernen();      
                vollbild=new Knopf("Vollbild An/Aus", 0, 1.0/20.0, 1, 1.0/6.0, font, this);
                vollbild.addActionListener(this);
                zurueckInsOptionsmenue=new Knopf("Zurueck",0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                zurueckInsOptionsmenue.addActionListener(this);                  
                fullscreenAn=true;
            }   
            else{
                container.fullscreenAus();
                knoepfeEntfernen();
                vollbild=new Knopf("Vollbild An/Aus", 0, 1.0/20.0, 1, 1.0/6.0, font, this);
                vollbild.addActionListener(this);
                zurueckInsOptionsmenue=new Knopf("Zurueck",0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                zurueckInsOptionsmenue.addActionListener(this);
                klein.setVisible(true);
                mittel.setVisible(true);
                gross.setVisible(true);
                fullscreenAn=false;
            }            
        }
        else if(e.getSource()==klein){
            fenstergroesseX=810;
            fenstergroesseY=600;
            container.setFensterX(fenstergroesseX);
            container.setFensterY(fenstergroesseY);
        }
        else if(e.getSource()==mittel){
            fenstergroesseX=1040;
            fenstergroesseY=800;
            container.setFensterX(fenstergroesseX);
            container.setFensterY(fenstergroesseY);               
        }
        else if(e.getSource()==gross){
            fenstergroesseX=1200;
            fenstergroesseY=900;
            container.setFensterX(fenstergroesseX);
            container.setFensterY(fenstergroesseY);
        }    
        else if(fensterVeraendern==false&&klein!=null&&mittel!=null&&gross!=null){
            klein.setVisible(false);
            mittel.setVisible(false);
            gross.setVisible(false);
        }

        //-------------------------------------------------
        else if(e.getSource()==lautstaerke){
            knoepfeEntfernen();
            if(musikIstAn==true){
                musikAn=new Knopf("Musik An",                  0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                musikAn.addActionListener(this);
                musikAn.setVisible(false);
            }
            else{
                musikAn=new Knopf("Musik Aus",                  0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                musikAn.addActionListener(this);
                musikAn.setVisible(false);
            }
            if(soundIstAn==true){
                soundAn=new Knopf("Sound An",                  0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                soundAn.addActionListener(this);
            }
            else{
                soundAn=new Knopf("Sound Aus",                  0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                soundAn.addActionListener(this);
            }
            /*musikLauter=new Knopf("Lauter",               1.0/1.1, 1.0/10.0, 1.0/2.0, 1.0/6.0, font, this);
            musikLauter.addActionListener(this); 
            musikLeiser=new Knopf("Leiser",               1.0/10.0, 1.0/10.0, 1.0/2.0, 1.0/6.0, font, this);
            musikLeiser.addActionListener(this);
            soundLauter=new Knopf("Lauter",               1.0/1.1, 1.0/1.7, 1.0/2.0, 1.0/6.0, font, this);
            soundLauter.addActionListener(this);
            soundLeiser=new Knopf("Leiser",               1.0/10.0, 1.0/1.7, 1.0/2.0, 1.0/6.0, font, this);
            soundLeiser.addActionListener(this);
            
            musikBalken=new Knopf("|", musikBalkenPosition,     1.0/10.0, 1.0/2.0, 1.0/6.0, font, this);
            musikBalken.setEnabled(false);
            soundBalken=new Knopf("|", soundBalkenPosition,     1.0/1.6, 1.0/2.0, 1.0/6.0, font, this);
            soundBalken.setEnabled(false);

            musikAnzahl=new Knopf(""+musikLautstaerkeBegrenzung, 1.0/2.0, 1.0/6.0, 1.0/2.0, 1.0/6.0, font, this);
            soundAnzahl=new Knopf(""+soundLautstaerkeBegrenzung, 1.0/2.0, 1.0/1.4, 1.0/2.0, 1.0/6.0, font, this);*/
            
            zurueckInsOptionsmenue=new Knopf("Zurueck",          0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
            zurueckInsOptionsmenue.addActionListener(this);
        }
        else if(e.getSource()==musikAn){
            if(musikIstAn==true){
                musikAn.setVisible(false);
                musikAn=new Knopf("Musik Aus", 0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                musikAn.addActionListener(this);
                musikIstAn=false;
                Fenster.musikHalt();
                //Musik Aus
            }
            else{
                musikAn.setVisible(false);
                musikAn=new Knopf("Musik An", 0, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                musikAn.addActionListener(this);
                musikIstAn=true;
                Fenster.musikWeiter("File Select - Super Mario 64.wav");
                //Musik An
            }
        }
        else if(e.getSource()==soundAn){
            if(soundIstAn==true){
                soundAn.setVisible(false);
                soundAn=new Knopf("Sound Aus", 0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                soundAn.addActionListener(this);
                soundIstAn=false;
                //Sound Aus
            }
            else{
                soundAn.setVisible(false);
                soundAn=new Knopf("Sound An", 0, 4.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
                soundAn.addActionListener(this);
                soundIstAn=true;
                //Sound An
            }
        }
        /*else if(e.getSource()==musikLauter){
            musikLautstaerkebalkenVerschieben(true);
        }
        else if(e.getSource()==musikLeiser){
            musikLautstaerkebalkenVerschieben(false);
        }   
        else if(e.getSource()==soundLauter){
            soundLautstaerkebalkenVerschieben(true);
        }
        else if(e.getSource()==soundLeiser){
            soundLautstaerkebalkenVerschieben(false);
        }*/

        if(e.getSource()==zurueckInsOptionsmenue){
            container.remove(this);
            container.add(new Optionsmenue(container));
            container.revalidate();
            container.repaint();
            einstellungenSpeichern();
        }
        else if(e.getSource()==zurueckInsHauptmenue){
            container.remove(this);
            container.add(new Hauptmenue(container));
            container.revalidate();
            container.repaint();
            einstellungenSpeichern();
        }
        if(e.getSource()==zurueckZumSpiel){
            Fenster.optionsmenueSchliessen();
        }
    }

    public static void knoepfeEntfernen(){
        steuerung.setVisible(false);
        fenstergroesse.setVisible(false);
        lautstaerke.setVisible(false);
        zurueckInsHauptmenue.setVisible(false);

        if(vollbild!=null){
            vollbild.setVisible(false);
        }
        if(zurueckInsOptionsmenue!=null){
            zurueckInsOptionsmenue.setVisible(false);
        }
        if(klein!=null&&mittel!=null&&gross!=null){
            klein.setVisible(false);
            mittel.setVisible(false);
            gross.setVisible(false);
        }
        if(musikLauter!=null){
            musikLauter.setVisible(false);
            musikLeiser.setVisible(false);
            soundLauter.setVisible(false);
            soundLeiser.setVisible(false);
            musikAn.setVisible(false);
            soundAn.setVisible(false);
        }
    }

    public void musikLautstaerkebalkenVerschieben(boolean lauter){
        if(lauter==true&&musikLautstaerkeBegrenzung<20){
            musikAnzahl.setVisible(false);
            musikBalken.setVisible(false);
            musikBalkenPosition=musikBalkenPosition+0.03;
            musikBalken=new Knopf("|", musikBalkenPosition, 1.0/10.0, 1.0/2.0, 1.0/6.0, font, this);
            musikBalken.setEnabled(false);
            musikLautstaerkeBegrenzung++;

            musikAnzahl=new Knopf(""+musikLautstaerkeBegrenzung, 1.0/2.0, 1.0/6.0, 1.0/2.0, 1.0/6.0, font, this);
        }
        else if(lauter==false&&musikLautstaerkeBegrenzung>0){
            musikAnzahl.setVisible(false);
            musikBalken.setVisible(false);
            musikBalkenPosition=musikBalkenPosition-0.03;
            musikBalken=new Knopf("|", musikBalkenPosition, 1.0/10.0, 1.0/2.0, 1.0/6.0, font, this);
            musikBalken.setEnabled(false);
            musikLautstaerkeBegrenzung--;

            musikAnzahl=new Knopf(""+musikLautstaerkeBegrenzung, 1.0/2.0, 1.0/6.0, 1.0/2.0, 1.0/6.0, font, this);
        }
        if(musikLautstaerkeBegrenzung>20){
            musikLautstaerkeBegrenzung=19;
            musikBalkenPosition=musikBalkenPosition-0.03;
        }
        if(musikLautstaerkeBegrenzung<0){
            musikLautstaerkeBegrenzung=1;
            musikBalkenPosition=musikBalkenPosition+0.03;
        }
    }

    public void soundLautstaerkebalkenVerschieben(boolean lauter){
        if(lauter==true&&soundLautstaerkeBegrenzung<20){
            soundAnzahl.setVisible(false);
            soundBalken.setVisible(false);
            soundBalkenPosition=soundBalkenPosition+0.03;
            soundBalken=new Knopf("|", soundBalkenPosition, 1.0/1.6, 1.0/2.0, 1.0/6.0, font, this);
            soundBalken.setEnabled(false);
            soundLautstaerkeBegrenzung++;

            soundAnzahl=new Knopf(""+soundLautstaerkeBegrenzung, 1.0/2.0, 1.0/1.4, 1.0/2.0, 1.0/6.0, font, this);
        }
        else if(lauter==false&&soundLautstaerkeBegrenzung>0){
            soundAnzahl.setVisible(false);
            soundBalken.setVisible(false);
            soundBalkenPosition=soundBalkenPosition-0.03;
            soundBalken=new Knopf("|", soundBalkenPosition, 1.0/1.6, 1.0/2.0, 1.0/6.0, font, this);
            soundBalken.setEnabled(false);
            soundLautstaerkeBegrenzung--;

            soundAnzahl=new Knopf(""+soundLautstaerkeBegrenzung, 1.0/2.0, 1.0/1.4, 1.0/2.0, 1.0/6.0, font, this);
        }
        if(soundLautstaerkeBegrenzung>20){
            soundLautstaerkeBegrenzung=19;
            soundBalkenPosition=soundBalkenPosition-0.03;
        }
        if(soundLautstaerkeBegrenzung<0){
            soundLautstaerkeBegrenzung=1;
            soundBalkenPosition=soundBalkenPosition+0.03;
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setPreferredSize(null);
        steuerung.skallieren(getWidth(), getHeight());
        fenstergroesse.skallieren(getWidth(), getHeight());
        lautstaerke.skallieren(getWidth(), getHeight());
        if(zurueckInsHauptmenue!=null){zurueckInsHauptmenue.skallieren(getWidth(), getHeight());}
        if(zurueckInsOptionsmenue!=null){zurueckInsOptionsmenue.skallieren(getWidth(), getHeight());}
        if(vollbild!=null){vollbild.skallieren(getWidth(), getHeight());}
        if(musikLauter!=null){musikLauter.skallieren(getWidth(), getHeight());}
        if(musikLeiser!=null){musikLeiser.skallieren(getWidth(), getHeight());}
        if(soundLauter!=null){soundLauter.skallieren(getWidth(), getHeight());}
        if(soundLeiser!=null){soundLeiser.skallieren(getWidth(), getHeight());}
        if(klein!=null){klein.skallieren(getWidth(), getHeight());}
        if(mittel!=null){mittel.skallieren(getWidth(), getHeight());}
        if(gross!=null){gross.skallieren(getWidth(), getHeight());} 
        if(zurueckZumSpiel!=null){zurueckZumSpiel.skallieren(getWidth(), getHeight());}
    }

    public static void tasteAendern(Knopf welche){
        welche.setForeground(new Color(115, 15, 250));
    }

    public static void alleEntmarkieren(){
        oben1.entmarkieren();
        oben2.entmarkieren();
        oben3.entmarkieren();
        oben4.entmarkieren();

        links1.entmarkieren();
        links2.entmarkieren();
        links3.entmarkieren();
        links4.entmarkieren();

        rechts1.entmarkieren();
        rechts2.entmarkieren();
        rechts3.entmarkieren();
        rechts4.entmarkieren();

        unten1.entmarkieren();
        unten2.entmarkieren();
        unten3.entmarkieren();
        unten4.entmarkieren();

        schiessen1.entmarkieren();
        schiessen2.entmarkieren();
        schiessen3.entmarkieren();
        schiessen4.entmarkieren();

        ducken1.entmarkieren();
        ducken2.entmarkieren();
        ducken3.entmarkieren();
        ducken4.entmarkieren();
    }    

    public boolean istTasteBelegt(int taste){
        if(
        Steuerung.getOben1()==taste||
        Steuerung.getOben2()==taste||
        Steuerung.getOben3()==taste||
        Steuerung.getOben4()==taste||
        Steuerung.getLinks1()==taste||
        Steuerung.getLinks2()==taste||
        Steuerung.getLinks3()==taste||
        Steuerung.getLinks4()==taste||
        Steuerung.getRechts1()==taste||
        Steuerung.getRechts2()==taste||
        Steuerung.getRechts3()==taste||
        Steuerung.getRechts4()==taste||
        Steuerung.getUnten1()==taste||
        Steuerung.getUnten2()==taste||
        Steuerung.getUnten3()==taste||
        Steuerung.getUnten4()==taste||
        Steuerung.getSchiessen1()==taste||
        Steuerung.getSchiessen2()==taste||
        Steuerung.getSchiessen3()==taste||
        Steuerung.getSchiessen4()==taste||
        Steuerung.getDucken1()==taste||
        Steuerung.getDucken2()==taste||
        Steuerung.getDucken3()==taste||
        Steuerung.getDucken4()==taste||

        Steuerung.getPause()==taste){
            return true;
        }
        else{
            return false;
        }
    }

    public void keyPressed(KeyEvent e){
        if(oben1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setOben1(e.getKeyCode());
                oben1.setText(Steuerung.getOben1Text());
            }
            oben1.entmarkieren();
        }
        if(oben2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setOben2(e.getKeyCode());
                oben2.setText(Steuerung.getOben2Text());
            }
            oben2.entmarkieren();
        }
        if(oben3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setOben3(e.getKeyCode());
                oben3.setText(Steuerung.getOben3Text());
            }
            oben3.entmarkieren();
        }
        if(oben4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setOben4(e.getKeyCode());
                oben4.setText(Steuerung.getOben4Text());
            }
            oben4.entmarkieren();
        }
        if(links1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setLinks1(e.getKeyCode());
                links1.setText(Steuerung.getLinks1Text());
            }
            links1.entmarkieren();
        }
        if(links2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setLinks2(e.getKeyCode());
                links2.setText(Steuerung.getLinks2Text());
            }
            links2.entmarkieren();
        }
        if(links3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setLinks3(e.getKeyCode());
                links3.setText(Steuerung.getLinks3Text());
            }
            links3.entmarkieren();
        }
        if(links4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setLinks4(e.getKeyCode());
                links4.setText(Steuerung.getLinks4Text());
            }
            links4.entmarkieren();
        }
        if(rechts1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setRechts1(e.getKeyCode());
                rechts1.setText(Steuerung.getRechts1Text());
            }
            rechts1.entmarkieren();
        }
        if(rechts2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setRechts2(e.getKeyCode());
                rechts2.setText(Steuerung.getRechts2Text());
            }
            rechts2.entmarkieren();
        }
        if(rechts3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setRechts3(e.getKeyCode());
                rechts3.setText(Steuerung.getRechts3Text());
            }
            rechts3.entmarkieren();
        }
        if(rechts4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setRechts4(e.getKeyCode());
                rechts4.setText(Steuerung.getRechts4Text());
            }
            rechts4.entmarkieren();
        }
        if(unten1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setUnten1(e.getKeyCode());
                unten1.setText(Steuerung.getUnten1Text());
            }
            unten1.entmarkieren();
        }
        if(unten2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setUnten2(e.getKeyCode());
                unten2.setText(Steuerung.getUnten2Text());
            }
            unten2.entmarkieren();
        }
        if(unten3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setUnten3(e.getKeyCode());
                unten3.setText(Steuerung.getUnten3Text());
            }
            unten3.entmarkieren();
        }
        if(unten4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setUnten4(e.getKeyCode());
                unten4.setText(Steuerung.getUnten4Text());
            }
            unten4.entmarkieren();
        }
        if(schiessen1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setSchiessen1(e.getKeyCode());
                schiessen1.setText(Steuerung.getSchiessen1Text());
            }
            schiessen1.entmarkieren();
        }
        if(schiessen2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setSchiessen2(e.getKeyCode());
                schiessen2.setText(Steuerung.getSchiessen2Text());
            }
            schiessen2.entmarkieren();
        }
        if(schiessen3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setSchiessen3(e.getKeyCode());
                schiessen3.setText(Steuerung.getSchiessen3Text());
            }
            schiessen3.entmarkieren();
        }
        if(schiessen4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setSchiessen4(e.getKeyCode());
                schiessen4.setText(Steuerung.getSchiessen4Text());
            }
            schiessen4.entmarkieren();
        }
        if(ducken1.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setDucken1(e.getKeyCode());
                ducken1.setText(Steuerung.getDucken1Text());
            }
            ducken1.entmarkieren();
        }
        if(ducken2.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setDucken2(e.getKeyCode());
                ducken2.setText(Steuerung.getDucken2Text());
            }
            ducken2.entmarkieren();
        }
        if(ducken3.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setDucken3(e.getKeyCode());
                ducken3.setText(Steuerung.getDucken3Text());
            }
            ducken3.entmarkieren();
        }
        if(ducken4.istMarkiert()){
            if(!istTasteBelegt(e.getKeyCode())){
                Steuerung.setDucken4(e.getKeyCode());
                ducken4.setText(Steuerung.getDucken4Text());
            }
            ducken4.entmarkieren();
        }
    }

    public static void einstellungenSpeichern(){
        try{
            FileOutputStream output=new FileOutputStream("einstellungen.opt");
            DataOutputStream dataout=new DataOutputStream(output);           
            try{
                dataout.writeInt(Steuerung.getOben1());
                dataout.writeInt(Steuerung.getOben2());
                dataout.writeInt(Steuerung.getOben3());
                dataout.writeInt(Steuerung.getOben4());
                dataout.writeInt(Steuerung.getLinks1());
                dataout.writeInt(Steuerung.getLinks2());
                dataout.writeInt(Steuerung.getLinks3());
                dataout.writeInt(Steuerung.getLinks4());
                dataout.writeInt(Steuerung.getRechts1());
                dataout.writeInt(Steuerung.getRechts2());
                dataout.writeInt(Steuerung.getRechts3());
                dataout.writeInt(Steuerung.getRechts4());
                dataout.writeInt(Steuerung.getUnten1());
                dataout.writeInt(Steuerung.getUnten2());
                dataout.writeInt(Steuerung.getUnten3());
                dataout.writeInt(Steuerung.getUnten4());
                dataout.writeInt(Steuerung.getSchiessen1());
                dataout.writeInt(Steuerung.getSchiessen2());
                dataout.writeInt(Steuerung.getSchiessen3());
                dataout.writeInt(Steuerung.getSchiessen4());
                dataout.writeInt(Steuerung.getDucken1());
                dataout.writeInt(Steuerung.getDucken2());
                dataout.writeInt(Steuerung.getDucken3());
                dataout.writeInt(Steuerung.getDucken4());

                dataout.writeInt(fenstergroesseX);
                dataout.writeInt(fenstergroesseY);
                dataout.writeBoolean(fullscreenAn);
                dataout.writeBoolean(musikIstAn);
                dataout.writeBoolean(soundIstAn);

            }catch(IOException e){e.printStackTrace();}
            try{
                output.close();
            }catch(IOException e1){}
        }catch(IOException e){e.printStackTrace();}
    }

    public static void einstellungenLaden(){
        try{
            FileInputStream iostream=new FileInputStream("einstellungen.opt");
            DataInputStream diostream=new DataInputStream(iostream);
            try{
                Steuerung.setOben1(diostream.readInt());
                Steuerung.setOben2(diostream.readInt());
                Steuerung.setOben3(diostream.readInt());
                Steuerung.setOben4(diostream.readInt());
                Steuerung.setLinks1(diostream.readInt());
                Steuerung.setLinks2(diostream.readInt());
                Steuerung.setLinks3(diostream.readInt());
                Steuerung.setLinks4(diostream.readInt());
                Steuerung.setRechts1(diostream.readInt());
                Steuerung.setRechts2(diostream.readInt());
                Steuerung.setRechts3(diostream.readInt());
                Steuerung.setRechts4(diostream.readInt());
                Steuerung.setUnten1(diostream.readInt());
                Steuerung.setUnten2(diostream.readInt());
                Steuerung.setUnten3(diostream.readInt());
                Steuerung.setUnten4(diostream.readInt());
                Steuerung.setSchiessen1(diostream.readInt());
                Steuerung.setSchiessen2(diostream.readInt());
                Steuerung.setSchiessen3(diostream.readInt());
                Steuerung.setSchiessen4(diostream.readInt());
                Steuerung.setDucken1(diostream.readInt());
                Steuerung.setDucken2(diostream.readInt());
                Steuerung.setDucken3(diostream.readInt());
                Steuerung.setDucken4(diostream.readInt());

                fenstergroesseX = diostream.readInt();//fenstergroesseX;
                fenstergroesseY = diostream.readInt();//fenstergroesseY
                container.setFensterX(fenstergroesseX);
                container.setFensterY(fenstergroesseY);

                fullscreenAn = diostream.readBoolean();//fullscreenAn

                if(fullscreenAn){
                   container.fullscreenAn();
                }

                musikIstAn = diostream.readBoolean();//musikIstAn
                soundIstAn = diostream.readBoolean();//soundIstAn

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e){}
    }

    public static boolean soundAnAus(){
        return soundIstAn;
    }

    public static boolean musikAnAus(){
        return musikIstAn;
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

}