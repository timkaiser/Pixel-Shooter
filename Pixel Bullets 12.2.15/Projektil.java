import java.util.*;
import javax.swing.*;
import java.awt.*;

public abstract class Projektil extends Objekt {
    static ArrayList<Hindernis> Hindernisse = new ArrayList<Hindernis>();
    String richtung;
    protected double speedX,speedY;
    protected boolean ueberHindernissGeflogen = false;
    protected boolean tief = false;
    protected double reichweite = 10;
    protected double schaden;
    protected Spieler besitzer;
    protected String element = "";
    public Projektil(double x, double y, int breite, int hoehe, double speedX, double speedY, double reichweite,double schaden,Spieler spieler){
        super(x,y,breite,hoehe);
        this.speedX = speedX;
        this.speedY = speedY;
        this.reichweite = reichweite;
        this.schaden = schaden;
        this.besitzer = spieler;
        if(besitzer.isGeduckt()){
            tief=true;
        }
    }

    public void zeichnen(Graphics g, JPanel panel){
        switch(element){
            default:
            g.setColor(Color.RED);
            break;
            case "Tarnung":
            g.setColor(new Color(200,200,200,100));
            break;
            case "Feuer":
            g.setColor(new Color(255,100,50));
            break;
            case "Eis":
            g.setColor(new Color(0,200,200));
            break;
            case "Gift":
            g.setColor(new Color(150,200,50));
            break;
            case "Verfolgung":
            g.setColor(new Color(200,50,150));
            break;
            case "Explosiv":
            g.setColor(new Color(255,200,0));
            break;
        }
        g.fillRect((int)(x*genauigkeit),(int)(y*genauigkeit),(int)(breite*genauigkeit),(int)(hoehe*genauigkeit));
    }

    public void bewegungBerechnen(){

        if(element.equals("Verfolgung")){
            verfolgen();
        }

        x+=speedX;
        y+=speedY;

        reichweite -= Math.abs(speedX);
        reichweite -= Math.abs(speedY);

        if(reichweite<=0){
            Objektmanager.getProjektile().remove(this);  
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
        }
        if(kollidiert()){
            Objektmanager.getProjektile().remove(this);
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
        }
        Spieler sp = kollidiertMitSpieler();
        NPC npc = kollidiertMitNPC();
        if(sp != null){
            Objektmanager.getProjektile().remove(this);
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
            if(sp.getMomentanLeben()-schaden<=0){
                besitzer.killcounterErhoehen();
            }
            sp.kriegtSchaden(schaden);
        }
        if(npc != null){
            Objektmanager.getProjektile().remove(this);
            if(element.equals("Explosiv"))
                new Explosion(x,y,3,schaden/10,besitzer);
            if(npc.getMomentanLeben()-schaden<=0){
                besitzer.killcounterErhoehen();
            }
            npc.kriegtSchaden(schaden);
        }
    }

    protected void verfolgen(){
        Spieler sp = null;
        Spieler s = null;
        double entf = 8;
        for(int i=0; i<Objektmanager.getSpieler().size(); i++){
            s = Objektmanager.getSpieler().get(i);
            if(s!=besitzer){
                double tmp = Math.sqrt(Math.pow(x-s.getX(),2)+Math.pow(y-s.getY(),2));
                if(tmp<entf){
                    sp = s;
                    entf = tmp;
                }
            }
        }

        if(sp!=null){
            speedX += Math.signum(sp.getX()-x)*0.6;
            speedY += Math.signum(sp.getY()-y)*0.6;
            reichweite+=1;
        }
    }

    protected boolean kollidiert(){        
        ArrayList<Hindernis> hindernisse = Objektmanager.getHindernisse();
        boolean kollidiert = false;
        Rectangle r = rect;
        r.setBounds((int)(x),(int)(y),(int)breite,(int)hoehe);
        for(int i=0; i<hindernisse.size(); i++){
            if(hindernisse.get(i).kollision(rect,this)){
                kollidiert = true;
                break;
            }            
        }
        return kollidiert;
    }

    protected Spieler kollidiertMitSpieler(){
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        Spieler sp = null;
        Rectangle r = rect;
        r.setBounds((int)(x),(int)(y),(int)breite,(int)hoehe);
        for(int i = 0; i < spieler.size();i++){
            if(spieler.get(i) != besitzer && spieler.get(i).amLeben()){
                Rectangle spielerRect = new Rectangle((int)(spieler.get(i).getX()),(int)(spieler.get(i).getY()-3),(int)spieler.get(i).getBreite(),(int)spieler.get(i).getHoehe()+3);
                if(r.intersects(spielerRect)&& !(ueberHindernissGeflogen&&spieler.get(i).isGeduckt())){
                    sp = spieler.get(i);
                    break;
                }    
            }
        }
        return sp;
    }

    protected NPC kollidiertMitNPC(){
        ArrayList<NPC> npc = Objektmanager.getNPC();
        NPC sp = null;
        Rectangle r = rect;
        r.setBounds((int)(x),(int)(y),(int)breite,(int)hoehe);
        for(int i = 0; i < npc.size();i++){
            if(npc.get(i).amLeben()){
                Rectangle npcRect = new Rectangle((int)(npc.get(i).getX()),(int)(npc.get(i).getY()-3),(int)npc.get(i).getBreite(),(int)npc.get(i).getHoehe()+3);
                if(r.intersects(npcRect)&& !(ueberHindernissGeflogen)){
                    sp = npc.get(i);
                    break;
                }    
            }
        }
        return sp;
    }

    public void kollidiertMitNiedrigemHindernis(){
        ueberHindernissGeflogen=true;
    }

    public boolean isTief(){
        return tief;
    }
}
