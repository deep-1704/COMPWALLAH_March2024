package Entities;

public class CashCard {
    private String Name;
    private int Amount;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "CashCard{" +
                "Name='" + Name + '\'' +
                ", Amount=" + Amount +
                '}';
    }

    public CashCard(String name, int amount) {
        Name = name;
        Amount = amount;
    }
}
