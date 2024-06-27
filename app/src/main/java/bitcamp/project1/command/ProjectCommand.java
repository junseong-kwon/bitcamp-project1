package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Project;

public class ProjectCommand {
  static ArrayList<Project> projectList = new ArrayList<>();

  public static void menu() {
    while (true) {
      try {
        System.out.println("[프로젝트 메뉴]");
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
        System.out.printf("오류 발생: %s\n", e.getMessage());
      }
    }
  }

  public static void add() {
    try {
      Project project = new Project();
      project.no = Prompt.inputInt("번호? ");
      project.title = Prompt.inputString("프로젝트명? ");
      project.content = Prompt.inputString("내용? ");
      project.owner = Prompt.inputString("관리자? ");
      project.members = Prompt.inputString("팀원? ");
      project.startDate = Prompt.inputInt("시작일? ");
      project.endDate = Prompt.inputInt("종료일? ");
      projectList.add(project);
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void list() {
    try {
      for (Project project : projectList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n",
            project.no, project.title, project.owner, project.members, project.startDate, project.endDate);
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void detail() {
    try {
      int no = Prompt.inputInt("번호? ");
      Project project = findByNo(no);
      if (project == null) {
        System.out.println("해당 번호의 프로젝트가 없습니다.");
        return;
      }
      System.out.printf("프로젝트명: %s\n", project.title);
      System.out.printf("내용: %s\n", project.content);
      System.out.printf("관리자: %s\n", project.owner);
      System.out.printf("팀원: %s\n", project.members);
      System.out.printf("시작일: %d\n", project.startDate);
      System.out.printf("종료일: %d\n", project.endDate);
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void update() {
    try {
      int no = Prompt.inputInt("번호? ");
      Project project = findByNo(no);
      if (project == null) {
        System.out.println("해당 번호의 프로젝트가 없습니다.");
        return;
      }
      String title = Prompt.inputString(String.format("프로젝트명(%s)? ", project.title));
      String content = Prompt.inputString(String.format("내용(%s)? ", project.content));
      String owner = Prompt.inputString(String.format("관리자(%s)? ", project.owner));
      String members = Prompt.inputString(String.format("팀원(%s)? ", project.members));
      int startDate = Prompt.inputInt(String.format("시작일(%d)? ", project.startDate));
      int endDate = Prompt.inputInt(String.format("종료일(%d)? ", project.endDate));

      project.title = title.length() > 0 ? title : project.title;
      project.content = content.length() > 0 ? content : project.content;
      project.owner = owner.length() > 0 ? owner : project.owner;
      project.members = members.length() > 0 ? members : project.members;
      project.startDate = startDate > 0 ? startDate : project.startDate;
      project.endDate = endDate > 0 ? endDate : project.endDate;

      System.out.println("프로젝트를 변경했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void delete() {
    try {
      int no = Prompt.inputInt("번호? ");
      Project project = findByNo(no);
      if (project == null) {
        System.out.println("해당 번호의 프로젝트가 없습니다.");
        return;
      }
      projectList.remove(project);
      System.out.println("프로젝트를 삭제했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  private static Project findByNo(int no) {
    try {
      for (Project project : projectList) {
        if (project.no == no) {
          return project;
        }
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
    return null;
  }
}
