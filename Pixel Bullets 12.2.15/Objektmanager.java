import java.util.*;

public class Objektmanager{
    private static Objektmanager objektmanager;

    private static ArrayList<Hindernis> hindernisse;
    private static Hintergrund hintergrund;
    private static ArrayList<Spieler> spieler;
    private static ArrayList<Projektil> projektile;
    private static ArrayList<Respawnpunkt> respawnpunkte;
    private static ArrayList<Gegnerspawnpunkt> gegnerspawnpunkte;
    private static ArrayList<NPC> npc;
    protected static Spielmodus modus;

    protected int breite, hoehe;

    private Objektmanager(){
        objektmanager = this;

        hindernisse = new ArrayList<Hindernis>();
        respawnpunkte = new ArrayList<Respawnpunkt>();
        gegnerspawnpunkte = new ArrayList<Gegnerspawnpunkt>();
        breite = 100;
        hoehe = 100;

        spieler = new ArrayList<Spieler>();
        projektile = new ArrayList<Projektil>();
        npc = new ArrayList<NPC>();
    } 

    public static void setMap(String map){        
        LevelReader level = new LevelReader(map);
        get().hindernisse = level.getHindernisse();
        get().respawnpunkte = level.getRespawnpunkte();
        get().gegnerspawnpunkte = level.getGegnerspawnpunkte();
        get().breite = level.getBreite();
        get().hoehe = level.getHoehe();
    }

    public static Objektmanager get() {
        if (objektmanager == null) {
            objektmanager = new Objektmanager ();
        }
        return objektmanager;
    }

    private void addP(Hindernis hindernis){
        hindernisse.add(hindernis);
    }

    public static void add(Hindernis hindernis){
        get().addP(hindernis);
    }

    public static ArrayList<Hindernis> getHindernisse(){
        return get().hindernisse;
    }

    public Spielmodus getSpielmodus(){
        return get().modus;
    }

    public void setSpielmodus(Spielmodus m){
        get().modus = m;
    }

    private void addP(Spieler spieler){
        this.spieler.add(spieler);
    }

    public static void add(Spieler spieler){
        get().addP(spieler);
    }

    private void addP(NPC npc){
        this.npc.add(npc);
    }

    public static void add(NPC npc){
        get().addP(npc);
    }

    public static ArrayList<Spieler> getSpieler(){
        return get().spieler;
    }

    public static Spieler getSpieler(int i){
        return get().spieler.get(i);
    }

    public static ArrayList<NPC> getNPC(){
        return get().npc;
    }

    public static NPC getNPC(int i){
        return get().npc.get(i);
    }

    private void addP(Projektil projektil){
        projektile.add(projektil);
    }

    public static void add(Projektil projektil){
        get().addP(projektil);
    }

    public static ArrayList<Projektil> getProjektile(){
        return get().projektile;
    }

    public static Projektil getProjektil(int i){
        return get().projektile.get(i);
    }

    public static int getLevelbreite(){
        return get().breite;
    }

    public static int getLevelhoehe(){
        return get().hoehe;
    }

    public static ArrayList<Respawnpunkt> getRespawnpunkte(){
        return get().respawnpunkte;
    }

    public static ArrayList<Gegnerspawnpunkt> getGegnerspawnpunkte(){
        return get().gegnerspawnpunkte;
    }

    public static Respawnpunkt getRandomRespawnpunkt(){
        ArrayList<Respawnpunkt> respawnpunkte = getRespawnpunkte();
        int tmp = (int)(Math.random()*respawnpunkte.size());
        if(tmp<respawnpunkte.size()){
            return respawnpunkte.get(tmp);
        }else{
            return respawnpunkte.get(0);
        }
    }

    public static void reset(){
        objektmanager=null;
        hindernisse=null;
        spieler=null;
        projektile=null;
        respawnpunkte=null;
        Wegfindung.Knoten.knoten=null;
    }

}
