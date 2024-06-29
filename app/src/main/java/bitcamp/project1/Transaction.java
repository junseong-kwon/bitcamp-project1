package bitcamp.project1;

import java.time.LocalDate;

public class Transaction {
    private String type; // "수입" or "지출"
    private String description;
    private int amount; // 변경: double에서 int로
    private LocalDate date;

    public Transaction(String type, String description, int amount, LocalDate date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return type + ": " + description + " - " + amount + " (" + date + ")";
    }
}
