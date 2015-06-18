/*
 * process_environment.c
 *
 *  Created on: Jun 14, 2015
 *      Author: root
 */


#include "apue.h"

static void my_exit1(void);
static void my_exit2(void);

void test_atexit(void)
{
	if (atexit(my_exit2) != 0)
		err_sys("cannot register my_exit2");
	if(atexit(my_exit1) != 0)
		err_sys("cannot register my_exit1");
	if(atexit(my_exit1) != 0)
		err_sys("cannot register my_exit1");

	printf("test_atexit is done\n");
}

static void my_exit1()
{
	printf("first exit handler.\n");
}

static void my_exit2()
{
	printf("second exit handler.\n");
}
