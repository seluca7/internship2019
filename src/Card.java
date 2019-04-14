public class Card {

    private String name;
    private double fee;
    private int withdrawLimit;
    private String expDate;
    private int avAmount;

    public Card(String name, double fee, int withdrawLimit, String expDate, int avAmount) {
        this.name = name;
        this.fee = fee;
        this.withdrawLimit = withdrawLimit;
        this.expDate = expDate;
        this.avAmount = avAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(int withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getAvAmount() {
        return avAmount;
    }

    public void setAvAmount(int avAmount) {
        this.avAmount = avAmount;
    }
}
