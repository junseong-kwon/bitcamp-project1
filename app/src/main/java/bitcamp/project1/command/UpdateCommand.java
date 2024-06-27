package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.AmountParser;
import bitcamp.project1.util.FinanceUtil;
import bitcamp.project1.util.ExceptionHandler;
import java.util.ArrayList;

public class UpdateCommand {
  static ArrayList<Expense> expenseList = FinanceCommand.expenseList;
  static ArrayList<Income> incomeList = FinanceCommand.incomeList;

  public static void menu() {
    while (true) {
      try {
        System.out.println("[변경 메뉴]");
        System.out.println("1. 지출 변경");
        System.out.println("2. 수입 변경");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            updateExpense();
            break;
          case 2:
            updateIncome();
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

  public static void updateExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = FinanceUtil.findExpenseById(expenseList, id);
      if (expense == null) {
        System.out.println("해당 ID의 지출 내역이 없습니다.");
        return;
      }
      int amount = AmountParser.parseAmount(Prompt.inputString(String.format("금액(%d원)? ", expense.amount)));
      String category = Prompt.inputString(String.format("카테고리(%s)? ", expense.category));

      expense.amount = amount > 0 ? amount : expense.amount;
      expense.category = category.length() > 0 ? category : expense.category;

      System.out.println("지출 내역을 변경했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void updateIncome() {
    try {
      String id = Prompt.inputString("ID? ");
      Income income = FinanceUtil.findIncomeById(incomeList, id);
      if (income == null) {
        System.out.println("해당 ID의 수입 내역이 없습니다.");
        return;
      }
      int amount = AmountParser.parseAmount(Prompt.inputString(String.format("금액(%d원)? ", income.amount)));
      String source = Prompt.inputString(String.format("출처(%s)? ", income.source));

      income.amount = amount > 0 ? amount : income.amount;
      income.source = source.length() > 0 ? source : income.source;

      System.out.println("수입 내역을 변경했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
