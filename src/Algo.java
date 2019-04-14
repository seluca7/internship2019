import java.util.ArrayList;
import java.util.List;

public class Algo {

    public Service srv= new Service();
    int indexMi=0;
    public List<Atm> atms = new ArrayList<Atm>();
    int ore=0;
    int minute=0;
    Atm atmAux;


    Atm ret (int index){
        for(int i=0;i<atms.size()-1;i++){
            if (atms.get(i).getId()==index)
                return atms.get(i);
        }
        return null;

    }

    int diferentaOre(int ora1, int minut1, int ora2, int minut2){
        int ret=0;
        if (ora1<ora2)
                ret= -minut2-((ora2-ora1-1)*60)-(60-minut1);

        if (ora1==ora2)
                ret=minut1-minut2;

        return ret;
    }

    void addOre(int ora1, int minut1, int ora2, int minut2){

         minute =minut1+minut2;
        if (minute >60){
            ora1++;
            minute=minute-60;
        }
        ore=ora1+ora2;
    }
/*
    void withdraw(){

        String anCurent= srv.getCl().getDataInceput().charAt(6)+srv.getCl().getDataInceput().charAt(7)+srv.getCl().getDataInceput().charAt(8)+srv.getCl().getDataInceput().charAt(9);
        String anAuxilizar;

        int anCr=Integer.parseInt(anCurent);
        int anCrAux;


        if(srv.getCl().getSil().getFee()<srv.getCl().getGold().getFee())
            if(srv.getCl().getSil().getFee()<srv.getCl().getPlat().getFee())
                // consideram ca se stie ca platinum are comision cel mai mic, urmat de gold is silver

                anAuxilizar=srv.getCl().getPlat().getExpDate().charAt(6)+srv.getCl().getPlat().getExpDate().charAt(7)+srv.getCl().getPlat().getExpDate().charAt(8)+srv.getCl().getPlat().getExpDate().charAt(9);
                anCrAux=Integer.parseInt(anAuxilizar);
                if(anCr>anCrAux) { // card expirat
                    anAuxilizar = srv.getCl().getGold().getExpDate().charAt(6) + srv.getCl().getGold().getExpDate().charAt(7) + srv.getCl().getGold().getExpDate().charAt(8) + srv.getCl().getGold().getExpDate().charAt(9);
                    anCrAux=Integer.parseInt(anAuxilizar);

                    if(anCr>anCrAux){
                        anAuxilizar = srv.getCl().getSil().getExpDate().charAt(6) + srv.getCl().getSil().getExpDate().charAt(7) + srv.getCl().getSil().getExpDate().charAt(8) + srv.getCl().getSil().getExpDate().charAt(9);
                        anCrAux=Integer.parseInt(anAuxilizar);
                        System.out.println("toate cardurile sunt expirate");
                    }else{
                        int sumaRetras= srv.getCl().getSumaDeRetras();
                        if(sumaRetras>srv.getCl().getSil().getWithdrawLimit())// mai putin in cont decat limita
                            if(srv.getCl().getSil().getWithdrawLimit()>srv.getCl().getSil().getAvAmount()) {
                                srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getSil().getAvAmount());
                                srv.getCl().getSil().setAvAmount(0);
                            }else{
                                srv.getCl().setSumaDeRetras(srv.getCl().getSumaDeRetras() - srv.getCl().getSil().getWithdrawLimit());
                                srv.getCl().getSil().setAvAmount(srv.getCl().getSil().getAvAmount()-srv.getCl().getSil().getWithdrawLimit());
                            }
                    }
                }

    }
    */

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
                    System.out.println(j);
                    atms.remove(j);
                    break;
                }
            }
            for(int j=0;j<atms.size();j++){
                System.out.println(atms.get(j).getId());
            }
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
            System.out.println(atmAux.getId() + " scos");

            switch (atmAux.getId()){
            case 1: System.out.println("Sunt aici");
                System.out.println(srv.getAtm1().getDurataAtm().get(2));
                System.out.println(srv.getAtm1().getDurataAtm().get(3));
                System.out.println(srv.getAtm1().getDurataAtm().get(4));

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
            System.out.println(atmAux.getId());

            aux = diferentaOre(srv.getCl().getOraIncepH(),srv.getCl().getOraIncepM(),atmAux.getOpenTimeH(),atmAux.getOpenTimeM());

            if(aux >= 0 ){ // timpul pana la deschiderea atm-ului
               // atmAux=ret(indexMi);
                //traseu.add(ret(indexMi));
                System.out.println("sunt aici 2");
                traseu.add(atmAux);
                System.out.println(traseu.get(1).getId());
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
