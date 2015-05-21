#

# This line is a comment

echo "A comment will follow." # Comment here.
#                            ^ Note whitespace before #

echo "The # here does not begin a comment."
echo 'The # here does not begin a comment.'
echo The \# here does not begin a coment.
echo The # here begins a comment.

echo ${PATH#*:}       # Parameter substitution, not a coment.
echo $((2#101011))    #Base conversion, not a comment.



# ;;
# Terminator in a case option [double semicolon]

case "$variable" in
  abc) echo "\$variable = abc";;
  xyz) echo "\$variable = xyz";;
esac

# .
# dot command [period]. equivalent to source. This is a bash builtin.


# as a component of a filename
# as a directory name


# character match
