package subject146;

import java.util.HashMap;

/**
 * LRU算法实现
 * @author xiejh
 * @Date 2020/10/16 21:46
 **/
class LRUCache {

    private HashMap<Integer, Node> map;

    private Node head;

    private Node tail;

    private int capacity;

    private int size = 0;

    static class Node {
        Node prev;
        int key;
        int value;
        Node next;

        public Node(Node prev, int key, int value, Node next) {
            this.prev = prev;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("容量不能小于等于0");
        }
        map = new HashMap<>(capacity * 2);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            if (node == tail) {
                return node.value;
            }
            Node pre = node.prev;
            Node next = node.next;
            node.next = null;
            next.prev = pre;
            if (node == head) {
                head = next;
            } else {
                pre.next = next;
            }
            tail.next = node;
            node.prev = tail;
            tail = node;
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            //满了就淘汰
            if (size == capacity) {
                if (head != null) {
                    map.remove(head.key);
                    size--; //减size
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        Node next = head.next;
                        head.next = null;
                        head = next;
                    }
                }
            }
            size++;
            node = new Node(null, key, value, null);
            map.put(key, node);  //要存到map里
            if (head == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                head.next = node;
                node.prev = head;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        } else {
            //value有变化，用新值覆盖旧值
            if (node.value != value) {
                node.value = value;
            }
            if (tail != node) {
                if (node == head) {
                    head = node.next;
                    head.prev = null;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }
    }

    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // 返回  1
//        cache.put(3, 3);    // 该操作会使得关键字 2 作废
//        System.out.println(cache.get(2));       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得关键字 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4

        //测试2
//        LRUCache cache = new LRUCache(2);
//        cache.put(2, 1);
//        cache.put(1, 1);
//        cache.put(2, 3);
//        cache.put(4, 1);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));

        int[][] arr = {{10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25}, {8}, {9, 22},
                {5, 5}, {1, 30}, {11}, {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10}, {10}, {6, 14}, {3, 1}, {3},
                {10, 11}, {8}, {2, 14}, {1}, {5}, {4}, {11, 4}, {12, 24}, {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12},
                {5}, {2, 9}, {13, 4}, {8, 18}, {1, 7}, {6}, {9, 29}, {8, 21}, {5}, {6, 30}, {1, 12}, {10}, {4, 15}, {7, 22},
                {11, 26}, {8, 17}, {9, 29}, {5}, {3, 4}, {11, 30}, {12}, {4, 29}, {3}, {9}, {6}, {3, 4}, {1}, {10}, {3, 29},
                {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10, 9}, {3, 26}, {8}, {7}, {5}, {13, 17}, {2, 27}, {11, 15},
                {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1}, {6, 19}, {4}, {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28},
                {1}, {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26}, {13, 28}, {11, 26}};
        LRUCache cache = new LRUCache(10);
        for (int[] ints : arr) {
            if (ints.length == 1) {
                cache.get(ints[0]);
            } else {
                cache.put(ints[0], ints[1]);
            }
        }

    }
}
