
# function function_name() {
#   COMMAND
# }

# function_name() {
#   COMMAND
# }

# fun(){COMMAND1; COMMAND2;}


# Function declaration must precede call


f1()
{
  echo "call function f1"
  f2
}

f2()
{
  echo "call function f2"
}

f1        # it works because f2 is not actually called until this point.


# function may not be empty.
# a function containing only comments is empty.



# shell script only pass value parameters to function.
