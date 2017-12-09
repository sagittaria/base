package searching;

import fundamentals.Queue;

public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);//
    }

    public int size() {
        int i = 0;
        for (Node x = first; x != null; x = x.next) {
            i++;
        }
        return i;
    }

    public Iterable<Key> keys() {
        Queue<Key> keys = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            keys.enqueue(x.key);
        }
        return keys;
    }

    public void delete(Key key) {
        if (key.equals(first.key)) {// 如果要删的是第一个元素
            first = first.next;
            return;// 如果只有一个元素，会正常地赋值first=null
        }

        for (Node x = first; x != null; x = x.next) {
            if (x.next != null && key.equals(x.next.key)) {// 普通情况，删中间的元素
                x.next = x.next.next;
                break;
            } else if (x.next == null) {// 已经到达链表尾
                if (key.equals(x.key)) {// 并且正好是要删的
                    for (Node y = first; y != null; y = y.next) {// 那么再轮询一遍
                        if (y.next == x) {// 找到倒数第二个，也就是x的前一个
                            y.next = null;// 将其置为null
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, String> sst = new SequentialSearchST<String, String>();
        sst.put("key1", "val1");
        sst.put("key2", "val2");
        sst.put("key3", "val3");
        System.out.println("size=" + sst.size());
        System.out.println("---------------------");
        Iterable<String> keys = sst.keys();
        for (String key : keys) {
            System.out.println(sst.get(key));
        } // 输出是 val3、val2、val1，因为队列实现的时候，新节点是添加在最前的
          // first = new Node(key, val, first);

        System.out.println("---------------------");
        sst.put("key2", "val4");
        keys = sst.keys();
        for (String key : keys) {
            System.out.println(sst.get(key));
        }

        System.out.println("------什么也没发生---------------");
        sst.delete("key5");
        keys = sst.keys();
        for (String key : keys) {
            System.out.println(sst.get(key));
        }

        System.out.println("---------------------");
        // sst.delete("key3");
        sst.delete("key2");
        // sst.delete("key1");
        keys = sst.keys();
        for (String key : keys) {
            System.out.println(sst.get(key));
        }

    }
}
