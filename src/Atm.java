import java.util.HashMap;

public class Atm {
    // nu am folosit date time, am inpartit ora in 2 int-uri, ore si minute
    public Atm(int id, int startToIt, int openTimeH, int openTimeM, int closeTimeH, int closeTimeM ) {
        this.id = id;
        this.startToIt = startToIt;
        this.openTimeH = openTimeH;
        this.openTimeM = openTimeM;
        this.closeTimeH = closeTimeH;
        this.closeTimeM = closeTimeM;
    }

    private int id;
    private int startToIt;
    private  int openTimeH;
    private  int openTimeM;
    private  int closeTimeH;
    private  int closeTimeM;
    private HashMap<Integer,Integer> durataAtm= new HashMap<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartToIt() {
        return startToIt;
    }

    public void setStartToIt(int startToIt) {
        this.startToIt = startToIt;
    }

    public int getOpenTimeH() {
        return openTimeH;
    }

    public void setOpenTimeH(int openTimeH) {
        this.openTimeH = openTimeH;
    }

    public int getOpenTimeM() {
        return openTimeM;
    }

    public void setOpenTimeM(int openTimeM) {
        this.openTimeM = openTimeM;
    }

    public int getCloseTimeH() {
        return closeTimeH;
    }

    public void setCloseTimeH(int closeTimeH) {
        this.closeTimeH = closeTimeH;
    }

    public int getCloseTimeM() {
        return closeTimeM;
    }

    public void setCloseTimeM(int closeTimeM) {
        this.closeTimeM = closeTimeM;
    }

    public HashMap<Integer , Integer> getDurataAtm() {
        return durataAtm;
    }

    public void setDurataAtm(HashMap<Integer, Integer> durataAtm) {
        this.durataAtm = durataAtm;
    }
}
