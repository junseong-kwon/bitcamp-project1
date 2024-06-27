package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.User;
import bitcamp.project1.util.ExceptionHandler;

public class UserCommand {
  static ArrayList<User> userList = new ArrayList<>();

  public static void menu() {
    while (true) {
      try {
        System.out.println("[사용자 메뉴]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세보기");
        System.out.println("4. 변경");
        System.out.println("5. 삭제");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            add();
            break;
          case 2:
            list();
            break;
          case 3:
            detail();
            break;
          case 4:
            update();
            break;
          case 5:
            delete();
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

  public static void add() {
    try {
      User user = new User();
      user.no = Prompt.inputInt("번호? ");
      user.name = Prompt.inputString("이름? ");
      user.email = Prompt.inputString("이메일? ");
      user.password = Prompt.inputString("비밀번호? ");
      userList.add(user);
      System.out.println("사용자를 등록했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void list() {
    try {
      for (User user : userList) {
        System.out.printf("%d, %s, %s\n",
            user.no, user.name, user.email);
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void detail() {
    try {
      int no = Prompt.inputInt("번호? ");
      User user = findByNo(no);
      if (user == null) {
        System.out.println("해당 번호의 사용자가 없습니다.");
        return;
      }
      System.out.printf("이름: %s\n", user.name);
      System.out.printf("이메일: %s\n", user.email);
      System.out.printf("비밀번호: %s\n", user.password);
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void update() {
    try {
      int no = Prompt.inputInt("번호? ");
      User user = findByNo(no);
      if (user == null) {
        System.out.println("해당 번호의 사용자가 없습니다.");
        return;
      }
      String name = Prompt.inputString(String.format("이름(%s)? ", user.name));
      String email = Prompt.inputString(String.format("이메일(%s)? ", user.email));
      String password = Prompt.inputString(String.format("비밀번호(%s)? ", user.password));

      user.name = name.length() > 0 ? name : user.name;
      user.email = email.length() > 0 ? email : user.email;
      user.password = password.length() > 0 ? password : user.password;

      System.out.println("사용자 정보를 변경했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void delete() {
    try {
      int no = Prompt.inputInt("번호? ");
      User user = findByNo(no);
      if (user == null) {
        System.out.println("해당 번호의 사용자가 없습니다.");
        return;
      }
      userList.remove(user);
      System.out.println("사용자를 삭제했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  private static User findByNo(int no) {
    try {
      for (User user : userList) {
        if (user.no == no) {
          return user;
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
    return null;
  }
}
