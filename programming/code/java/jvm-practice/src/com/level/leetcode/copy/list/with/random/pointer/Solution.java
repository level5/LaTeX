package com.level.leetcode.copy.list.with.random.pointer;

import java.util.HashMap;
import java.util.Map;



public class Solution {
	
	private Map<RandomListNode, Integer> olds = new HashMap<RandomListNode, Integer>();
	private Map<Integer, RandomListNode> news = new HashMap<Integer, RandomListNode>();
	
    public RandomListNode copyRandomList(RandomListNode head) {
        
    	RandomListNode check = head;
    	RandomListNode end = null;
    	int index = 0;
    	while (head != null) {
    		olds.put(head, index++);
    		head = head.next;
    		if (olds.containsKey(head.next)) {
    			end = head;
    			break;
    		}
    	}
    	
    	RandomListNode list = new RandomListNode(0);
    	RandomListNode result = list;
    	index = 0;
    	head = check;
    	while (head != null) {
    		
    		RandomListNode n; 
    		if ((n = news.get(index)) == null) {
    			n = new RandomListNode(head.label);
    			news.put(index, n);
    		}
    		
    		if (head.random != null) {
    			int position = olds.get(head.random);
        		RandomListNode r; 
        		if ((r = news.get(position)) == null) {
        			r = new RandomListNode(head.random.label);
        			news.put(index, r);
        		}
        		n.random = r;
    		}
    		
    		list.next = n; 
    		if (head == end) {
    			break;
    		}
    		
    		list = list.next;
    		head = head.next;
    		index++;
    	}
    	
    	return result.next;
    }
}