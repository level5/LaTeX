# [] Vs. [[]]

# [ is a bulitin. this command considers its arguments as comparison expressions or file test and returns an exit status corresponding to the result of the comparision(0 for true, 1 for false).

# [[ is a keyword.

# ((...)) and let constructs return an exit status, according to whether the arithmetic expressons they evaluate expand to a non-zero value.


# let return the same exit status as the double=parentheses arithmetic expansion.


var=-2 && (( var+=2)) 
# first part exist without error, so exist code is 0, means true in logic expression.
# second part, the result of arithmetic expasion is 0, so ((...)) return false(1)
# true && false ==> 1


# if can test any command, not just conditions enclosed within bracket.
if NOT_EXIST_COMMAND 2>/dev/null;
	then
		echo "won't happen"
	else 
		echo "error, this is not a command."
fi


