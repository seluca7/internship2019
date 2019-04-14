import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Algo algo=new Algo();
        List<Atm> traseu  = new ArrayList<Atm>();

        traseu = algo.getAtmsRouts();
        for(int i=0; i<(traseu.size());i++){
            System.out.println(traseu.get(i).getId() + " Atm "+ traseu.get(i).getOpenTimeH() );
        }
    }
}
