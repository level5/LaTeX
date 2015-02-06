package com.level.leetcode.restore.ip.addresses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	
	private Map<String, List<String>> result = new HashMap<String, List<String>>();
	
    public List<String> restoreIpAddresses(String s) {
        
    	return null;
    }
    
    private List<String> resortIp(String s, int count) {
    	
    	List<String> result = new ArrayList<String>();

    	if (count == 1) {
    		int i = Integer.parseInt(s);
    		if (i >= 0 && i <= 255) {
    			result.add(s);
    			return result;
    		} else {
    			return result;
    		}
    	} 
    	
    	return null;
    	
    }
}
