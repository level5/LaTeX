#!/bin/bash

# Demonstration of recursion.


RECURSION=9
r_count=0

recuse ()
{
	var="$1"

	while [ "$var" -ge 0 ]
	do
		echo "Recursion count = "$r_count"  +-+  \$var ="$var""
		(( var-- )); (( r_count++ ))
		recuse "$var"
	done
}

recuse $RECURSION

exit $?