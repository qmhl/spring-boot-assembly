package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.Exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test14_合并两个链表 {


    public static void main(String[] args) {
        Node head1 = new Node(1);//1->3->5
        Node node3 = new Node(3);
        head1.next = node3;
        Node node5 = new Node(5);
        node3.next = node5;
        node5.next = null;

        Node head2 = new Node(2);//2->4->6
        Node node4 = new Node(4);
        head2.next = node4;
        Node node6 = new Node(6);
        node4.next = node6;
        node6.next = null;

//        Node mergeHead = mergeList(head1, head2);
//        while (mergeHead != null) {
//            System.out.print(mergeHead.data + " ");
//            mergeHead = mergeHead.next;//1->2->3->4->5->6
//        }

        Node mergeHead2 = mergeList2(head1, head2);
        while (mergeHead2 != null) {
            System.out.print(mergeHead2.data + " ");
            // mergeHead2.next  后移 遍历
            mergeHead2 = mergeHead2.next;//1->2->3->4->5->6
        }
    }


    /**
     * https://blog.csdn.net/zhangquan2015/article/details/82818614
     * 使用递归
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node mergeList(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Node head = null;
        if (head1.data < head2.data) {
            head = head1;
            head.next = mergeList(head1.next, head2);
        } else {
            head = head2;
            head.next = mergeList(head1, head2.next);
        }
        return head;
    }


    /**
     * 使用头结点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node mergeList2(Node head1, Node head2) {
        // 虚拟一个head节点
        Node finalRes = new Node(-1);
        Node res = finalRes;
        while (head1 != null & head2 != null) {
            if (head1.data > head2.data) {
                res.next = head2;
                head2 = head2.next;
                res = res.next;
            } else {
                res.next = head1;
                head1 = head1.next;
                res = res.next;
            }
            System.out.println();
        }
        if (head1 == null) {
            res.next = head2;
        }
        if (head2 == null) {
            res.next = head1;
        }
        return finalRes.next;
    }


}

/**
 * 定义一个Node节点
 */
class Node {
    Node next = null;
    int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


