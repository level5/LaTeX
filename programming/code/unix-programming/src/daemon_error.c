/*
 * daemon_error.c
 *
 *  Created on: Jun 13, 2015
 *      Author: root
 */

#include "apue.h"
#include <errno.h>
#include <stdarg.h>
#include <syslog.h>
//
//static void log_doit(int, int, const char *, va_list ap);
//
//extern int log_to_stderr;
//
///*
// * Print a mesage and return to caller
// * Caller specifies "errnoflag" and "priority"
// */
//static void log_doit(int errnoflag, int priority, const char *fmt, va_list ap)
//{
//	int errno_save;
//	char buf[MAXLINE];
//
//	errno_save = errno; /* value caller might want printed. */
//	vsnprintf(buf, MAXLINE, fmt, ap);
//	if (errnoflag)
//		snprintf(buf+strlen(buf), MAXLINE - strlen(buf), ": %s", strerror(errno_save));
//	strcat(buf, "\n");
//	if(log_to_stderr)
//	{
//		fflush(stdout);
//		fputc(buf, stderr);
//		fflush(stderr);
//	}
//	else
//	{
//		syslog(priority, buf);
//	}
//}
