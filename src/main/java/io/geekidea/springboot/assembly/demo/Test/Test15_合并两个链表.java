package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
public class Test15_合并两个链表 {


    public static void main(String[] args) {
        Node1 head1 = new Node1(1);//1->3->5
        Node1 node3 = new Node1(3);
        head1.next = node3;
        Node1 node5 = new Node1(5);
        node3.next = node5;
        node5.next = null;

        Node1 head2 = new Node1(2);//2->3->6
        Node1 node4 = new Node1(3);
        head2.next = node4;
        Node1 node6 = new Node1(6);
        node4.next = node6;
        node6.next = null;

        Node1 mergeHead = getIntersectionNode(head1, head2);
        System.out.println("是否有公共点" + mergeHead.data);

        Node1 mergeHead2 = getIntersectionNode2(head1, head2);
        System.out.println("是否有公共点2" + mergeHead2.data);



    }

    public static Node1 getIntersectionNode(Node1 headA, Node1 headB) {
        if (headA == null || headA == null) return null;
        Node1 A = headA, B = headB;

        while (A.data != B.data) {
            A = A == null ? headA : A.next;
            B = B == null ? headB : B.next;
        }

        return A;
    }


    public static  Node1 getIntersectionNode2(Node1 headA, Node1 headB) {
        if(headA == null || headB == null) return null;
        HashSet<Integer> set = new HashSet<Integer>();

        while(headA != null){
            set.add(headA.data);
            headA = headA.next;
        }

        while(headB != null){
            if(set.contains(headB.data)){
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }


}

/**
 * 定义一个Node节点
 */
class Node1 {
    Node1 next = null;
    int data;

    public Node1(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}