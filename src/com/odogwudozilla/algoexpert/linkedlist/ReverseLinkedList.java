package com.odogwudozilla.algoexpert.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList {
    static List<Integer> nodeValues = new ArrayList<>();

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = reverseList(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }

        ListNode thisHead = new ListNode(2);
        thisHead.next = new ListNode(4);
        thisHead.next.next = new ListNode(6);
        thisHead.next.next.next = new ListNode(8);
        thisHead.next.next.next.next = new ListNode(10);

        System.out.println(linkedListValues(thisHead));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode previous =  null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }

    private static List<Integer> linkedListValues (ListNode currentNode) {

        if(currentNode == null) return nodeValues;

        nodeValues.add(currentNode.value);

        return linkedListValues(currentNode.next);
    }
}
