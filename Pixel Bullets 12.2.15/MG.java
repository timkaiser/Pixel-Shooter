public class MG extends Waffe{
    public MG(Spieler besitzer){
        super(besitzer);
        maxCooldown = 100;
        reichweite = 35;
        schaden=10;
        startwerteAnpassen();
    }

    public static void playSound(){  
        if(Optionsmenue.soundAnAus()==true){
            Sound schusssound=new Sound("Schuss.wav");
            schusssound.start();
        }
    }
    
    public void aktivieren(){
        if(cooldown<=0){
            cooldown=maxCooldown;
            benutzt = true;
            if(besitzer.getRichtung().equals("HOCH")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),-2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RUNTER")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,0+besitzer.getXSpeed(),2+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("LINKS")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,-2+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            else if(besitzer.getRichtung().equals("RECHTS")){
                Objektmanager.add(new ProjektilNormal(besitzer.getX(),besitzer.getY(),1,1,2+besitzer.getXSpeed(),0+besitzer.getYSpeed(),reichweite,schaden,besitzer));
            }
            if(besitzer.getWaffenTyp()=="MG"){
                MG.playSound();
            }
            else if(besitzer.getWaffenTyp()=="Pistole"){
                Pistole.playSound();
            }
        }
    }

    public String getType(){
        return "MG";
    }
    
    public Waffe getKopie(Spieler s){
        return new MG(s);
    }
}