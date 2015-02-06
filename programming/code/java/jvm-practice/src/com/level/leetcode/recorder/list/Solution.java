package com.level.leetcode.recorder.list;


public class Solution {
    public void reorderList(ListNode head) {
    	
    	if (head == null) return;
    	
        int length = 0;
        ListNode step = head;
        while (step != null) {
        	length++;
        	step = step.next;
        }
        
        int mid = length / 2 + length % 2;
        step = head;
        while (--mid > 0) {
        	step = step.next;
        }
        
        ListNode second = step.next;
        step.next = null;
        
        step = second;
        ListNode pre = null;
        while (step != null) {
        	ListNode tmp = step.next;
        	step.next = pre;
        	pre = step;
        	step = tmp;
        }
        second = pre;
        //merge
        while (head != null && second != null) {
        	ListNode tmp1 = head.next;
        	ListNode tmp2 = second.next;
        	
        	head.next = second;
        	second.next = tmp1;
        	
        	head = tmp1;
        	second = tmp2;
        }
        
    }
}
