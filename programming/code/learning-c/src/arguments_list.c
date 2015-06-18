/*
 * arguments_list.c
 *
 *  Created on: Jun 12, 2015
 *      Author: root
 */


#include <stdarg.h>

float average(int n_values, ...)
{
	va_list var_arg;
	int count;
	float sum = 0;

	/*
	 * start to visit var list
	 */
	va_start(var_arg, n_values);

	/*
	 *
	 */
	for( count = 0; count < n_values; count +=1 )
	{
		sum += va_arg(var_arg, int);
	}

	/*
	 *
	 */
	va_end(var_arg);

	return sum / n_values;
}
