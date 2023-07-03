package top.xijinian.leetcode100.lru_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache {

    public static class Node {
        Node prev;
        Node next;
        Integer value;
        Integer key;

        public Node() {
        }

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeList {
        Node begin;
        Node end;

        public NodeList() {
            this.begin = new Node(null, null);
            this.end = new Node(null, null);
            begin.next = end;
            end.prev = begin;
        }
    }

    private final Map<Integer, Node> map;
    private final int capacity;
    private final NodeList nodeList;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.nodeList = new NodeList();
    }

    public int get(int key) {
        Node currNode = this.map.get(key);
        if (currNode == null) {
            return -1;
        } else {
            transToFirst(currNode);
            return this.map.get(key).value;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node currNode = this.map.get(key);
            transToFirst(currNode);
            currNode.value = value;
        } else if (this.map.size() == this.capacity) {
            Node currNode = new Node(key, value);
            addToFirst(currNode);
            removeLastNode();
        } else {
            Node currNode = new Node(key, value);
            addToFirst(currNode);
        }
    }

    private void removeLastNode() {
        Node originLastNode = this.nodeList.end.prev;
        this.map.remove(originLastNode.key);
        originLastNode.prev.next = this.nodeList.end;
        this.nodeList.end.prev = originLastNode.prev;
    }

    private void transToFirst(Node currNode) {
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        addToFirst(currNode);
    }

    private void addToFirst(Node currNode) {
        currNode.prev = this.nodeList.begin;
        currNode.next = this.nodeList.begin.next;
        this.nodeList.begin.next = currNode;
        currNode.next.prev = currNode;
        this.map.put(currNode.key, currNode);
    }
}
