package xzy.leetCode.list;

public class Problem_2 {

    public static void main(String[] args){
        Node head = initNode();
        copy(head);
        System.out.println(head);
    }

    public static Node copyRandomList(Node head){

        return null;
    }

    public static void copy(Node head){
        while(head!=null){
            Node cloneNode = new Node(head.val);
            Node nextNode = head.next;
            head.next = cloneNode;
            cloneNode.next = nextNode;
            head = cloneNode.next;
        }

    }

    public static Node initNode(){
        Node head = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(1);
        Node node3 = new Node(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        return head;
    }



    static class Node{
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}


