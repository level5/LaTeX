/*
 * define-macro.c
 *
 *  Created on: Jun 14, 2015
 *      Author: root
 */

#define TEST_NAME 1
#define p(name) print_name(#name, name)

static void print_name(char *name, int value)
{
	printf("value of \"%s\" is %d.\n", name, value);
}

void test_pound_sign()
{
	p(TEST_NAME);
}
