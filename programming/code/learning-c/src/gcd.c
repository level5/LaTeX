/*
 * gcd.c
 *
 *  Created on: 2015年6月22日
 *      Author: huang
 */



int gcd(int m, int n)
{
    if (n == 0)
        return m;
    return gcd(n, m%n);
}


int gcd2(int m, int n)
{
    if (!m || !n)
        return m ? m : n;

    int t = m < n ? m : n;
    while (t > 0)
    {
        if (!(m%t) && !(n%t))
            break;
    }
    return t;
}
