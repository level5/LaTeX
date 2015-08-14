#!/bin/bash
# function function_name () {
#   COMMAND
# }

# function_name () {
#   COMMAND
# }

# fun (){COMMAND1; COMMAND2;}


# Function declaration must precede call
# 函数定义必须出现在函数调用前面


f1 ()
{
  echo "call function f1"
  f2
}

f2 ()
{
  echo "call function f2"
}

f1        # it works because f2 is not actually called until this point.
          # 这个是可以的，因为f2实际的调用点是在这里。


# function may not be empty.
# a function containing only comments is empty.
# 函数不能为空
# 只含有注释的函数也是一个空函数


# shell script only pass value parameters to function.



# 函数可以嵌套，但是其实并没有什么卵用



# 函数参数

f3(){
  if [[ -z "$1" ]]; then
    echo "-Parameter #1 is zero length"
  else
    echo "-Parameter #1 is \"$1\""
  fi
}

f3

f3 ""

f3 "kitta"
