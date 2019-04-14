public class Client {

    String dataInceput;
    int oraIncepH;
    int oraIncepM;
    int sumaDeRetras;
    Card sil= new Card("SILVER",0.2,4500,"23.05.2020",20000);
    Card gold= new Card("GOLD",0.1,3000,"15.08.2018",25000);
    Card plat= new Card("PLATINUM",0.0,4000,"20.03.2019",3000);

    public Client(String dataInceput, int oraIncepH, int oraIncepM,int sumaDeRetras) {
        this.dataInceput = dataInceput;
        this.oraIncepH = oraIncepH;
        this.oraIncepM = oraIncepM;
        this.sumaDeRetras = sumaDeRetras;
    }

    public String getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(String dataInceput) {
        this.dataInceput = dataInceput;
    }

    public int getOraIncepH() {
        return oraIncepH;
    }

    public void setOraIncepH(int oraIncepH) {
        this.oraIncepH = oraIncepH;
    }

    public int getOraIncepM() {
        return oraIncepM;
    }

    public void setOraIncepM(int oraIncepM) {
        this.oraIncepM = oraIncepM;
    }

    public int getSumaDeRetras() {
        return sumaDeRetras;
    }

    public void setSumaDeRetras(int sumaDeRetras) {
        this.sumaDeRetras = sumaDeRetras;
    }

    public Card getSil() {
        return sil;
    }

    public void setSil(Card sil) {
        this.sil = sil;
    }

    public Card getGold() {
        return gold;
    }

    public void setGold(Card gold) {
        this.gold = gold;
    }

    public Card getPlat() {
        return plat;
    }

    public void setPlat(Card plat) {
        this.plat = plat;
    }
}
