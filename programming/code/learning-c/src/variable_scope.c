/*
 * variable_scope.c
 *
 *  Created on: Jun 13, 2015
 *      Author: root
 */


int b;

int c(int d)
{
	int e;
	int f(int g);
	return 0;
}


static int i;

int func()
{
	int j;
	extern int k;
	extern int i;
	return 0;
}
