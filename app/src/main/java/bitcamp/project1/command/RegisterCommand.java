package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.AmountParser;
import bitcamp.project1.util.ExceptionHandler;
import java.util.ArrayList;

public class RegisterCommand {
  static ArrayList<Expense> expenseList = FinanceCommand.expenseList;
  static ArrayList<Income> incomeList = FinanceCommand.incomeList;

  public static void menu() {
    while (true) {
      try {
        System.out.println("[등록 메뉴]");
        System.out.println("1. 지출 등록");
        System.out.println("2. 수입 등록");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            addExpense();
            break;
          case 2:
            addIncome();
            break;
          case 9:
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        ExceptionHandler.handle(e);
      }
    }
  }

  public static void addExpense() {
    try {
      Expense expense = new Expense();
      expense.id = Prompt.inputString("ID? ");
      expense.amount = AmountParser.parseAmount(Prompt.inputString("금액? "));
      expense.date = System.currentTimeMillis();
      expense.category = Prompt.inputString("카테고리? ");
      expenseList.add(expense);
      System.out.println("지출 내역을 등록했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void addIncome() {
    try {
      Income income = new Income();
      income.id = Prompt.inputString("ID? ");
      income.amount = AmountParser.parseAmount(Prompt.inputString("금액? "));
      income.date = System.currentTimeMillis();
      income.source = Prompt.inputString("출처? ");
      incomeList.add(income);
      System.out.println("수입 내역을 등록했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
