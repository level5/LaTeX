package com.level.jvm.lesson08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Calculation {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("E:/workspaces/android/jvm-practice/src/com/level/jvm/lesson08/result.txt");
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		ArrayList<Long> list = new ArrayList<Long>(9963);
		while ((line = br.readLine()) != null) {
			line = line.replace(",", "").trim();
			list.add(Long.parseLong(line));
		}
		Collections.sort(list, new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				if (o1 - o2 > 0) {
					return 1;
				} else if (o1 -o2 == 0) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		
		int max = -1;
		long start = -1;
		long end = -1;
		
		for (int i = 0; i < list.size(); i++) {
			int count = 0;
			long s = list.get(i);
			long e = s;
			int j = i;
			while (j < list.size() && list.get(j) - s <= 1000) {
				count++;
				e = list.get(j);
				j++;
			}
			if (count  > max) {
				max = count;
				start = s;
				end = e;
			}
		}
		
		System.out.println("max:" + max + ", start:" + start + ", end:" + end);
	}
}
