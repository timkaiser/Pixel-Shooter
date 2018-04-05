import java.awt.*;
import javax.swing.*;

public abstract class Waffe{
    protected Spieler besitzer;
    protected boolean benutzt;
    protected int cooldown;
    protected int maxCooldown = 1000;
    protected int startCooldown, startSchaden;
    protected int magazin, magazinGroeße;
    protected double reichweite, startReichweite;
    protected int schaden;
    final int genauigkeit = Objekt.genauigkeit;

    public Waffe(Spieler besitzer){
        this.besitzer = besitzer;
        benutzt = false;
    }

    public void zeichnen(Graphics g, JPanel panel){
        g.setColor(Color.BLACK);
        if(benutzt){
            if(besitzer.getRichtung().equals("HOCH")){
                g.fillRect((int)(besitzer.getX()*genauigkeit),(int)((besitzer.getY()-2)*genauigkeit),1*genauigkeit,1*genauigkeit);
            }
            else if(besitzer.getRichtung().equals("RUNTER")){
                g.fillRect((int)(besitzer.getX()*genauigkeit),(int)(besitzer.getY()*genauigkeit),1*genauigkeit,2*genauigkeit);
            }
            else if(besitzer.getRichtung().equals("LINKS")){
                g.fillRect((int)((besitzer.getX()-1)*genauigkeit),(int)(besitzer.getY()*genauigkeit),2*genauigkeit,1*genauigkeit);
            }
            else if(besitzer.getRichtung().equals("RECHTS")){
                g.fillRect((int)((besitzer.getX()+3)*genauigkeit),(int)(besitzer.getY()*genauigkeit),2*genauigkeit,1*genauigkeit);
            }
        }
    }

    public String schadensUpgrade(){
        if(schaden*1.2 <= startSchaden*2)
        schaden *=1.2;
        return "Schadens-Upgrade";
    }

    public String schussgeschwindigkeitsUpgrade(){
        if(maxCooldown-maxCooldown*0.1 <= startCooldown/2)
        maxCooldown -=startCooldown*0.1;
        return "Schuss-Upgrade";
    }

    public String reichweiteUpgrade(){
        if(reichweite*1.2 <= startReichweite*2)
        reichweite *=1.2;
        return "Reichweiten-Upgrade";
    }

    public void aktivieren(){
        benutzt = true;
    }

    public void deaktivieren(){
        benutzt = false;
    }
    
    public void startwerteAnpassen(){
        startSchaden = schaden;
        startReichweite = reichweite;
        startCooldown = maxCooldown;
    }
    
    public void reset(){
    }

    public void cooldownVerringern(){
        cooldown-=25;
        if((cooldown < 0)){
            deaktivieren();
        }
    }

    public String getType(){
        return "";
    }

    abstract Waffe getKopie(Spieler s);

    public void setSpieler(Spieler sp){
        this.besitzer = sp;
    }

    public void setSchaden(int schaden){
        this.schaden = schaden;
    }

    public int getSchaden(){
        return schaden;
    }

    public void setReichweite(double reichweite){
        this.reichweite = reichweite;
    }

    public double getReichweite(){
        return reichweite;
    }

    public void setCooldown(int cooldown){
        maxCooldown = cooldown;
    }

    public int getCooldown(){
        return maxCooldown;
    }
}