/*
 * recursion.c
 *
 *  Created on: 2015-6-24
 *      Author: user
 */


int fact(int n)
{
    if (n < 0)
        return 0;
    else if (n == 0 || n == 1)
        return 1;
    else
        return n * fact(n-1);
}


int fact_tail(int n, int a)
{
    if (n < 0)
        return 0;
    else if (n == 0 || n == 1)
        return a;
    else
        return fact_tail(n-1, n*a);
}



