import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics;

public class Spielschleife implements Runnable{
    public static boolean laeuft=true;
    public static boolean loopBeendet=false;

    private Spielmodus modus;
    private Spielfeld spielfeld;
    private Steuerung steuerung;
    static Thread gameloop;
    public Spielschleife(Spielfeld feld,Spielmodus modus){
        spielfeld = feld;
        this.modus = modus;
        steuerung = new Steuerung(this);
        feld.addKeyListener(steuerung);

        gameloop = new Thread (this);
        gameloop.start();
    }

    public void run(){
        Date schlafzeit=new Date();
        while(loopBeendet==false){
            if(laeuft){
                steuerung.indirektesInputUeberpruefung();
                for(int i=0;i<Objektmanager.getSpieler().size();i++){
                    if(Objektmanager.getSpieler(i).amLeben()){
                        Objektmanager.getSpieler(i).berechnen();
                    }
                }
                for(int i=0;i<Objektmanager.getProjektile().size();i++){
                    Objektmanager.getProjektil(i).bewegungBerechnen();
                }
                for(int i=0;i<Objektmanager.getHindernisse().size();i++){
                    Objektmanager.getHindernisse().get(i).berechnen();
                }
                
                modus.schleife();
            }
            if(Fenster.getPauseAn()==false){
                spielfeld.repaint();
            }

            try {
                long t=(schlafzeit.getTime()+   25   )-(new Date()).getTime();

                if(t>0){ Thread.sleep(25); };
                schlafzeit=new Date();
            } catch (InterruptedException e) {}
        }
    }

    public static void spielPausieren(){
        if(laeuft==true){
            pausieren();
        }
        else{
            fortsetzen();
        }
    }

    public static boolean getLaeuft(){
        return laeuft;
    }

    public static void pausieren(){
        laeuft =false;
        Animation.pausiert=true;
    }

    public static void fortsetzen(){
        laeuft = true;
        Animation.pausiert=false;
    }

    public static void loopBeenden(){
        loopBeendet=true;
    }

    public static void loopBeginnen(){
        loopBeendet=false;
    }

    public Spielmodus getSpielmodus(){
        return modus;
    }
    
}