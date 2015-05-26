# protecting a comand-line parameter from the shell

# suppress echo's "appetite" for newlines

echo ${ls -l}

echo "${ls -l}"

# "..." double quotes prevent reinterpretation of all special characters with the quoted string except $, `(backquote) and(escape).

# '...' single quotes prevent reinterpretation of all special characters with the quoted string except '(singlequote).
# so variable reference is turn off in single quotes.

List="one two three"

for a in $List
do
  echo "$a"
done
# one
# two
# three

for a in "$List"
do
  echo "$a"
done
# one two three





var="a variable containing five words"
COMMAND This is $var
# COMMAND with 7 arguments: This is a variable containing five words

COMMAND "This is $var"
# COMMAND with 1 argument: "This is a variable containing five words"


var2=""

COMMAND $var2 $var2 $var2
# no argument

COMMAND "$var2" "$var2" "$var2"
# 3 empty arguments

COMMAND "$var2 $var2 $var2"
# 1 argument(2 spaces)



# Echoing Weird Variables

var="'(]\\{}\$\""

echo $var         # '(]\{}$"
echo "$var"       # '(]\{}$" same as above


IFS='\'    # IFS

echo $var         # '(] {}$"
echo "$var"       # '(]\{}$"




# Escape a space can prevent word splitting in a command's argument list






