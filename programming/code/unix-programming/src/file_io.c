/*
 * file_io.c
 *
 *  Created on: Jun 13, 2015
 *      Author: root
 */

#include "apue.h"
#include <fcntl.h>

static const char *NOT_EXISTS_FILE = "/home/huang/tmp/notexist";

/*
 * 		O_RDONLY: ready only
 * 		O_WRONLY: write only
 * 		O_RDWR:	  read and write
 *
 * 		O_APPEND:
 *		O_CREAT:
 *		O_EXCL: used with O_CREAT, if file is not exist, create file; if file is exist, return error.
 *		O_TRUNC: open with O_WRONLY or O_RDWR, turncate file length to 0.
 *		O_NOCTTY: ??
 *		O_NONBLOCK: ??
 *
 *		O_DSYNC, O_SYNC
 *		O_RSYNC
 *
 */
void open_file()
{
	char* content = "abcdefghijklmnopqrstuvwxwz";

	int f = open(NOT_EXISTS_FILE, O_RDWR);
	if(f < 0) {
		printf("cannot open file.\n");
	} else {
		printf("delete %s first.", NOT_EXISTS_FILE);
		close(f);
		exit(1);
	}

	f = open(NOT_EXISTS_FILE, O_RDWR | O_CREAT | O_APPEND);
	if (f < 0) {
		printf("cannot create file.\n");
		exit(1);
	}

}


void test_open_file()
{
	int fp;
	if ((fp = open(NOT_EXISTS_FILE, O_RDWR)) < 0)
		err_ret("%s is not exist.", NOT_EXISTS_FILE);
	if (close(fp) != 0)
		err_exit("cannot close %s", NOT_EXISTS_FILE);
}

