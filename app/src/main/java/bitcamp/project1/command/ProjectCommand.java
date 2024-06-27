package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Project;
import bitcamp.project1.vo.User;

public class ProjectCommand {

  LinkedList projectList = new LinkedList();
  LinkedList userList;

  public ProjectCommand(LinkedList userList) {
    this.userList = userList;
  }

  private void addMembers(Project project) {
    while (true) {
      int userNo = Prompt.inputInt("추가할 팀원 번호?(종료: 0)");
      if (userNo == 0) {
        break;
      }

      User user = (User) userList.get(userList.indexOf(new User(userNo)));
      if (user == null) {
        System.out.println("없는 팀원입니다.");
        continue;
      }

      if (project.getMembers().contains(user)) {
        System.out.printf("'%s'은 현재 팀원입니다.\n", user.getName());
        continue;
      }

      project.getMembers().add(user);
      System.out.printf("'%s'을 추가했습니다.\n", user.getName());
    }
  }

  private void viewProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project project = (Project) projectList.get(projectList.indexOf(new Project(projectNo)));
    if (project == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }
    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("설명: %s\n", project.getDescription());
    System.out.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
    System.out.println("팀원:");
    for (int i = 0; i < project.getMembers().size(); i++) {
      User user = (User) project.getMembers().get(i);
      System.out.printf("- %s\n", user.getName());
    }
  }

  private void updateProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project project = (Project) projectList.get(projectList.indexOf(new Project(projectNo)));
    if (project == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }
    project.setTitle(Prompt.input("프로젝트명(%s)?", project.getTitle()));
    project.setDescription(Prompt.input("설명(%s)?", project.getDescription()));
    project.setStartDate(Prompt.input("시작일(%s)?", project.getStartDate()));
    project.setEndDate(Prompt.input("종료일(%s)?", project.getEndDate()));

    System.out.println("팀원:");
    deleteMembers(project);
    addMembers(project);

    System.out.println("변경 했습니다.");
  }

  private void deleteProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project deletedProject = (Project) projectList.get(projectList.indexOf(new Project(projectNo)));
    if (deletedProject != null) {
      System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedProject.getTitle());
    } else {
      System.out.println("삭제 되었습니다.");
    }

  }

  public void executeProjectCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addProject();
        break;
      case "조회":
        viewProject();
        break;
      case "목록":
        listProject();
        break;
      case "변경":
        updateProject();
        break;
      case "삭제":
        deleteProject();
        break;
    }
  }

  private void addProject() {
    Project project = new Project();
    project.setTitle(Prompt.input("프로젝트명?"));
    project.setDescription(Prompt.input("설명?"));
    project.setStartDate(Prompt.input("시작일?"));
    project.setEndDate(Prompt.input("종료일?"));

    System.out.println("팀원:");
    addMembers(project);
    project.setNo(Project.getNextSeqNo());
    this.projectList.add(project);
    System.out.println("등록했습니다.");
  }

  private void listProject() {
    System.out.println("번호 프로젝트 기간");
    for (Object obj : projectList.toArray()) {
      Project project = (Project) obj;
      System.out.printf("%d %s %s ~ %s\n",
          project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
    }
  }

  private void deleteMembers(Project project) {
    Object[] members = project.getMembers().toArray();
    for (Object obj : members) {
      int index = project.getMembers().indexOf(obj);
      User member = (User) obj;
      String str = Prompt.input("팀원(%s) 삭제?", member.getName());
      if (str.equalsIgnoreCase("y")) {
        project.getMembers().remove(index);
        System.out.printf("'%s' 팀원을 삭제합니다.\n", member.getName());
      } else {
        System.out.printf("'%s' 팀원을 유지합니다.\n", member.getName());
      }
    }
  }


}
