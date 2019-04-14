import java.util.ArrayList;
import java.util.List;

public class Algo {

    public Service srv= new Service();
    int indexMi=0;
    public List<Atm> atms = new ArrayList<Atm>();
    int ore=0;
    int minute=0;
    Atm atmAux;

    // returneaza atmul din lista de atm-uri(atms) cu id-ul dat ca parametru, poate fi 1,2,3,4
    Atm ret (int id){
        for(int i=0;i<atms.size()-1;i++){
            if (atms.get(i).getId()==id)
                return atms.get(i);
        }
        return null;

    }

    //face diferenta intre 2 ore rezultatul fiind exprimat in minute, care pot fi pozitive sa nu, daca sunt negative inseamna ca atm-ul
    //se deschide mai tarziu decat timpul de start al clientului
    int diferentaOre(int ora1, int minut1, int ora2, int minut2){
        int ret=0;
        if (ora1<ora2)
                ret= -minut2-((ora2-ora1-1)*60)-(60-minut1);

        if (ora1==ora2)
                ret=minut1-minut2;

        return ret;
    }

    //aduna 2 ore
    void addOre(int ora1, int minut1, int ora2, int minut2){

         minute =minut1+minut2;
        if (minute >60){
            ora1++;
            minute=minute-60;
        }
        ore=ora1+ora2;
    }

    // face retragerea de 5000 de lei de la un atm, de pe unul sau mai multe carduri
    //consideram ca se cunosc comisionele cardului si de aceea verificam diponibilitatea de sold si valabilitatea
    //cardului patinum dupa care verificam cardul gold si ultima oara silver.
    void withdraw(){

        String anCurent= srv.getCl().getDataInceput().charAt(6)+srv.getCl().getDataInceput().charAt(7)+ ""+srv.getCl().getDataInceput().charAt(8) +srv.getCl().getDataInceput().charAt(9);
        String anAuxilizar;

        int anCr=Integer.parseInt(anCurent);
        int anCrAux;

        Boolean valid= true;

        // consideram ca se stie ca platinum are comision cel mai mic, urmat de gold is silver
        if (srv.getCl().getPlat().getAvAmount()>0){
            anAuxilizar = srv.getCl().getPlat().getExpDate().charAt(6) + srv.getCl().getPlat().getExpDate().charAt(7) +""+ srv.getCl().getPlat().getExpDate().charAt(8) + srv.getCl().getPlat().getExpDate().charAt(9);
            anCrAux = Integer.parseInt(anAuxilizar);
            if (anCr > anCrAux) { // card expirat
                valid =false;
            }else{
                int sumaRetras = srv.getCl().getSumaDeRetras();
                if (sumaRetras > srv.getCl().getPlat().getWithdrawLimit())// mai putin de retras decat necesar
                    if (srv.getCl().getPlat().getWithdrawLimit() > srv.getCl().getPlat().getAvAmount()) {
                        srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getPlat().getAvAmount());
                        srv.getCl().getPlat().setAvAmount(0);
                    } else {
                        srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getPlat().getWithdrawLimit());
                        srv.getCl().getPlat().setAvAmount(srv.getCl().getPlat().getAvAmount() - srv.getCl().getPlat().getWithdrawLimit());
                    }
            }
        }else{
            valid=false;
        }



        if(!valid) {

            valid=true;

            if (srv.getCl().getGold().getAvAmount() > 0) {
                anAuxilizar = srv.getCl().getGold().getExpDate().charAt(6) + srv.getCl().getGold().getExpDate().charAt(7) + ""+srv.getCl().getGold().getExpDate().charAt(8) + srv.getCl().getGold().getExpDate().charAt(9);
                anCrAux = Integer.parseInt(anAuxilizar);
                if (anCr > anCrAux) { // card expirat
                    valid = false;
                } else {
                    int sumaRetras = srv.getCl().getSumaDeRetras();
                    if (sumaRetras > srv.getCl().getGold().getWithdrawLimit())// mai putin de retras decat necesar
                        if (srv.getCl().getGold().getWithdrawLimit() > srv.getCl().getGold().getAvAmount()) {
                            srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getGold().getAvAmount());
                            srv.getCl().getGold().setAvAmount(0);
                        } else {
                            srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getGold().getWithdrawLimit());
                            srv.getCl().getGold().setAvAmount(srv.getCl().getGold().getAvAmount() - srv.getCl().getGold().getWithdrawLimit());
                        }
                }
            } else {
                valid = false;
            }
        }

        if(!valid) {

            valid=true;

            if (srv.getCl().getSil().getAvAmount() > 0) {
                anAuxilizar = srv.getCl().getSil().getExpDate().charAt(6) + srv.getCl().getSil().getExpDate().charAt(7) + ""+srv.getCl().getSil().getExpDate().charAt(8) + srv.getCl().getSil().getExpDate().charAt(9);
                anCrAux = Integer.parseInt(anAuxilizar);
                if (anCr > anCrAux) { // card expirat
                    valid = false;
                } else {
                    int sumaRetras = srv.getCl().getSumaDeRetras();
                    if (sumaRetras > srv.getCl().getSil().getWithdrawLimit())// mai putin de retras decat necesar
                        if (srv.getCl().getSil().getWithdrawLimit() > srv.getCl().getSil().getAvAmount()) {
                            srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getSil().getAvAmount());
                            srv.getCl().getSil().setAvAmount(0);
                        } else {
                            srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getSil().getWithdrawLimit());
                            srv.getCl().getSil().setAvAmount(srv.getCl().getSil().getAvAmount() - srv.getCl().getSil().getWithdrawLimit());
                        }
                }
            } else {
                valid = false;
            }
        }

    }

    //returneaza durata minima a atm-ului pentru a ajunge din punctul de start
    int minDuration (){
        int min=1000;

        for(int i=0;i<atms.size()-1;i++){
            if(atms.get(i).getStartToIt()< min){
                min=atms.get(i).getStartToIt();
                indexMi=atms.get(i).getId();
            }
        }
        return min;
    }

    // returneaza lista de atm-uri in ordinea prin care trece clientul pentru a retrage banii
    // clientul se orienteaza in functie de durata minima necesara pentru a ajunge la un ATM var verifica si daca este deschis sau nu
    //sau cat trebuie sa astepte pentru a se dechide si daca este convenabil sau nu
    public List<Atm> getAtmsRouts(){

        List<Atm> traseu  = new ArrayList<Atm>();


        atms.add(srv.getAtm1());
        atms.add(srv.getAtm2());
        atms.add(srv.getAtm3());
        atms.add(srv.getAtm4());

        int durtaMin1= minDuration(); //5 min

        int aux = diferentaOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),ret(indexMi).getOpenTimeH(),ret(indexMi).getOpenTimeM()); //-30

        if(aux >= 0 ){ // timpul pana la deschiderea atm-ului daca e pozitiv clientul nu trebuie sa astepte
            atmAux=ret(indexMi);
            traseu.add(ret(indexMi));
            atms.remove(indexMi);

            addOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),ret(indexMi).getOpenTimeH(),ret(indexMi).getOpenTimeM());
            srv.getCl().setOraIncepH(ore);
            srv.getCl().setOraIncepM(minute);
            ore=0;
            minute=0;
            srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-3000);

        }else{
            atmAux=ret(indexMi); // atm1
            ////////////////////////////////
            for(int j=0;j<atms.size()-1;j++){
                if(atms.get(j).getId()== atmAux.getId()) {
                   // System.out.println(j);
                    atms.remove(j);
                    break;
                }
            }
           // for(int j=0;j<atms.size();j++){
               // System.out.println(atms.get(j).getId());
          //  }
            //atms.remove(indexMi);
            if(minDuration()<(-aux)) {// se verifica daca timpul de asteptare este mai mic decat la celelate bancomate chiar daca clientul trebuie sa astepte pana se deschide

                traseu.add(ret(indexMi));// se pune cel mai mic daca exista
                //atms.remove(indexMi);
                addOre(srv.getCl().getOraIncepH(), srv.getCl().getOraIncepM(), ret(indexMi).getOpenTimeH(), ret(indexMi).getOpenTimeM());
                srv.getCl().setOraIncepH(ore);
                srv.getCl().setOraIncepM(minute);
                srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-3000);
            }else{
                traseu.add(atmAux);
                addOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),atmAux.getOpenTimeH(),atmAux.getOpenTimeM());
                srv.getCl().setOraIncepH(ore);
                srv.getCl().setOraIncepM(minute);
                srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-3000);
            }

        }


        while(srv.getCl().getSumaDeRetras() > 0){

            //int mini =60;

            //returneaza atmul cel mai apropiat fata de atm-ul la care este clientul in momentul de fata
           // System.out.println(atmAux.getId() + " scos");

            switch (atmAux.getId()){
            case 1:
                if(srv.getAtm1().getDurataAtm().get(2)<=srv.getAtm1().getDurataAtm().get(3)) {
                if (srv.getAtm1().getDurataAtm().get(2) <= srv.getAtm1().getDurataAtm().get(4))
                    atmAux = srv.getAtm2();
                else
                    atmAux = srv.getAtm4();
            }else{
                atmAux=srv.getAtm3();
            }
            break;

            case 2: if(srv.getAtm2().getDurataAtm().get(1)<srv.getAtm2().getDurataAtm().get(3)) {
                if (srv.getAtm2().getDurataAtm().get(1) < srv.getAtm2().getDurataAtm().get(4))
                    atmAux = srv.getAtm1();
                else
                    atmAux = srv.getAtm4();
            }else{
                atmAux=srv.getAtm3();
            }
                break;
                    //if(srv.getAtm3().getDurataAtm().get(1)<=srv.getAtm3().getDurataAtm().get(2))
            case 3:   if(srv.getAtm3().getDurataAtm().get(1)<=srv.getAtm3().getDurataAtm().get(2)) {
                if (srv.getAtm3().getDurataAtm().get(1) <= srv.getAtm3().getDurataAtm().get(4))
                    atmAux = srv.getAtm1();
                else
                    atmAux = srv.getAtm4();
            }else{
                atmAux=srv.getAtm2();
            }
                break;


            case 4: if(srv.getAtm4().getDurataAtm().get(1)<srv.getAtm4().getDurataAtm().get(2)) {
                if (srv.getAtm4().getDurataAtm().get(1) < srv.getAtm4().getDurataAtm().get(3))
                    atmAux = srv.getAtm1();
                else
                    atmAux = srv.getAtm3();
            }else{
                atmAux=srv.getAtm2();
            }
                break;
            default:
                break;

            }
           // System.out.println(atmAux.getId());

            aux = diferentaOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),atmAux.getOpenTimeH(),atmAux.getOpenTimeM());

            if(aux >= 0 ){ // timpul pana la deschiderea atm-ului
               // atmAux=ret(indexMi);
                //traseu.add(ret(indexMi));
               // System.out.println("sunt aici 2");
                traseu.add(atmAux);
               // System.out.println(traseu.get(1).getId());
                        for(int j=0;j<atms.size()-1;j++){
                            if(atms.get(j).getId()== atmAux.getId()) {
                                atms.remove(j);
                                break;
                            }
                        }
                //atms.remove(atmAux.getId());
                addOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),atmAux.getOpenTimeH(),atmAux.getOpenTimeM());
                srv.getCl().setOraIncepH(ore);
                srv.getCl().setOraIncepM(minute);
                srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-4500);

            }else{
                atmAux=ret(indexMi);
                atms.remove(indexMi);
                if(minDuration()<=durtaMin1) {

                    traseu.add(ret(indexMi));
                    atms.remove(indexMi);
                    addOre(srv.getCl().getOraIncepH(), srv.getCl().getOraIncepM(), ret(indexMi).getOpenTimeH(), ret(indexMi).getOpenTimeM());
                    srv.getCl().setOraIncepH(ore);
                    srv.getCl().setOraIncepM(minute);
                    srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-3000);
                }else{
                    traseu.add(atmAux);
                    addOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),atmAux.getOpenTimeH(),atmAux.getOpenTimeM());
                    srv.getCl().setOraIncepH(ore);
                    srv.getCl().setOraIncepM(minute);
                    srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras()-4500);
                }

            }

        }
        return  traseu;
    }

}
