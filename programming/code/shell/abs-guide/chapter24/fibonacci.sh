#!/bin/bash

# -------------------------------
# Fibo(0) = 0
# Fibo(1) = 1
# Fibo(n) = Fibo(n-1) + Fibo(n-2)
# -------------------------------


MAXTERM=15
MINIDX=2

Fibonacci ()
{
	idx=$1
	if [ "$idx" -lt "$MINIDX" ]; then
		echo "$idx"
	else
		((--idx))
		term1=$(Fibonacci $idx)

		((--idx))
		term2=$(Fibonacci $idx)

		echo $((term1 + term2))
	fi
}

for i in $( seq 0 $MAXTERM)
do
	FIBO=$(Fibonacci $i)			# this is why we don't need two use local variable. new process for each call.
	echo -n "$FIBO "
done

echo 

exit 0