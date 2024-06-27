package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Transaction;
import java.util.Date;

public class FinanceCommand {
  LinkedList transactionList = new LinkedList();

  public void executeFinanceCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addTransaction();
        break;
      case "목록":
        this.listTransactions();
        break;
      case "조회":
        this.viewTransaction();
        break;
      case "변경":
        this.updateTransaction();
        break;
      case "삭제":
        this.deleteTransaction();
        break;
    }
  }

  private void addTransaction() {
    Transaction transaction = new Transaction();
    transaction.setDate(new Date());
    transaction.setType(Prompt.input("유형(수입/지출)?"));
    transaction.setCategory(Prompt.input("카테고리?"));
    transaction.setAmount(Prompt.inputInt("금액?"));
    transaction.setDescription(Prompt.input("설명?"));
    transaction.setNo(Transaction.getNextSeqNo());
    transactionList.add(transaction);
  }

  private void listTransactions() {
    System.out.println("번호 유형 카테고리 금액 설명");
    for (Object obj : transactionList.toArray()) {
      Transaction transaction = (Transaction) obj;
      System.out.printf("%d %s %s %d %s\n",
          transaction.getNo(), transaction.getType(), transaction.getCategory(),
          transaction.getAmount(), transaction.getDescription());
    }
  }

  private void viewTransaction() {
    int transactionNo = Prompt.inputInt("거래 번호?");
    Transaction transaction = (Transaction) transactionList.get(transactionList.indexOf(new Transaction(transactionNo)));
    if (transaction == null) {
      System.out.println("해당 번호의 거래가 없습니다.");
      return;
    }
    System.out.printf("유형: %s\n", transaction.getType());
    System.out.printf("카테고리: %s\n", transaction.getCategory());
    System.out.printf("금액: %d\n", transaction.getAmount());
    System.out.printf("설명: %s\n", transaction.getDescription());
    System.out.printf("일자: %1$tY-%1$tm-%1$td\n", transaction.getDate());
  }

  private void updateTransaction() {
    int transactionNo = Prompt.inputInt("거래 번호?");
    Transaction transaction = (Transaction) transactionList.get(transactionList.indexOf(new Transaction(transactionNo)));
    if (transaction == null) {
      System.out.println("해당 번호의 거래가 없습니다.");
      return;
    }
    transaction.setType(Prompt.input("유형(%s)?", transaction.getType()));
    transaction.setCategory(Prompt.input("카테고리(%s)?", transaction.getCategory()));
    transaction.setAmount(Prompt.inputInt("금액(%d)?", transaction.getAmount()));
    transaction.setDescription(Prompt.input("설명(%s)?", transaction.getDescription()));
    System.out.println("변경했습니다.");
  }

  private void deleteTransaction() {
    int transactionNo = Prompt.inputInt("거래 번호?");
    Transaction deletedTransaction = (Transaction) transactionList.get(transactionList.indexOf(new Transaction(transactionNo)));
    if (deletedTransaction != null) {
      transactionList.remove(transactionList.indexOf(deletedTransaction));
      System.out.println("삭제했습니다.");
    } else {
      System.out.println("해당 번호의 거래가 없습니다.");
    }
  }
}