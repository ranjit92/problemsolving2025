package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MMT {

    /*    # Example 1:

# Input
# ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
        # [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
        # Output
# [null, null, null, "hello", "hello", null, "hello", "leet"]

# Explanation
# AllOne allOne = new AllOne();
# allOne.inc("hello");
# allOne.inc("hello");
#allOne.inc("hello");
# allOne.getMaxKey(); // return "hello"
# allOne.getMinKey(); // return "hello"
# allOne.inc("leet");
#allOne.inc("leet");
#allOne.inc("abc");
#allOne.dec("leet");
#allOne.dec("leet");
# allOne.getMaxKey(); // return "hello"
# allOne.getMinKey(); // return "leet"
head > hello(3)  > abc(1) > leet(0) > tail
*/

    Map<String, Node> map = new HashMap<>();

    Node freqNode = new Node();
    Node head = freqNode;
    Node tail = freqNode;

    public Node inc(String key){
        Node current = null;
        if(map.containsKey(key)){
            current = map.get(key);
            current.frequency += 1;
        }
        else{
            current = new Node(key, 1);
            map.put(key, current);
        }
        //check both head and tail and update
        if(head.next.frequency < current.frequency){
            deleteNode(current);
            insertNodeFront(current);
        }
        if(tail.prev.frequency > current.frequency){
            deleteNode(current);
            insertNodeBack(current);
        }

        return current;
    }




    public void deleteNode(Node node){

    }
    public void insertNodeFront(Node node){

    }
    public void insertNodeBack(Node node){

    }


}

class Node{
    List<String> value;
    int frequency;
    Node prev;
    Node next;

    public Node(){

    }

    public Node(String key_, int frequency_){

    }
}
