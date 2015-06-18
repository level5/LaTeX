/*
 ============================================================================
 Name        : learning-c.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#include "my_head.h"

void input_output();

int main(void) {
//	printf("hello world!\n");
//	input_output();

//	printf("average of 1,2,3,4,5 is %f", average(5, 1, 2, 3, 4, 5));
	test_pound_sign();

	return 0;
}

void input_output() {
	int c;
	while ((c = getchar()) != EOF) {   // stdio.h
		putchar(c);
	}
}

