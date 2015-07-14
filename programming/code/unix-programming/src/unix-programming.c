/*
 ============================================================================
 Name        : unix-programming.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include "apue.h"
#include "myhead.h"

#include <fcntl.h>
#include <string.h>


#define MAX_BUFFER 1024

typedef int Func(int);

void output_file();
void print_file(char*);


void test_typeof();

int main(void) {
//	open_file();
//	output_file();
//	test_write_eof();

//	test_typeof();
//	test_atexit();

	printf("function != &function:%s\n", output_file == &output_file? "true":"false");

	return 0;
}


void output_file()
{
	char* path="/home/huang/workspaces/c/unix-programming/src/unix-programming.c";
	print_file(path);

}

void print_file(char* path)
{
	char buf[MAX_BUFFER];
	int n;

	int f = open(path, O_RDONLY);
	if (f < 0) {
		printf("cannot open file.\n");
		exit(1);
	}

	while((n = read(f, buf, MAX_BUFFER-1)) != 0) {
		buf[n] = '\0';
		printf("%s", buf);
	}

	close(f);
}

void create_file(char* path, char* content)
{
	int f = open(path, O_RDWR | O_CREAT | O_TRUNC);
	if(f < 0)
		exit(1);

	int rst = write(f, content, strlen(content));
	int rst2 = close(f);

	if (rst < 0 || rst2 < 0)
		exit(1);
}

int ssqrt(int a)
{
	return a*a;
}

void test_typeof()
{
	Func* funpoint = &ssqrt;

	int ret = (*funpoint)(10);
	printf("result is %d", ret);


}


void test_dup()
{
	int ret = close(0);
}

void test_const()
{

}


// how to truncate a file to a specified position.



