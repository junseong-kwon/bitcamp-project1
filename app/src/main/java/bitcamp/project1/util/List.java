package bitcamp.project1.util;

//데이터 목록을 다루는 일을 할 객체의 사용법
// -> 즉 '그 객체에게 일을 시킬 떄 다음의 메서드를 호출하여 일을 시켜라.'라고 지정하는 문법
public interface List {

  // 데이터를 더할 때 호출 할 메서드
  void add(Object value);//규칙이기 때문에 메서드의 시그너처만 정의한다.

  Object remove(int index); //메서드 바디는 있으면 안된다.

  Object get(int index); // 이것이 추상 메서드

  int indexOf(Object value); // interface는 규칙이고 대부분 공용이라 public 생략 가능

  Object[] toArray(); //abstract도 생략 가능하다.

  int size();

}
