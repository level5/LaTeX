package com.level.jvm.lesson03;

import java.io.File;


public class ListFiles {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("help ls <dir>");
			return;
		}
		File f = new File(args[0]);
		if (!f.isDirectory()) {
			return;
		}
		for (File file : f.listFiles()) {
			System.out.println(file.toString());
		}
	}
}
