# [] Vs. [[]]

# [ is a bulitin. this command considers its arguments as comparison expressions or file test and returns an exit status corresponding to the result of the comparision(0 for true, 1 for false).

# [[ is a keyword.

# ((...)) and let constructs return an exit status, according to whether the arithmetic expressons they evaluate expand to a non-zero value.


var=-2 && (( var+=2))
# first part exist without error, so exist code is 0, means true in logic expression.
# second part, the result of arithmetic expression is 0, so ((...)) return false(1)
# true && false ==> 1
echo "exit status of var=-2 && (( var+=2)) is $?."


# if can test any command, not just conditions enclosed within bracket.
if NOT_EXIST_COMMAND 2>/dev/null;
	then
		echo "won't happen"
	else
		echo "error, this is not a command."
fi



# File test

# File exit; -e, -a; -a is deprecated.
if [ -e "/etc/passwd" ]; then
	echo "/etc/passwd exists"
else
	echo "/etc/passwd does not exist"
fi

if [ -a "/etc/password" ]; then
	echo "/etc/password exists"
else
	echo "/etc/password does not exist"
fi

# is regular file; -fi

if [ -f "/etc/passwd" ]; then
	echo "/etc/passwd is a regular file."
else
	echo "/etc/passwd is not a regular file."
fi

# is directory; -d
if [ -d "/etc" ]; then
	echo "/etc is a directory."
else
	echo "/etc is not a diretory."
fi

# !
if [ ! -d "/etc/passwd" ]; then
	echo "/etc/passwd is not a directory."
else
	echo "/ect/passwd is a direcotry."
fi

# string test

# -z is null

# -n is not null

# if there is only one argument, it is true if and only if argument is not null.
if [ ! ]; then
	echo "! is true."
else
	echo "! is false."
fi

# so, 0, 1 is true
if [ 0 ]; then
	echo "0 is true."
else
	echo "0 is false."
fi

if [ 1 ]
then
	echo "1 is true."
else
	echo "1 is false."
fi

# and undefined variable and defined variable with null value if false.
if [ "$var" ]; then
	echo "undefined var is true."
else
	echo "undefined var is false."
fi

var=
if [ "$var" ]; then
	echo "defined variable with null value is true."
else
	echo "defined variable with null value is false."
fi

# it seems empty string is null
if [ "" ]; then
	echo "empty string is not null."
else
	echo "empty string is null."
fi
