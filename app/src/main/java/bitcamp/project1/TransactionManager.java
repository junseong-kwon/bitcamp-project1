package bitcamp.project1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    protected int balance; // 변경: double에서 int로
    protected ArrayList<Transaction> transactions;

    public TransactionManager() {
        balance = 0;
        transactions = new ArrayList<>();
    }

    public void addTransaction(String type, String description, int amount, LocalDate date) {
        transactions.add(new Transaction(type, description, amount, date));
        if (type.equals("수입")) {
            balance += amount;
        } else if (type.equals("지출")) {
            balance -= amount;
        }
    }

    public void updateTransaction(int index, String description, int amount) {
        if (index < 0 || index >= transactions.size()) {
            throw new IndexOutOfBoundsException("잘못된 번호입니다.");
        }
        Transaction transaction = transactions.get(index);
        int oldAmount = transaction.getAmount();
        transaction = new Transaction(transaction.getType(), description, amount, transaction.getDate());
        transactions.set(index, transaction);
        if (transaction.getType().equals("수입")) {
            balance = balance - oldAmount + amount;
        } else if (transaction.getType().equals("지출")) {
            balance = balance + oldAmount - amount;
        }
    }

    public void deleteTransaction(int index) {
        if (index < 0 || index >= transactions.size()) {
            throw new IndexOutOfBoundsException("잘못된 번호입니다.");
        }
        Transaction transaction = transactions.remove(index);
        if (transaction.getType().equals("수입")) {
            balance -= transaction.getAmount();
        } else if (transaction.getType().equals("지출")) {
            balance += transaction.getAmount();
        }
    }

    public int getBalance() {
        return balance;
    }

    public void showTransactions() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    public List<Transaction> getTransactionsByMonth(int year, int month) {
        return transactions.stream()
                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }
}
