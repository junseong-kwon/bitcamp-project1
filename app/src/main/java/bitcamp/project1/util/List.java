package bitcamp.project1.util;

public interface List {
  void add(Object obj);
  Object[] toArray();
  Object get(int index);
  boolean remove(Object obj);
  int size();
}
