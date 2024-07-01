package bitcamp.project1;

import java.time.LocalDate;
import java.util.List;

public class App {
  private TransactionManager transactionManager;
  private InputHandler inputHandler;


  public App() {
    transactionManager = new TransactionManager();
    inputHandler = new InputHandler();
  }

  public void run() {
    while (true) {
      printMenu();
      int choice = inputHandler.getIntInput("");

      switch (choice) {
        case 1:
          addIncome();
          break;
        case 2:
          addExpense();
          break;
        case 3:
          updateTransaction();
          break;
        case 4:
          deleteTransaction();
          break;
        case 5:
          showBalance();
          break;
        case 6:
          showTransactionsByMonth();
          break;
        case 0:
          exit();
          return;
        default:
          System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
      }
    }
  }

  private void addIncome() {
    int amount = inputHandler.getIntInput("수입 금액을 입력하세요: ");
    inputHandler.consumeNewLine(); // 추가된 부분
    String description = inputHandler.getStringInput("수입 설명을 입력하세요: ");
    LocalDate date = inputHandler.getDateInput("수입 날짜를 입력하세요(yyyy-MM-dd 또는 yyyyMMdd): ");
    transactionManager.addTransaction("수입", description, amount, date);
  }

  private void addExpense() {
    int amount = inputHandler.getIntInput("지출 금액을 입력하세요: ");
    inputHandler.consumeNewLine(); // 추가된 부분
    String description = inputHandler.getStringInput("지출 설명을 입력하세요: ");
    LocalDate date = inputHandler.getDateInput("지출 날짜를 입력하세요(yyyy-MM-dd 또는 yyyyMMdd): ");
    transactionManager.addTransaction("지출", description, amount, date);
  }

  private void updateTransaction() {
    transactionManager.showTransactions();
    int index = inputHandler.getIntInput("번호를 입력하세요: ") - 1; // 인덱스는 0부터 시작
    inputHandler.consumeNewLine(); // 추가된 부분
    String description = inputHandler.getStringInput("새로운 설명을 입력하세요: ");
    int amount = inputHandler.getIntInput("새로운 금액을 입력하세요: ");
    try {
      transactionManager.updateTransaction(index, description, amount);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
  }

  private void deleteTransaction() {
    transactionManager.showTransactions();
    int index = inputHandler.getIntInput("번호를 입력하세요: ") - 1; // 인덱스는 0부터 시작
    inputHandler.consumeNewLine(); // 추가된 부분
    try {
      transactionManager.deleteTransaction(index);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
  }

  private void showBalance() {
    System.out.println("현재 잔액: " + transactionManager.getBalance());
  }

  private void showTransactionsByMonth() {
    int year = inputHandler.getIntInput("연도를 입력하세요 (예: 2023): ");
    int month = inputHandler.getIntInput("월을 입력하세요 (1-12): ");
    List<Transaction> monthlyTransactions = transactionManager.getTransactionsByMonth(year, month);
    if (monthlyTransactions.isEmpty()) {
      System.out.println("해당 기간에 거래 내역이 없습니다.");
    } else {
      monthlyTransactions.forEach(System.out::println);
    }
  }

  private void exit() {
    System.out.println("프로그램을 종료합니다.");
    inputHandler.closeScanner();
  }
  public static void main(String[] args) {
    App app = new App();
    app.run();
  }
  public void printMenu() {
    System.out.println("1. 수입 추가 | 2. 지출 추가 | 3. 거래 수정");
    System.out.println("4. 거래 삭제 | 5. 잔액 확인 | 6. 거래 내역 확인");
    System.out.println("0. 종료");
    System.out.print("메뉴를 선택하세요: ");
  }
}
