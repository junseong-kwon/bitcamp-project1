package bitcamp.project1.util;

public class LinkedList extends AbstractList {
  private Node head;
  private Node tail;

  private class Node {
    Object value;
    Node next;
    Node prev;

    Node(Object value) {
      this.value = value;
    }
  }

  public void add(Object obj) {
    Node node = new Node(obj);
    if (head == null) {
      head = tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    size++;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node cursor = head;
    int i = 0;
    while (cursor != null) {
      arr[i++] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node cursor = head;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  public boolean remove(Object obj) {
    Node cursor = head;
    while (cursor != null) {
      if (cursor.value.equals(obj)) {
        if (cursor.prev != null) {
          cursor.prev.next = cursor.next;
        } else {
          head = cursor.next;
        }
        if (cursor.next != null) {
          cursor.next.prev = cursor.prev;
        } else {
          tail = cursor.prev;
        }
        size--;
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }
}
