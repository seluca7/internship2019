import java.util.HashMap;
import java.util.List;

public class Service {

    public Service() {
        initAtms();
    }

    private HashMap<Integer,Integer> durataAtm1= new HashMap<>();
    private HashMap<Integer,Integer> durataAtm2= new HashMap<>();
    private HashMap<Integer,Integer> durataAtm3= new HashMap<>();
    private HashMap<Integer,Integer> durataAtm4= new HashMap<>();


    private Client cl = new Client("19.03.2019",11,30,7500);
    private Atm atm1 = new Atm(1,5,12,0,18,0);
    private Atm atm2 = new Atm(2,60,10,0,17,0);
    private Atm atm3 = new Atm(3,30,22,0,12,0);
    private Atm atm4 = new Atm(4,45,17,0,1,0);

    //initializare duratele dintre atm-uri, sunt valorile din tabel
    public void initAtms(){
        durataAtm1.put(2,40);
        durataAtm1.put(3,40);
        durataAtm1.put(4,45);

        durataAtm2.put(1,40);
        durataAtm2.put(3,15);
        durataAtm2.put(4,30);

        durataAtm3.put(1,40);
        durataAtm3.put(2,15);
        durataAtm4.put(4,15);

        durataAtm4.put(1,45);
        durataAtm4.put(2,30);
        durataAtm4.put(3,15);

        atm1.setDurataAtm(durataAtm1);
        atm2.setDurataAtm(durataAtm2);
        atm3.setDurataAtm(durataAtm3);
        atm4.setDurataAtm(durataAtm4);
    }

    public Client getCl() {
        return cl;
    }

    public void setCl(Client cl) {
        this.cl = cl;
    }

    public Atm getAtm1() {
        return atm1;
    }

    public void setAtm1(Atm atm1) {
        this.atm1 = atm1;
    }

    public Atm getAtm2() {
        return atm2;
    }

    public void setAtm2(Atm atm2) {
        this.atm2 = atm2;
    }

    public Atm getAtm3() {
        return atm3;
    }

    public void setAtm3(Atm atm3) {
        this.atm3 = atm3;
    }

    public Atm getAtm4() {
        return atm4;
    }

    public void setAtm4(Atm atm4) {
        this.atm4 = atm4;
    }
}
