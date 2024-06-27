package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;

public class FinanceCommand {
  static ArrayList<Expense> expenseList = new ArrayList<>();
  static ArrayList<Income> incomeList = new ArrayList<>();

  public static void menu() {
    while (true) {
      try {
        System.out.println("[가계부 메뉴]");
        System.out.println("1. 지출 등록");
        System.out.println("2. 지출 목록");
        System.out.println("3. 지출 변경");
        System.out.println("4. 지출 삭제");
        System.out.println("5. 수입 등록");
        System.out.println("6. 수입 목록");
        System.out.println("7. 수입 변경");
        System.out.println("8. 수입 삭제");
        System.out.println("9. 이전 메뉴");
        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            addExpense();
            break;
          case 2:
            listExpenses();
            break;
          case 3:
            updateExpense();
            break;
          case 4:
            deleteExpense();
            break;
          case 5:
            addIncome();
            break;
          case 6:
            listIncomes();
            break;
          case 7:
            updateIncome();
            break;
          case 8:
            deleteIncome();
            break;
          case 9:
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        System.out.printf("오류 발생: %s\n", e.getMessage());
      }
    }
  }

  public static void addExpense() {
    try {
      Expense expense = new Expense();
      expense.id = Prompt.inputString("ID? ");
      expense.description = Prompt.inputString("설명? ");
      expense.amount = Prompt.inputInt("금액? ");
      expense.date = System.currentTimeMillis();
      expense.category = Prompt.inputString("카테고리? ");
      expenseList.add(expense);
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void listExpenses() {
    try {
      for (Expense expense : expenseList) {
        System.out.printf("%s, %s, %d, %s, %d\n",
            expense.id, expense.description, expense.amount, expense.category, expense.date);
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void updateExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = findExpenseById(id);
      if (expense == null) {
        System.out.println("해당 ID의 지출 내역이 없습니다.");
        return;
      }
      String description = Prompt.inputString(String.format("설명(%s)? ", expense.description));
      int amount = Prompt.inputInt(String.format("금액(%d)? ", expense.amount));
      String category = Prompt.inputString(String.format("카테고리(%s)? ", expense.category));

      expense.description = description.length() > 0 ? description : expense.description;
      expense.amount = amount > 0 ? amount : expense.amount;
      expense.category = category.length() > 0 ? category : expense.category;

      System.out.println("지출 내역을 변경했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void deleteExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = findExpenseById(id);
      if (expense == null) {
        System.out.println("해당 ID의 지출 내역이 없습니다.");
        return;
      }
      expenseList.remove(expense);
      System.out.println("지출 내역을 삭제했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void addIncome() {
    try {
      Income income = new Income();
      income.id = Prompt.inputString("ID? ");
      income.description = Prompt.inputString("설명? ");
      income.amount = Prompt.inputInt("금액? ");
      income.date = System.currentTimeMillis();
      income.source = Prompt.inputString("출처? ");
      incomeList.add(income);
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void listIncomes() {
    try {
      for (Income income : incomeList) {
        System.out.printf("%s, %s, %d, %s, %d\n",
            income.id, income.description, income.amount, income.source, income.date);
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void updateIncome() {
    try {
      String id = Prompt.inputString("ID? ");
      Income income = findIncomeById(id);
      if (income == null) {
        System.out.println("해당 ID의 수입 내역이 없습니다.");
        return;
      }
      String description = Prompt.inputString(String.format("설명(%s)? ", income.description));
      int amount = Prompt.inputInt(String.format("금액(%d)? ", income.amount));
      String source = Prompt.inputString(String.format("출처(%s)? ", income.source));

      income.description = description.length() > 0 ? description : income.description;
      income.amount = amount > 0 ? amount : income.amount;
      income.source = source.length() > 0 ? source : income.source;

      System.out.println("수입 내역을 변경했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void deleteIncome() {
    try {
      String id = Prompt.inputString("ID? ");
      Income income = findIncomeById(id);
      if (income == null) {
        System.out.println("해당 ID의 수입 내역이 없습니다.");
        return;
      }
      incomeList.remove(income);
      System.out.println("수입 내역을 삭제했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  private static Expense findExpenseById(String id) {
    try {
      for (Expense expense : expenseList) {
        if (expense.id.equals(id)) {
          return expense;
        }
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
    return null;
  }

  private static Income findIncomeById(String id) {
    try {
      for (Income income : incomeList) {
        if (income.id.equals(id)) {
          return income;
        }
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
    return null;
  }
}
